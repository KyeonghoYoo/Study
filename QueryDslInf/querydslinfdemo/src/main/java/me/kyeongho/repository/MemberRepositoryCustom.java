package me.kyeongho.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.kyeongho.dto.MemberSearchCondition;
import me.kyeongho.dto.MemberTeamDto;

public interface MemberRepositoryCustom {
	public List<MemberTeamDto> search(MemberSearchCondition condition);
	public Page<MemberTeamDto> searchSimple(MemberSearchCondition condition, Pageable pageable);
	public Page<MemberTeamDto> searchComplex(MemberSearchCondition condition, Pageable pageable);
}
