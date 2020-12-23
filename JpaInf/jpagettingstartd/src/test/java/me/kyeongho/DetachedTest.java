package me.kyeongho;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJdbcTest
public class DetachedTest {

	@Test
	@Order(1)
	public void deteachedTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// 회원 엔티티 생성, 비영속(new/transient) 상태
		Member member = new Member();
		member.setName("Kyeongho");

		// 회원 엔티티 영속(managed) 상태
		em.persist(member);
		
		// 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속(detached) 상태
		em.detach(member);

		tx.commit();
		
		em.close();
	}


	@Test
	@Order(2)
	public void clearTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();

		Member member1 = new Member();
		member1.setName("Kyeongho");

		// 새 엔티티 디비에 저장
		em.persist(member1);
		em.flush();
		
		// 엔티티 조회, 영속 상태
		Member member2 = em.find(Member.class, 1L);
		
		em.clear(); // 영속성 컨텍스트 초기화
		
		// 준영속 상태
		member2.setName("ModifiedName");
		
		tx.commit();

		em.close();
	}


	@Test
	@Order(3)
	public void mergeTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// 비영속 상태
		Member member1 = new Member();
		member1.setName("Kyeongho");
		
		// 영속 상태
		em.persist(member1);
		
		// 준영속 상태
		em.detach(member1);
		
		// 병합, 영속 상태
		Member mergeMember = em.merge(member1);
		
		System.out.println("em.contains(member1) = " + em.contains(member1));
		System.out.println("em.contains(mergeMember) = " + em.contains(mergeMember));
		
		
		tx.commit();

		em.close();
	}
}
