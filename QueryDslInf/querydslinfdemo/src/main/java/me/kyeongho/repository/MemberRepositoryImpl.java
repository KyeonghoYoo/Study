package me.kyeongho.repository;

import static me.kyeongho.entity.QMember.member;
import static me.kyeongho.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.kyeongho.dto.MemberSearchCondition;
import me.kyeongho.dto.MemberTeamDto;
import me.kyeongho.dto.QMemberTeamDto;
import me.kyeongho.entity.Member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	@Override
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
						teamNameEq(condition.getTeamName()),
						ageGoe(condition.getAgeGoe()),
						ageLoe(condition.getAgeLoe()))
				.fetch();
	}

	@Override
	public Page<MemberTeamDto> searchSimple(MemberSearchCondition condition, Pageable pageable) {
		QueryResults<MemberTeamDto> results = queryFactory
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
						teamNameEq(condition.getTeamName()),
						ageGoe(condition.getAgeGoe()),
						ageLoe(condition.getAgeLoe()))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		List<MemberTeamDto> content = results.getResults();
		long total = results.getTotal();
		
		return new PageImpl<>(content, pageable, total);
		
	}

	// 카운트 쿼리 분리해야할 때
	@Override
	public Page<MemberTeamDto> searchComplex(MemberSearchCondition condition, Pageable pageable) {
		List<MemberTeamDto> content = queryFactory
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
						teamNameEq(condition.getTeamName()),
						ageGoe(condition.getAgeGoe()),
						ageLoe(condition.getAgeLoe()))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch(); // fetchResults()가 아니라 fetch()한다.
		
		JPAQuery<Member> countQuery = queryFactory
				.select(member)
				.from(member)
				.leftJoin(member.team, team)
				.where(usernameEq(condition.getUsername()),
						teamNameEq(condition.getTeamName()),
						ageGoe(condition.getAgeGoe()),
						ageLoe(condition.getAgeLoe()));
		
		
		
//		return new PageImpl<>(content, pageable, total);
		
//		첫번째 count 쿼리가 생략 가능한 경우 생략해서 처리
//		페이지 시작이면서 컨텐츠 사이즈가 페이지 사이즈보다 작을 때
//		마지막 페이지 일 때 (offset + 컨텐츠 사이즈를 더해서 전체 사이즈 구함)
		return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
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
