package jpabook.jpashop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;

@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;
	
	@Test
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setUsername("Yoo");
		
		//when
		Long savedId = memberService.join(member);
		
		//then
		em.flush();
		assertEquals(member, memberRepository.find(savedId));
	}
	
	@Test
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setUsername("Kyeong");
		
		Member member2 = new Member();
		member2.setUsername("kyeong");
		
		//when
		memberRepository.save(member1);
		memberRepository.save(member2); // 예외가 발생해야함!!!	
		
		//then
		assertThatIllegalStateException();
	}
}
