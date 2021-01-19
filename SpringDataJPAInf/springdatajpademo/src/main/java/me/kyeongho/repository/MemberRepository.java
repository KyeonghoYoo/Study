package me.kyeongho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.kyeongho.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
}
