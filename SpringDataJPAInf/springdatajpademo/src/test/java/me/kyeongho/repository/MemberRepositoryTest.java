package me.kyeongho.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import me.kyeongho.entity.Member;

@Slf4j
@SpringBootTest
@Transactional
public class MemberRepositoryTest {
	
	@Autowired MemberRepository memberRepository;
	
	@Test
	public void testMember() {
		log.info("memberRepository = " + memberRepository.getClass());
		
		Member member = new Member("memberA");
		
		Member savedMember = memberRepository.save(member);
		
		Optional<Member> findMember = memberRepository.findById(savedMember.getId());
		
		assertThat(findMember.get().getId()).isEqualTo(member.getId());
		assertThat(findMember.get().getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember.get()).isEqualTo(member);
	}
	
	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		
		Member savedMember1 = memberRepository.save(member1);
		Member savedMember2 = memberRepository.save(member2);
		
		// 단건 조회
		Optional<Member> findMember1 = memberRepository.findById(member1.getId());
		Optional<Member> findMember2 = memberRepository.findById(member2.getId());
		assertThat(findMember1.get()).isEqualTo(member1);
		assertThat(findMember2.get()).isEqualTo(member2);
		
		// 커밋 시점에 변경 감지(Dirty Checking)에 의한 변경이 일어난다.
		findMember1.get().setUsername("modifedMember1");
		
		// 리스트 조회
		List<Member> all = memberRepository.findAll();
		assertThat(all.size()).isEqualTo(2);
		
		// 카운트 검증
		long count = memberRepository.count();
		assertThat(count).isEqualTo(2);
		
		// 삭제 검증
		memberRepository.delete(member1);
		memberRepository.delete(member2);
		
		long count2 = memberRepository.count();
		assertThat(count2).isEqualTo(0);
	}
	
	@Test
	public void findByUsernameAndAgeGreaterThen() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("aaa", 20);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		List<Member> findMembers = memberRepository.findByUsernameAndAgeGreaterThan("aaa", 15);
		
		findMembers.forEach(m -> log.info("member = " + m));
		
		assertThat(findMembers.size()).isEqualTo(1);
		assertThat(findMembers.get(0).getAge()).isEqualTo(20);
	}
}
