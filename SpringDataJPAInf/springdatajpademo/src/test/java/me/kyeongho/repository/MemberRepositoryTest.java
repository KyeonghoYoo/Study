package me.kyeongho.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import com.sun.el.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import me.kyeongho.dto.MemberDto;
import me.kyeongho.entity.Member;
import me.kyeongho.entity.Team;

@Slf4j
@SpringBootTest
@Transactional
public class MemberRepositoryTest {
	
	@Autowired MemberRepository memberRepository;
	@Autowired TeamRepository teamRepository;
	@PersistenceContext EntityManager em;
	
	@Test
	public void testMember() {
		log.info("memberRepository = " + memberRepository.getClass());
		
		Member member = new Member("memberA");
		
		Member savedMember = memberRepository.save(member);
		
		Optional<Member> findMember = memberRepository.findById(savedMember.getId());
		
		assertThat(findMember.get().getId()).isEqualTo(member.getId());
		assertThat(findMember.get().getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember.get()).isEqualTo(member);
	}
	
	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		
		Member savedMember1 = memberRepository.save(member1);
		Member savedMember2 = memberRepository.save(member2);
		
		// 단건 조회
		Optional<Member> findMember1 = memberRepository.findById(member1.getId());
		Optional<Member> findMember2 = memberRepository.findById(member2.getId());
		assertThat(findMember1.get()).isEqualTo(member1);
		assertThat(findMember2.get()).isEqualTo(member2);
		
		// 커밋 시점에 변경 감지(Dirty Checking)에 의한 변경이 일어난다.
		findMember1.get().setUsername("modifedMember1");
		
		// 리스트 조회
		List<Member> all = memberRepository.findAll();
		assertThat(all.size()).isEqualTo(2);
		
		// 카운트 검증
		long count = memberRepository.count();
		assertThat(count).isEqualTo(2);
		
		// 삭제 검증
		memberRepository.delete(member1);
		memberRepository.delete(member2);
		
