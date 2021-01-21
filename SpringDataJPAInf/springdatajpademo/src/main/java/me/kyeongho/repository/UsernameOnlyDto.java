package me.kyeongho.repository;

public class UsernameOnlyDto {

	private final String username;

	// 생성자의 파라미터 이름으로 매칭시켜 Spring 데이터 JPA Projection이 된다.
	public UsernameOnlyDto(String username) {
		super();
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}
