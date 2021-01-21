package me.kyeongho.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

	public String getUsername();
	
	// Open Projection -> 멤버 엔티티를 모두 조회한 뒤 값을 빼냄.
	// 장점 -> SpEL 지원
	@Value("#{target.username + ' ' + target.age}")
	public String getBySpl();
}
