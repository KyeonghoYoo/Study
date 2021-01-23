package me.kyeongho.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import me.kyeongho.dto.MemberSearchCondition;
import me.kyeongho.dto.MemberTeamDto;
import me.kyeongho.entity.Member;
import me.kyeongho.entity.Team;

@SpringBootTest
@Transactional
public class MemberJpaRepositoryTest {

	@Autowired
	EntityManager em;
	
	@Autowired 
	MemberJpaRepository memberJpaRepository;
	
	@Test
	public void basicTest() {
		Member member = new Member("member1", 10);
		
		memberJpaRepository.save(member);
		
		Optional<Member> findMember = memberJpaRepository.findById(member.getId());
		
		assertThat(member).isEqualTo(findMember.get());
		
		List<Member> result2 = memberJpaRepository.findAll();
		
		assertThat(result2).containsExactly(member);
	}
	
	@Test
	public void basicQueryDslTest() {
		Member member = new Member("member1", 10);
		
		memberJpaRepository.save(member);
		
		Optional<Member> findMember = memberJpaRepository.findById(member.getId());
		
		assertThat(member).isEqualTo(findMember.get());
		
		List<Member> result1 = memberJpaRepository.findAll_QueryDsl();
		
		assertThat(result1).containsExactly(member);
		
		List<Member> result2 = memberJpaRepository.findByUsername_QueryDsl("member1");
		
		assertThat(result2).containsExactly(member);
		
	}
	
	// 검색 시 검색 조건이 없을 때 모든 데이터를 반환할 때가 있다. 모든 데이터가 조회되면 성능에 나쁜 영향을 끼칠 수 있으니
	// 기본적으로 페이징 처리를 넣도록 하여 나쁜 영향을 끼치는 것을 방지하는 걸 권장한다.
	@Test
	public void searchTest() {
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
		condition.setTeamNaem("teamB");
		
		List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);
		
		assertThat(result.size()).isEqualTo(2);
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
		condition.setTeamNaem("teamB");
		
		List<MemberTeamDto> result = memberJpaRepository.search(condition);
		
		assertThat(result.size()).isEqualTo(2);
	}
}
