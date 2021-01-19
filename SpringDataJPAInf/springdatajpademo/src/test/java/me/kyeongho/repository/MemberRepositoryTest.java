package me.kyeongho.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import me.kyeongho.entity.Member;
import me.kyeongho.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {
	
	@Autowired MemberRepository memberRepository;
	
	@Test
	public void testMember() {
		Member member = new Member("memberA");
		
		Member savedMember = memberRepository.save(member);
		
		Optional<Member> findMember = memberRepository.findById(savedMember.getId());
		
		assertThat(findMember.get().getId()).isEqualTo(member.getId());
		assertThat(findMember.get().getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember.get()).isEqualTo(member);
	}
	
}
