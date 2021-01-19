package me.kyeongho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.kyeongho.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	public List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
}
