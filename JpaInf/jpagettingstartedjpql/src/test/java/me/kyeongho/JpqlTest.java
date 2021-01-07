package me.kyeongho;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import me.kyeongho.domain.Member;
import me.kyeongho.domain.Team;

@DataJdbcTest
public class JpqlTest {

//	@Test
	public void criteriaTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Member newMember = Member.builder().name("Kyeongho").build();
			
			em.persist(newMember);
			
			em.flush();
			em.clear();
			
			// Criteria 사용 준비
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Member> query = cb.createQuery(Member.class);
			
			Root<Member> m = query.from(Member.class);
			
			CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "Kyeongho"));
			
			List<Member> resultList = em.createQuery(cq).getResultList();
			
			for (Member member : resultList) {
				System.out.println("name = " + member.getName());
			}
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();;
		}
		emf.close();
	}
	
	@Test
	public void joinTest() {
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
		
			List<Member> results = em.createQuery("select m from Member as m left outer join m.team t", Member.class)
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
