package jpabook.jpashop.repository.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jpabook.jpashop.domain.member.Member;

public interface MemberRepository_SpringDataJpa extends JpaRepository<Member, Long>{
	public List<Member> findByUsername(String username);
}
