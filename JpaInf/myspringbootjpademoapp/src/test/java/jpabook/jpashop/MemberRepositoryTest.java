package jpabook.jpashop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.member.MemberRepository;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@Transactional
	@Rollback(false) // 스프링이 기본적으로 테스트 데이터는 롤백해버림, 해당 옵션을 주면 롤백 안 함
	public void testMember() throws Exception {
		//given
		Member member = new Member();
		member.setUsername("memberA");
		
		
		//when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);
		
		//then
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member); // 영속성 컨텍스트를 통해 엔티티를 받기 때문에 true가 나옴
	}
}
