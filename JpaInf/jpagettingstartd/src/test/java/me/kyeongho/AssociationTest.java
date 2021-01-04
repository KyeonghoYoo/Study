package me.kyeongho;

import java.util.List;

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
public class AssociationTest {

	@Test
	@Order(1)
	public void uniDirectionAssociationTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// 팀 엔티티 생성
		Team team = new Team();
		team.setName("TeamA");
		
		em.persist(team);
		
		// 회원 엔티티 생성
		Member member = new Member();
		member.setUsername("Kyeongho");
		
		// 연관관계 설정, 참조 저장
		member.setTeam(team);

		em.persist(member);
		
		tx.commit();
		
		em.close();
		emf.close();
	}


	@Test
	@Order(2)
	public void biDirectionAssociationTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		// 팀 엔티티 생성
		Team team = new Team();
		team.setName("TeamA");
		
		em.persist(team);
		
		// 회원 엔티티 생성
		Member member = new Member();
		member.setUsername("Kyeongho");
		// 연관관계 설정, 참조 저장
		member.setTeam(team);
		
		em.persist(member);
		
		em.flush();
		em.clear();
		
		Member findMember = em.find(Member.class, member.getId());
		List<Member> members = findMember.getTeam().getMembers(); // 참조로 연관관계 조회 - 객체 그래프 탐색
		
		for (Member m : members) {
			System.out.println("m = " + m.getUsername());
		}
		tx.commit();

		em.close();
		emf.close();
	}
}
