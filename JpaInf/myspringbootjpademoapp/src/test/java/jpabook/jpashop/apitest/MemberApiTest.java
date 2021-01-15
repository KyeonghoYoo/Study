package jpabook.jpashop.apitest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;

import jpabook.jpashop.api.MemberApiController;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.member.MemberDTO;
import jpabook.jpashop.domain.valtype.Address;
import jpabook.jpashop.service.MemberService;

@WebMvcTest(controllers = MemberApiController.class)
public class MemberApiTest {

	MockMvc mockMvc;

	@MockBean
	MemberService memberService;

	@BeforeEach
	void initEach() {
		this.mockMvc = standaloneSetup(new MemberApiController())
				.addFilter(new CharacterEncodingFilter("UTF-8", true))
				.defaultRequest(get("/api/test/members").accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.alwaysExpect(status().isOk())
				.alwaysExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.alwaysDo(print())
				.build();
		
		List<Member> memberList = new ArrayList<>();
		Member member1 = new Member();
		member1.setId(1L);
		member1.setUsername("member1");
		member1.setAddress(new Address("서울", "오솔길", "123-123"));
		
		Member member2 = new Member();
		member1.setId(2L);
		member2.setUsername("member2");
		member2.setAddress(new Address("제주", "올레길", "123-123"));
		
		Member member3 = new Member();
		member1.setId(3L);
		member3.setUsername("member3");
		member3.setAddress(new Address("부산", "가락길", "123-123"));
		
		memberList.add(member1);
		memberList.add(member2);
		memberList.add(member3);
		
		when(memberService.findMembers()).thenReturn(memberList);
	}

	@Test
	public void 회원_apiSpecTest_username_존재() throws Exception {
		this.mockMvc.perform(get("/api/test/members"))
			.andExpect(jsonPath("$..['username']").exists())
			.andExpect(jsonPath("$[0].username").value(equalTo("member1")))
			.andExpect(jsonPath("$[0].address.city").value(equalTo("서울")));
	}

	@Test
	public void 회원_apiSpecTest_속성값_존재() throws Exception {
		String expectByUsername = "$.[?(@.username == '%s')]";
		String addressByCity = "$..address[?(@.city == '%s')]";

		this.mockMvc.perform(get("/api/test/members"))
				.andExpect(jsonPath(expectByUsername, "member1").exists())
				.andExpect(jsonPath(expectByUsername, "member2").exists())
				.andExpect(jsonPath(expectByUsername, "member3").exists())
				.andExpect(jsonPath(addressByCity, "서울").exists())
				.andExpect(jsonPath(addressByCity, "제주").exists())
				.andExpect(jsonPath(addressByCity, "부산").exists())
				.andExpect(jsonPath("$..['username']").exists())
				.andExpect(jsonPath("$[0]").exists())
				.andExpect(jsonPath("$[1]").exists())
				.andExpect(jsonPath("$[2]").exists());
	}

	@RestController
	private class MemberApiController {

		@GetMapping("/api/test/members")
		public List<MemberDTO> get() {
			List<Member> findMembers = memberService.findMembers();

			return findMembers.stream()
					.map(
						e -> MemberDTO.builder()
						.id(e.getId())
						.name(e.getUsername())
						.address(e.getAddress())
						.build())
					.collect(Collectors.toList());
		}
	}
}
