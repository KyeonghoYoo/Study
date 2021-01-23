package me.kyeongho;

import static com.querydsl.jpa.JPAExpressions.select;
import static me.kyeongho.entity.QMember.member;
import static me.kyeongho.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import me.kyeongho.entity.Member;
import me.kyeongho.entity.QMember;
import me.kyeongho.entity.Team;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {

	@Autowired
	EntityManager em;
	
	JPAQueryFactory queryFactory;
	
	@BeforeEach
	public void init() {
		queryFactory = new JPAQueryFactory(em);
		
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 14, teamB);
		Member member3 = new Member("member3", 13, teamA);
		Member member4 = new Member("member4", 15, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
	}
	
	@Test
	public void startJPQL() {
		// memeber1을 찾아라
		Member result = em.createQuery(
				"select m from Member m " + 
				"where m.username = :username", Member.class)
				.setParameter("username", "member1")
				.getSingleResult();
		
		assertThat(result.getUsername()).isEqualTo("member1");
	}
	
	@Test
	public void startQueryDsl() {
		// memeber1을 찾아라
		Member result = queryFactory
				.select(member)
				.from(member)
				.where(member.username.eq("member1"))
				.fetchOne();
		
		assertThat(result.getUsername()).isEqualTo("member1");
	}
	
	//// 검색 기능
	@Test
	public void search() {
		Member result = queryFactory
				.selectFrom(member)
				.where(member.username.eq("member1")
						.and(member.age.eq(10)))
				.fetchOne();
		
		assertThat(result.getUsername()).isEqualTo("member1");
	}
	
	@Test
	public void searchAndParam() {
		Member result = queryFactory
				.selectFrom(member)
				.where( // and만 있는 경우엔 ,로 넣어도 된다.
						member.username.eq("member1"),
						member.age.eq(10)
				)
				.fetchOne();
		
		assertThat(result.getUsername()).isEqualTo("member1");
	}
	
	//// 결과 조회 기능
	@Test
	public void getResult() {
		// List로 조회
		List<Member> result1 = queryFactory
				.selectFrom(member)
				.fetch();
		// 단건 조회
		Member result2 = queryFactory
				.selectFrom(member)
				.where(member.username.eq("member1"))
				.fetchOne();
		// == (...).limit(1).fetchOne()
		Member result3 = queryFactory
				.selectFrom(member)
				.fetchFirst();
		// 페이징 정보랑 같이줌
		QueryResults<Member> result4 = queryFactory
				.selectFrom(member)
				.fetchResults();
		result4.getTotal();
		List<Member> result4List = result4.getResults();
		// 토탈 카운트 반환
		long result5 = queryFactory
				.selectFrom(member)
				.fetchCount();
	}
	//// 정렬
	@Test
	public void sort() {
		/**
		 * 회원 정렬 순서
		 * 1. 회원 나이 내림차순(desc)
		 * 2. 회원 이름 올림차순 (asc)
		 * 단, 2에서 회원 이름이 없으면 마지막에 출력 (nulls last)
		 */
		em.persist(new Member(null, 100));
		em.persist(new Member("member5", 100));
		em.persist(new Member("member6", 100));
		
		List<Member> result = queryFactory
				.selectFrom(member)
				.orderBy(member.age.desc(), member.username.asc().nullsLast())
				.fetch();
		
		assertThat(result.get(0).getUsername()).isEqualTo("member5");
		assertThat(result.get(1).getUsername()).isEqualTo("member6");
		assertThat(result.get(2).getUsername()).isNull();
	}
	
	//// 페이징
	@Test
	public void paging() {
		List<Member> result = queryFactory
				.selectFrom(member)
				.orderBy(member.username.desc())
				.offset(1)
				.limit(2)
				.fetch();
		
		assertThat(result.size()).isEqualTo(2);

	}
	
	// 페이징 쿼리가 단순하면 아래처럼 사용할 수 있다.
	// 쿼리가 복잡한 경우, 카운터 쿼리를 따로 짜야 되는 경우에 분리해서 사용한다.
	@Test
	public void paging2() {
		QueryResults<Member> result = queryFactory
				.selectFrom(member)
				.orderBy(member.username.desc())
				.offset(1)
				.limit(2)
				.fetchResults();
		
		assertThat(result.getTotal()).isEqualTo(4);
		assertThat(result.getLimit()).isEqualTo(2);
		assertThat(result.getOffset()).isEqualTo(1);
		assertThat(result.getResults().size()).isEqualTo(2);
	}
	
	//// 집합 함수
	// 실무에서는 Tuple을 사용하기 보다는 DTO로 직접 뽑아내는 방법을 많이 쓴다.
	@Test
	public void aggregation() {
		List<Tuple> result = queryFactory
				.select(
						member.count(),
						member.age.sum(),
						member.age.avg(),
						member.age.max(),
						member.age.min()
						)
				.from(member)
				.fetch();
		Tuple tuple = result.get(0);
		
		assertThat(tuple.get(member.count())).isEqualTo(4);
		assertThat(tuple.get(member.age.sum())).isEqualTo(52);
		assertThat(tuple.get(member.age.avg())).isEqualTo(13);
		assertThat(tuple.get(member.age.max())).isEqualTo(15);
		assertThat(tuple.get(member.age.min())).isEqualTo(10);
	}
	/**
	 * 팀의 이름과 각 팀의 평균 연령을 구해라.
	 */
	@Test
	public void group() {
		List<Tuple> result = queryFactory
				.select(team.name, member.age.avg())
				.from(member)
				.join(member.team, team)
				.groupBy(team.name)
				.fetch();
		
		Tuple teamA = result.get(0);
		Tuple teamB = result.get(1);
		
		assertThat(teamA.get(team.name)).isEqualTo("teamA");
		assertThat(teamB.get(team.name)).isEqualTo("teamB");
	}
	
	//// 조인
	/**
	 * 팀 A에 소속된 모든 회원
	 */
	@Test
	public void join() {
		List<Member> result = queryFactory
				.selectFrom(member)
				.leftJoin(member.team, team)
				.where(team.name.eq("teamA"))
				.fetch();
		
		assertThat(result)
				.extracting("username")
				.containsExactly("member1", "member3");
	}
	/**
	 * 세타 조인
	 * 회원의 이름 팀 이름과 같은 회원 조회 (막 조인)
	 */
	@Test
	public void theta_join() {
		em.persist(new Member("teamA"));
		em.persist(new Member("teamB"));
		em.persist(new Member("teamC"));
		
		List<Member> result = queryFactory
				.select(member)
				.from(member, team)
				.where(member.username.eq(team.name))
				.fetch();
		
		assertThat(result)
				.extracting("username")
				.containsExactly("teamA", "teamB");
	}
	// 조인 on 절 활용 (JPA 2.1부터 지원)
	/**
	 * 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
	 * JPQL : select m, t from Member m left join m.team t on t.name = 'teamA'
	 */
	@Test
	public void join_on_filtering() {
		List<Tuple> result = queryFactory
				.select(member, team)
				.from(member)
				.leftJoin(member.team, team) // 이런식으로 하면 FK를 대조하여 조인 on member0_.team_id=team1_.team_id
				.on(team.name.eq("teamA"))
				.fetch();
		for (Tuple tuple : result) {
			Member member = tuple.get(0, Member.class);
			Team team = tuple.get(1, Team.class);
			System.out.println(member);
			System.out.println(team);
		}
	}
	/**
	 * 연관관계가 없는 엔티티를 외부조인
	 * 회원의 이름이 팀 이름과 같은 대상 외부 조인
	 */
	@Test
	public void theta_on_no_relation() {
		em.persist(new Member("teamA"));
		em.persist(new Member("teamB"));
		em.persist(new Member("teamC"));
		
		List<Tuple> result = queryFactory
				.select(member, team) // 이런식으로 하면 FK를 대조하지 않고 조인함 member.team_id와 team.team_id를 대조하지 않음
				.from(member)
				.leftJoin(team).on(member.username.eq(team.name))
				.fetch();
		
		for (Tuple tuple : result) {
			Member member = tuple.get(0, Member.class);
			Team team = tuple.get(1, Team.class);
			System.out.println(member);
			System.out.println(team);
		}
	}
	
	@PersistenceUnit
	EntityManagerFactory emf;
	
	// 페치 조인
	@Test
	public void fetchJoinNo() {
		em.flush();
		em.clear();
		Member result = queryFactory
				.selectFrom(member)
				.join(member.team, team).fetchJoin()
				.where(member.username.eq("member1"))
				.fetchOne();
		
		boolean loaded = emf.getPersistenceUnitUtil().isLoaded(result.getTeam());
		
		assertThat(loaded).as("페치 조인 적용").isTrue();
	}
	
	//// 서브 쿼리 
	/**
	 * 나이가 가장 많은 회원 조회
	 */
	@Test
	public void subQeury() {
		QMember memberSub = new QMember("memberSub");
		
		Member result = queryFactory
				.selectFrom(member)
				.where(member.age.eq(
						JPAExpressions
								.select(memberSub.age.max())
								.from(memberSub))
						)
				.fetchOne();
		
		assertThat(result.getUsername()).isEqualTo("member4");
		
	}
	
	/**
	 * 나이가 평균 이상인 회원 조회
	 */
	@Test
	public void subQeuryGoe() {
		QMember memberSub = new QMember("memberSub");
		
		List<Member> result = queryFactory
				.selectFrom(member)
				.where(member.age.goe(
						JPAExpressions
								.select(memberSub.age.avg())
								.from(memberSub))
						)
				.orderBy(member.age.asc())
				.fetch();
		
		assertThat(result).extracting("age")
				.containsExactly(13, 14, 15);
		
	}
	
	/**
	 * 나이가 평균 이상인 회원 조회
	 */
	@Test
	public void subQeuryIn() {
		QMember memberSub = new QMember("memberSub");
		
		List<Member> result = queryFactory
				.selectFrom(member)
				.where(member.age.in(
						JPAExpressions
								.select(memberSub.age)
								.from(memberSub)
								.where(memberSub.age.gt(13))))
				.orderBy(member.age.asc())
				.fetch();
		
		assertThat(result).extracting("age")
				.containsExactly(14, 15);
	}
	
	@Test
	public void selectSubQeury() {
		QMember memberSub = new QMember("memberSub");
		
		List<Tuple> result = queryFactory
				.select(member.username, 
							select(memberSub.age.avg())
							.from(memberSub))
				.from(member)
				.fetch();
		
	}
	//// case
	// 심플 case
	@Test
	public void basicCase() {
		List<String> result = queryFactory
				.select(member.age
							.when(10).then("열살")
							.when(15).then("열다섯살")
							.otherwise("기타"))
				.from(member)
				.fetch();
		
		for (String string : result) {
			System.out.println("s = " + string);
		}
	}
	
	// 복잡한 case
	@Test
	public void complexCase() {
		List<String> result = queryFactory
				.select(new CaseBuilder()
							.when(member.age.between(0, 13)).then("0 ~ 13살")
							.when(member.age.between(14, 15)).then("14 ~ 15살")
							.otherwise("기타"))
				.from(member)
				.fetch();
		
		for (String string : result) {
			System.out.println("s = " + string);
		}
	}
	
	//// 상수, 문자 더하기
	@Test
	public void constant() {
		List<Tuple> result = queryFactory
				.select(member.username, Expressions.constant("A"))
				.from(member)
				.fetch();
		for (Tuple tuple : result) {
			System.out.println("Tuple = " + tuple);
		}
	}
	
	@Test
	public void concat() {
		// {username}_{age}
		List<String> result = queryFactory
				.select(member.username.concat("_").concat(member.age.stringValue()))
				.from(member)
				.fetch();
		for (String s : result) {
			System.out.println("s = " + s);
		}
	}
	
	////// 중급 문법
	// Projection
	@Test
	public void simpleProjection() {
		List<String> result = queryFactory
				.select(member.username)
				.from(member)
				.fetch();
	}
	
	@Test
	public void tupleProjection() {
		List<Tuple> result = queryFactory
				.select(member.username, member.age)
				.from(member)
				.fetch();
		
		for (Tuple tuple : result) {
			String username = tuple.get(member.username);
			Integer age = tuple.get(member.age);
			System.out.println("username = " + username);
			System.out.println("age = " + age);
		}
	}
}
