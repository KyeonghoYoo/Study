package me.kyeongho;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import me.kyeongho.domain.Member;
import me.kyeongho.domain.Team;

@DataJdbcTest
public class PathExperssionTest {

	@Test
	public void associationFieldTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Member newMember = Member.builder().name("Kyeongho").build();
			
			Team team = Team.builder().name("teamA").build(); 
			em.persist(team);

			newMember.setTeam(team);
			
			em.persist(newMember);
			
			em.flush();
			em.clear();
		
			// FROM 절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 가능
			String query = "select m from Team as t join t.members m";
			
			List<Member> results = em.createQuery(query, Member.class)
					.getResultList();
			
			
			for (Member member : results) {
				System.out.println("name = " + member.getName());
			}
			
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
