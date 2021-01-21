package me.kyeongho.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kyeongho.entity.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

	private Long id;
	
	private String username;
	
	private String teamName;

	public MemberDto(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
	public MemberDto(Member member) {
		this.id = member.getId();
		this.username = member.getUsername();
	}
	
}
