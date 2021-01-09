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
public class BulkOperationTest {

	
	@Test
	public void operatingBulk() {
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

			String query = "update Member m set m.age = 20";
			
			int resultCount = em.createQuery(query)
					.executeUpdate();
			
			System.out.println("resultCount = " + resultCount);
			
			System.out.println("=== before refreshing ===");
			
			System.out.println("member1.age = " + member1.getAge());
			System.out.println("member2.age = " + member2.getAge());
			System.out.println("member3.age = " + member3.getAge());
			
			em.refresh(member1);
			em.refresh(member2);
			em.refresh(member3);
			
			System.out.println("=== after refreshing ===");
			
			System.out.println("member1.age = " + member1.getAge());
			System.out.println("member2.age = " + member2.getAge());
			System.out.println("member3.age = " + member3.getAge());
			
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
