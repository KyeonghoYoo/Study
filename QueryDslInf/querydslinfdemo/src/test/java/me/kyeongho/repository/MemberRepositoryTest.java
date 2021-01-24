package me.kyeongho.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import me.kyeongho.dto.MemberSearchCondition;
import me.kyeongho.dto.MemberTeamDto;
import me.kyeongho.entity.Member;
import me.kyeongho.entity.Team;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

	@Autowired
	EntityManager em;
	
	@Autowired 
	MemberRepository memberRepository;
	
	@Test
	public void basicTest() {
		Member member = new Member("member1", 10);
		
		memberRepository.save(member);
		
		Optional<Member> findMember = memberRepository.findById(member.getId());
		
		assertThat(member).isEqualTo(findMember.get());
		
		List<Member> result2 = memberRepository.findAll();
		
		assertThat(result2).containsExactly(member);
	}
	
	@Test
	public void basicQueryDslTest() {
		Member member = new Member("member1", 10);
		
		memberRepository.save(member);
		
		Optional<Member> findMember = memberRepository.findById(member.getId());
		
		assertThat(member).isEqualTo(findMember.get());
		
		List<Member> result1 = memberRepository.findAll();
		
		assertThat(result1).containsExactly(member);
		
		List<Member> result2 = memberRepository.findByUsername("member1");
		
		assertThat(result2).containsExactly(member);
		
	}
	
	@Test
	public void searchWhereTest() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 14, teamB);
		Member member3 = new Member("member3", 13, teamA);
		Member member4 = new Member("member4", 15, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
		
		MemberSearchCondition condition = new MemberSearchCondition();
		condition.setAgeGoe(14);
		condition.setAgeLoe(15);
		condition.setTeamName("teamB");
		
		List<MemberTeamDto> result = memberRepository.search(condition);
		
		assertThat(result.size()).isEqualTo(2);
	}
	
	@Test
	public void searchPageSimple() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 14, teamB);
		Member member3 = new Member("member3", 13, teamA);
		Member member4 = new Member("member4", 15, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
		
		MemberSearchCondition condition = new MemberSearchCondition();
		PageRequest pageRequest = PageRequest.of(0, 3);
		
		Page<MemberTeamDto> result = memberRepository.searchSimple(condition, pageRequest);
		
		assertThat(result.getSize()).isEqualTo(3);
		assertThat(result.getContent()).extracting("username").containsExactly("member1", "member2", "member3");
	}
}
