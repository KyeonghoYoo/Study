package me.kyeongho.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import me.kyeongho.entity.Member;

@SpringBootTest
@Transactional
public class MemberJpaRepositoryTest {
	
	@Autowired MemberJpaRepository memberJpaRepository;
	
	@Test
	public void testMember() {
		Member member = new Member("memberA");
		
		Member savedMember = memberJpaRepository.save(member);
		
		Member findMember = memberJpaRepository.find(savedMember.getId());
		
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member);
	}
	
	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		
		Member savedMember1 = memberJpaRepository.save(member1);
		Member savedMember2 = memberJpaRepository.save(member2);
		
		// 단건 조회
		Member findMember1 = memberJpaRepository.find(member1.getId());
		Member findMember2 = memberJpaRepository.find(member2.getId());
		assertThat(findMember1).isEqualTo(member1);
		assertThat(findMember2).isEqualTo(member2);
		
		// 커밋 시점에 변경 감지(Dirty Checking)에 의한 변경이 일어난다.
		findMember1.setUsername("modifedMember1");
		
		// 리스트 조회
		List<Member> all = memberJpaRepository.findAll();
		assertThat(all.size()).isEqualTo(2);
		
		// 카운트 검증
		long count = memberJpaRepository.count();
		assertThat(count).isEqualTo(2);
		
		// 삭제 검증
		memberJpaRepository.delete(member1);
		memberJpaRepository.delete(member2);
		
		long count2 = memberJpaRepository.count();
		assertThat(count2).isEqualTo(0);

	}
	
}
