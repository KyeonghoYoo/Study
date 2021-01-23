package me.kyeongho.repository;

import static me.kyeongho.entity.QMember.member;
import static me.kyeongho.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.util.StringUtils.isEmpty;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.kyeongho.dto.MemberSearchCondition;
import me.kyeongho.dto.MemberTeamDto;
import me.kyeongho.dto.QMemberTeamDto;
import me.kyeongho.entity.Member;
import me.kyeongho.entity.QTeam;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {
	
	private final EntityManager em;
	private final JPAQueryFactory queryFactory;
	
	public void save(Member member) {
		em.persist(member);
	}
	
	public Optional<Member> findById(Long id) {
		Member findMember = em.find(Member.class, id);
		return Optional.ofNullable(findMember);
	}
	
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
					.getResultList();
	}
	
	public List<Member> findAll_QueryDsl() {
		return queryFactory
						.selectFrom(member)
						.fetch();
	}
	
	public List<Member> findByUsername(String username) {
		return em.createQuery("select m from Member m where m.username = :username", Member.class)
					.setParameter("username", username)
					.getResultList();
	}
	
	public List<Member> findByUsername_QueryDsl(String username) {
		return queryFactory
						.selectFrom(member)
						.where(member.username.eq(username))
						.fetch();
	}
	
	public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition) {
		BooleanBuilder builder = new BooleanBuilder();
		if(StringUtils.hasText(condition.getUsername())) {
			builder.and(member.username.eq(condition.getUsername()));
		}
		if(StringUtils.hasText(condition.getTeamNaem())) {
			builder.and(team.name.eq(condition.getTeamNaem()));
		}
		if(condition.getAgeGoe() != null) {
			builder.and(member.age.goe(condition.getAgeGoe()));
		}
		if(condition.getAgeLoe() != null) {
			builder.and(member.age.loe(condition.getAgeLoe()));
		}
		
		
		return queryFactory
				.select(new QMemberTeamDto(
						member.id.as("memberId"),
						member.username, 
						member.age,
						team.id.as("teamId"), 
						team.name.as("teamName")
						))
				.from(member)
				.leftJoin(member.team, team)
				.where(builder)
				.fetch();
	}
	
	public List<MemberTeamDto> search(MemberSearchCondition condition) {
		return queryFactory
				.select(new QMemberTeamDto(
						member.id.as("memberId"),
						member.username, 
						member.age,
						team.id.as("teamId"), 
						team.name.as("teamName")
						))
				.from(member)
				.leftJoin(member.team, team)
				.where(usernameEq(condition.getUsername()),
						teamNameEq(condition.getTeamNaem()),
						ageGoe(condition.getAgeGoe()),
						ageLoe(condition.getAgeLoe()))
				.fetch();
	}

	private BooleanExpression ageLoe(Integer ageLoe) {
		return ageLoe != null ? member.age.loe(ageLoe) : null;
	}

	private BooleanExpression ageGoe(Integer ageGoe) {
		return ageGoe != null ? member.age.goe(ageGoe) : null;
	}

	private BooleanExpression teamNameEq(String teamNaem) {
		return hasText(teamNaem) ? team.name.eq(teamNaem) : null;
	}

	private BooleanExpression usernameEq(String username) {
		return hasText(username) ? member.username.eq(username) : null;
	}
}
