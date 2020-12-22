package me.kyeongho;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ApplicationTest {

	// 동일성 비교 Test
	@Test
	@Order(1)
	public void test1() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Member member = new Member();
		member.setName("Kyeongho");

		// 저장
		em.persist(member);

		tx.commit();
		
		tx.begin();
		
		Member a = em.find(Member.class, "Kyeongho");
		Member b = em.find(Member.class, "Kyeongho");

		assertThat(a == b).isTrue(); //동일성 비교 true
		
		tx.commit();
		
		em.close();

	}

	// 쓰기 지연
	@Test
	@Order(2)
	public void test2() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Member member1 = new Member();
			member1.setName("Kyeongho1");
			Member member2 = new Member();
			member2.setName("Kyeongho2");
			
			// 저장
			em.persist(member1);
			System.out.println("1------");
			em.persist(member2);
			System.out.println("2------");
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	// 변경 감지
	@Test
	@Order(3)
	public void test3() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Member member = em.find(Member.class, "Kyeongho");

			member.setName("ModifiedKyeongho");
			System.out.println("-------");
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
