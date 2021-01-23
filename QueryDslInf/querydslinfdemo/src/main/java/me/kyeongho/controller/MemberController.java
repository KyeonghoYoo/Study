package me.kyeongho.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.kyeongho.dto.MemberSearchCondition;
import me.kyeongho.dto.MemberTeamDto;
import me.kyeongho.repository.MemberJpaRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberJpaRepository memberJpaRepository;
	
	@GetMapping("/v1/members")
	public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
		return memberJpaRepository.search(condition);
	}
}
