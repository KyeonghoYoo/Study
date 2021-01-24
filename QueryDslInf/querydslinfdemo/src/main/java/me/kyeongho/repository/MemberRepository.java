package me.kyeongho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.kyeongho.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
	
	public List<Member> findByUsername(String username);
}
