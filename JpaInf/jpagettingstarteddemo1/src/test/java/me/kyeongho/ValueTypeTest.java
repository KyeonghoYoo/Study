package me.kyeongho;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import me.kyeongho.domain.Member;
import me.kyeongho.domain.valuetype.Address;

@DataJdbcTest
public class ValueTypeTest {
	
	@Test
	public void test() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Member member = new Member();
		member.setName("Kyeongho");
		
		member.setAddress(Address.builder().city("city").street("street").zipcode("zipcode").build());
		
		em.persist(member);
		
		em.flush();
		em.clear();

		Member a = em.find(Member.class, 1L);
		
		System.out.println("member = " + a.toString());

		tx.commit();

		em.close();
	}
	
}
