package me.kyeongho;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import me.kyeongho.domain.Member;
import me.kyeongho.domain.Team;

@Component
public class JpqlRunner implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Member newMember = Member.builder().name("Kyeongho").build();
			
			Team newTeam = Team.builder().name("teamA").build();
			
			newMember.setTeam(newTeam);
			
			em.persist(newTeam);
			
			em.persist(newMember);
			
			
			em.flush();
			em.clear();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.commit();
			em.close();
		}
		emf.close();
	}

}
