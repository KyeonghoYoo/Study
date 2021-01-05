package me.kyeongho;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import me.kyeongho.domain.Member;
import me.kyeongho.domain.valuetype.Address;

@Component
public class JpaRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		System.out.println("트랜잭션 시작");
		tx.begin();

		try {
			Member member = new Member();
			member.setName("Kyeongho");
			
			member.setAddress(Address.builder().city("city").street("street").zipcode("zipcode").build());
			
//			member.setPeriod(Period.builder()
//								.startDate(LocalDateTime.now())
//								.endDate(LocalDateTime.now().plusDays(30L))
//								.build());

			em.persist(member);

			em.flush();
			em.clear();

			Member a = em.find(Member.class, 1L);

			System.out.println("member = " + a.toString());

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		
		emf.close();
		System.out.println("Runner 종료");
	}
}
