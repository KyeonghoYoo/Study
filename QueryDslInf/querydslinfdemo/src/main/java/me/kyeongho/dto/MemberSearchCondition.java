package me.kyeongho.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {
	// 회원명, 팀명, 나이(ageGoe, ageLoe)
	
	private String username;
	
	private String teamNaem;
	
	private Integer ageGoe;

	private Integer ageLoe;
}
