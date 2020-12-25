package me.kyeongho;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaRunner implements ApplicationRunner {

	@Autowired
	DataSource dataSource;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try (Connection connection = dataSource.getConnection()) {
			// 컨넥션 테스트
			System.out.println(connection.getMetaData().getURL());
			System.out.println(connection.getMetaData().getUserName());
		}
		Long id = null;
		// Unit Name을 넣어서 생성 EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		// 한 트랜잭션에서 실행되는 작업을 시작할 때마다 EntityManager를 반드시 만들어서 사용해줘야함
		EntityManager em = emf.createEntityManager();
		// EntityManager로 부터 트랜잭션을 받아와 사용할 수 있다.
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setUsername("Kyeongho");
			
			// 저장
			em.persist(member);
			
			id = member.getId();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		tx2.begin();
		
		try {
			
			// 조회
			Member member2 = em2.find(Member.class, id);
			System.out.println("memeber.id:" + member2.getId());
			System.out.println("member.name: " + member2.getUsername());
			
			// 수정
			member2.setUsername("Hello Kyeongho");
			
			//삭제
			em2.remove(member2);
			tx2.commit();
		} catch (Exception e) {
			tx2.rollback();
		} finally {
			em2.close();
		}
		
		emf.close();
	}
}
