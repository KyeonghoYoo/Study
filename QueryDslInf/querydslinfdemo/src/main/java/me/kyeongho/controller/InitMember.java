package me.kyeongho.controller;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.kyeongho.entity.Member;
import me.kyeongho.entity.Team;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

	private final InitMemberService initMemberService;
	
	// @PostConstruct와 @Transactional이 Spring내에서 같이 작동될 수 없도록 돼있음. 하여 분리함.
	@PostConstruct
	public void init() {
		initMemberService.init();
	}
	
	@Component
	static class InitMemberService {
		@PersistenceContext
		private EntityManager em;
		
		@Transactional
		public void init() {
			Team teamA = new Team("teamA");
			Team teamB = new Team("teamB");
			em.persist(teamA);
			em.persist(teamB);
			
			for(int i = 0; i < 100; i++) {
				Team selectedTeam = i % 2 == 0 ? teamA : teamB;
				Member member = new Member("member" + i, i, selectedTeam);
				em.persist(member);
			}
		}
	}
}
