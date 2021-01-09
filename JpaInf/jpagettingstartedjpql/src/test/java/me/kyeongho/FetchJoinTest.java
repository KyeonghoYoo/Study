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
public class FetchJoinTest {

	@Test
	public void collectionFetchJoinTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Team teamA = Team.builder().name("teamA").build();
			Team teamB = Team.builder().name("teamB").build();
			
			em.persist(teamA);
			em.persist(teamB);
			
			Member member1 = Member.builder().name("회원1").build();
			Member member2 = Member.builder().name("회원2").build();
			Member member3 = Member.builder().name("회원3").build();
			
			member1.setTeam(teamA);
			member2.setTeam(teamA);
			member3.setTeam(teamB);

			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			
			em.flush();
			em.clear();
		
			String query = "select m from Member as m join fetch m.team";
			
			List<Member> results = em.createQuery(query, Member.class)
					.getResultList();
			
			
			for (Member member : results) {
				System.out.println("name = " + member.getName() + ", team = " + member.getTeam().getName());
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
	
	@Test
	public void collectionFetchJoinTest2() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Team teamA = Team.builder().name("teamA").build();
			Team teamB = Team.builder().name("teamB").build();
			
			em.persist(teamA);
			em.persist(teamB);
			
			Member member1 = Member.builder().name("회원1").build();
			Member member2 = Member.builder().name("회원2").build();
			Member member3 = Member.builder().name("회원3").build();
			
			member1.setTeam(teamA);
			member2.setTeam(teamA);
			member3.setTeam(teamB);

			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			
			em.flush();
			em.clear();
		
			String query = "select t from Team as t join fetch t.members";
			
			List<Team> results = em.createQuery(query, Team.class)
					.getResultList();
			 
			
			for (Team e : results) {
				System.out.print("team = " + e.getName() + "(" + e.hashCode() + ")");
				for (Member member : e.getMembers()) {
					System.out.print(", member = " + member.getName() + "(" + member.hashCode() + ")");
				}
				System.out.println();
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
