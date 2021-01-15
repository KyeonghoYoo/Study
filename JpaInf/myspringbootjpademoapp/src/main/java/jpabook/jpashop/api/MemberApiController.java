package jpabook.jpashop.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.member.MemberDTO;
import jpabook.jpashop.domain.valtype.Address;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberService memberService;
	
	@GetMapping("/api/v1/members")
	public List<MemberDTO> findMembersV1() {
		List<Member> findMembers = memberService.findMembers();

		return findMembers.stream()
				.map(e -> MemberDTO.builder()
						.id(e.getId())
						.name(e.getUsername())
						.address(e.getAddress())
						.build())
				.collect(Collectors.toList());
	}
	
	@GetMapping("/api/v2/members")
	public Result<List<MemberDTO>> memberV2() {
		List<Member> findMembers = memberService.findMembers();
		List<MemberDTO> collect = findMembers.stream()
				.map(m -> new MemberDTO(m.getId(), m.getUsername(), m.getAddress()))
				.collect(Collectors.toList());
		
		return new Result<List<MemberDTO>>(collect.size() , collect);
	}
	
	// 바로 ArrayList로 반환하면 API 스팩의 확장성이 떨어지므로 한 번 감싸줘서 보내는 걸 추천한다.
	// data의 갯수인 count 정보와 같은 DTO 값 외에도 요구사항이 추가되어도 유연하게 대처할 수 있기 때문
	@Data
	@AllArgsConstructor
	static class Result<T> {
		
		private int count;
		
		private T data;
	}
	
	
	@PostMapping("/api/v1/members")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	
	// 별도의 DTO를 사용하면 서비스, 레퍼지토리 단의 코드가 수정되어도 API 스팩이 바뀌지 않는다.
	@PostMapping("/api/v2/members")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
		Member newMember = new Member();
		newMember.setUsername(request.getName());
		
		Long id = memberService.join(newMember);
		return new CreateMemberResponse(id);
	}
	
	@PutMapping("/api/v2/members/{id}")
	public UpdateMemberResponse updateMemberV2(
			@PathVariable("id") Long id,
			@RequestBody @Valid UpdateMemberRequest request) {
		memberService.update(id, request.getName());
		Member findMember = memberService.findOne(id);
		
		return new UpdateMemberResponse(findMember.getId(), findMember.getUsername());
		
		
	}
	
	@Data
	static class CreateMemberRequest {
		
		@NotEmpty
		private String name;
		
		private Address address;
	}
	
	@Data
	static class CreateMemberResponse {
		private Long id;

		public CreateMemberResponse(Long id) {
			this.id = id;
		}
		public CreateMemberResponse(Member member) {
			this.id = member.getId();
		}
		
	}
	
	@Data
	static class UpdateMemberRequest {
		
		private String name;
		
	}
	
	@Data
	@AllArgsConstructor
	static class UpdateMemberResponse {
		
		private Long id;
		
		private String name;
	}
}
