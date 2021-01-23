package me.kyeongho.entity;

import static me.kyeongho.entity.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class MemberTest {

	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testEntity() {
		// given
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
		
		// when
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		List<Member> result = query.selectFrom(member).fetch();
		
		result.forEach(e -> {
			log.info("member = " + e);
			log.info("member.team = " + e.getTeam());
		});
		
		// then
		assertThat(member1).isIn(result);
		assertThat(member2).isIn(result);
		assertThat(member3).isIn(result);
		assertThat(member4).isIn(result);
		
	}
}
