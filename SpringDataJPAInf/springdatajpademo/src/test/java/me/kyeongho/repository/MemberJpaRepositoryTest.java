package me.kyeongho.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import me.kyeongho.entity.Member;

@Slf4j
@SpringBootTest
@Transactional
public class MemberJpaRepositoryTest {
	
	@Autowired MemberJpaRepository memberJpaRepository;
	
	@Test
	public void testMember() {
		Member member = new Member("memberA");
		
		Member savedMember = memberJpaRepository.save(member);
		
		Member findMember = memberJpaRepository.find(savedMember.getId());
		
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member);
	}
	
	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		
		Member savedMember1 = memberJpaRepository.save(member1);
		Member savedMember2 = memberJpaRepository.save(member2);
		
		// 단건 조회
		Member findMember1 = memberJpaRepository.find(member1.getId());
		Member findMember2 = memberJpaRepository.find(member2.getId());
		assertThat(findMember1).isEqualTo(member1);
		assertThat(findMember2).isEqualTo(member2);
		
		// 커밋 시점에 변경 감지(Dirty Checking)에 의한 변경이 일어난다.
		findMember1.setUsername("modifedMember1");
		
		// 리스트 조회
		List<Member> all = memberJpaRepository.findAll();
		assertThat(all.size()).isEqualTo(2);
		
		// 카운트 검증
		long count = memberJpaRepository.count();
		assertThat(count).isEqualTo(2);
		
		// 삭제 검증
		memberJpaRepository.delete(member1);
		memberJpaRepository.delete(member2);
		
		long count2 = memberJpaRepository.count();
		assertThat(count2).isEqualTo(0);

	}
	
	@Test
	public void findByUsernameAndAgeGreaterThan() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("aaa", 20);
		
		memberJpaRepository.save(member1);
		memberJpaRepository.save(member2);
		
		List<Member> findMembers = memberJpaRepository.findByUsernameAndAgeGreaterThan("aaa", 15);
		
		findMembers.forEach(m -> log.info("member = " + m));
		
		assertThat(findMembers.size()).isEqualTo(1);
		assertThat(findMembers.get(0).getAge()).isEqualTo(20);
	}
	
	@Test
	public void namedQueryTest() {
		Member member1 = new Member("aaa", 10);
		Member member2 = new Member("bbb", 20);
		
		memberJpaRepository.save(member1);
		memberJpaRepository.save(member2);
		
		List<Member> findByUsername = memberJpaRepository.findByUsername("aaa");
		Member findMember = findByUsername.get(0);
		
		assertThat(findMember).isEqualTo(member1);
	}
	
	@Test
	public void paging() {
		memberJpaRepository.save(new Member("member1", 10));
		memberJpaRepository.save(new Member("member2", 10));
		memberJpaRepository.save(new Member("member3", 10));
		memberJpaRepository.save(new Member("member4", 10));
		memberJpaRepository.save(new Member("member5", 10));
		
		int age = 10;
		int offset = 0;
		int limit = 3;
		
		List<Member> findByPage = memberJpaRepository.findByPage(age, offset, limit);
		long totalCount = memberJpaRepository.totalCount(age);
		
		//페이지 계산 공식 적용...
		// totalPage = totalCount / size ...
		// 마지막 페이지 ...
		// 최초 페이지 ..
		
		// then
		assertThat(findByPage.size()).isEqualTo(3);
		assertThat(totalCount).isEqualTo(5);
	}
	
	@Test
	public void bulkUpadate() {
		memberJpaRepository.save(new Member("member1", 10));
		memberJpaRepository.save(new Member("member2", 19));
		memberJpaRepository.save(new Member("member3", 20));
		memberJpaRepository.save(new Member("member4", 21));
		memberJpaRepository.save(new Member("member5", 40));
		
		int age = 20;

		int bulkAgePlus = memberJpaRepository.bulkAgePlus(age);

		
		//페이지 계산 공식 적용...
		// totalPage = totalCount / size ...
		// 마지막 페이지 ...
		// 최초 페이지 ..
		
		// then
		assertThat(bulkAgePlus).isEqualTo(3);
	}
	
}