		long count2 = memberRepository.count();
		assertThat(count2).isEqualTo(0);
	}
	
	@Test
	public void findByUsernameAndAgeGreaterThen() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("aaa", 20);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		List<Member> findMembers = memberRepository.findByUsernameAndAgeGreaterThan("aaa", 15);
		
		findMembers.forEach(m -> log.info("member = " + m));
		
		assertThat(findMembers.size()).isEqualTo(1);
		assertThat(findMembers.get(0).getAge()).isEqualTo(20);
	}
	
	
	@Test
	public void namedQueryTest() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("bbb", 20);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		List<Member> findByUsername = memberRepository.findByUsername("aaa");
		Member findMember = findByUsername.get(0);
		
		assertThat(findMember).isEqualTo(member1);
	}
	
	@Test
	public void queryTest() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("bbb", 20);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		List<Member> findByUsername = memberRepository.findUser("aaa", 10);
		Member findMember = findByUsername.get(0);
		
		assertThat(findMember).isEqualTo(member1);
	}
	
	@Test
	public void findUsernameListTest() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("bbb", 20);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		List<String> findUsernameList = memberRepository.findUsernameList();
		
		findUsernameList.forEach(un -> log.info("username = " + un));
		
		assertThat(member1.getUsername()).isIn(findUsernameList);
		assertThat(member2.getUsername()).isIn(findUsernameList);
	}
	
	@Test
	public void findMemberDto() {
		Team team = new Team("teamA");
		
		teamRepository.save(team);
		
		Member member1 = new Member("aaa", 10);
		member1.setTeam(team);
		
		memberRepository.save(member1);
		
		List<MemberDto> findMemberDto = memberRepository.findMemberDto();
		
		findMemberDto.forEach(e -> System.out.println(e));
		
		assertThat(member1.getTeam().getName()).isIn(findMemberDto.get(0).getTeamName());
	}
	
	@Test
	public void findByNames() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("bbb", 20);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		List<Member> findUsernameList = memberRepository.findByNames(List.of("aaa", "bbb"));
		
		findUsernameList.forEach(m -> log.info("username = " + m));
		
		List<String> collect = findUsernameList.stream().map(Member::getUsername).collect(Collectors.toList());
		
		assertThat(member1.getUsername()).isIn(collect);
		assertThat(member2.getUsername()).isIn(collect);
	}
	
	@Test
	public void returnType() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("bbb", 20);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		List<Member> findByUsername = memberRepository.findByUsername(member1.getUsername());
		// 컬렉션 반환 시에 값이 없다면 빈 컬렉션을 반환함. null 값이 아님.
		List<Member> findListByUsername = memberRepository.findListByUsername(member1.getUsername());
		// 단건 조회인 경우에는 값이 없다면 null 반환.
		Member findMemberByUsername = memberRepository.findMemberByUsername(member1.getUsername());
		Optional<Member> findOptionalByUsername = memberRepository.findOptionalByUsername(member1.getUsername());
	}
	
	@Test
	public void paging() {
		Team savedTeam = teamRepository.save(new Team("teamA"));
		
		Member member1 = memberRepository.save(new Member("member1", 10));
		Member member2 = memberRepository.save(new Member("member2", 10));
		Member member3 = memberRepository.save(new Member("member3", 10));
		Member member4 = memberRepository.save(new Member("member4", 10));
		Member member5 = memberRepository.save(new Member("member5", 10));
		
		int age = 10;
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Direction.DESC, "username"));
		
		
		Page<Member> findByPage = memberRepository.findByAge(age, pageRequest);
		
		// map()을 이용한 Dto로 변환
		Page<MemberDto> toMap = findByPage.map(m -> new MemberDto(m.getId(), m.getUsername()));
		
		List<Member> content = findByPage.getContent();
		long totalCount = findByPage.getTotalElements();
		
		content.forEach(System.out::println);
		System.out.println("totalCount = " + totalCount);
		
		//페이지 계산 공식 적용...
		// totalPage = totalCount / size ...
		// 마지막 페이지 ...
		// 최초 페이지 ..
		
		// then
		assertThat(content.size()).isEqualTo(3);
		assertThat(totalCount).isEqualTo(5);
		// 페이지 넘버 검증
		assertThat(findByPage.getNumber()).isEqualTo(0);
		// 총 페이지 넘버 검증
		assertThat(findByPage.getTotalPages()).isEqualTo(2);
		// 첫번째 페이지인가
		assertThat(findByPage.isFirst()).isTrue();
		// 다음 페이지가 있는지
		assertThat(findByPage.hasNext()).isTrue();	
	}
	
	@Test
	public void bulkUpadate() {
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 19));
		memberRepository.save(new Member("member3", 20));
		memberRepository.save(new Member("member4", 21));
		memberRepository.save(new Member("member5", 40));
		
		int age = 20;

		int bulkAgePlus = memberRepository.bulkAgePlus(age);

		
		//페이지 계산 공식 적용...
		// totalPage = totalCount / size ...
		// 마지막 페이지 ...
		// 최초 페이지 ..
		
		// then
		assertThat(bulkAgePlus).isEqualTo(3);
	}
	
	@Test
	public void findMemberLazy() {
		// given
		// member1 -> teamA
		// member2 -> teamB
		
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		
		teamRepository.save(teamA);
		teamRepository.save(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 10, teamB);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		em.flush();
		em.clear();
		
		// when
		List<Member> member = memberRepository.findMemberFetchJoin();
		member.forEach(m -> {
			System.out.println("member = " + m);
			System.out.println("-> member.team = " + m.getTeam().getName());
		});
	}
	
	@Test
	public void queryHint() {
		// given
		Member savedMember = memberRepository.save(new Member("member1", 10));
		
		em.flush();
		em.clear();
		
		// when
		Member findMember = memberRepository.findReadOnlyByUsername(savedMember.getUsername());
		findMember.setUsername("modifiedMember"); // 커밋 시점에 변경감지가 일어나지 않는다!
		
		em.flush();
	}
	
	@Test
	public void lock() {
		// given
		Member savedMember = memberRepository.save(new Member("member1", 10));
		
		em.flush();
		em.clear();
		
		// when
		List<Member> findMember = memberRepository.findLockByUsername(savedMember.getUsername());
		
		
		em.flush();
	}
}
