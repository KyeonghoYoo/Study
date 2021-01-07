package me.kyeongho;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import me.kyeongho.domain.Member;

@DataJdbcTest
public class PagingTest {
	
	@Test
	void pagingTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			for(int i = 0; i < 100; i++) {
				Member newMember = Member.builder().name("Kyeongho" + i).age(i).build();
				
				em.persist(newMember);
					
			}
			
			em.flush();
			em.clear();
		
			List<Member> results = em.createQuery("select m from Member as m order by m.age desc", Member.class)
					.setFirstResult(0)
					.setMaxResults(10)
					.getResultList();
			
			System.out.println("results.size = " + results.size());
			for (Member member : results) {
				System.out.println("name = " + member);
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
