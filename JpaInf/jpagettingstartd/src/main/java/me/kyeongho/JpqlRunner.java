package me.kyeongho;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JpqlRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setUsername("Hello");
			Member member2 = new Member();
			member2.setUsername("Hi");
			// 저장
			em.persist(member);
			em.persist(member2);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {

		}
		
		tx.begin();
		try {
			List<Member> result = em.createQuery("select m from Member as m", Member.class)
					.getResultList();
			
			for (Member e: result) {
				System.out.println("memeber.name: " + e.getUsername());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
