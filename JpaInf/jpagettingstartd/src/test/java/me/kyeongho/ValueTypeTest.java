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
public class ValueTypeTest {

	@Test
	@Order(1)
	public void valueTypeCollectionTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// 회원 엔티티 생성, 비영속(new/transient) 상태
		Member member = new Member();
		member.setUsername("Kyeongho");
		
		member.setAddress(new Address("city1", "street1", "zipcode1"));
		
		member.getAddressHistory().add(new Address("city2", "street2", "zipcode2"));
		member.getAddressHistory().add(new Address("city3", "street3", "zipcode3"));
		member.getAddressHistory().add(new Address("city4", "street4", "zipcode4"));
		
		em.persist(member);
		
		em.flush();
		em.clear();

		Member refMember = em.getReference(Member.class, member.getId());

		System.out.println("refMember = " + refMember.getClass().getName());
		
		System.out.println("refMember.getUsername() = " + refMember.getUsername());
		
		
		tx.commit();
		
		em.close();
	}

}
