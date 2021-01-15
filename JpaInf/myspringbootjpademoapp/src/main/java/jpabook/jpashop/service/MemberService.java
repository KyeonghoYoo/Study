package jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	// final 추천 -> 컴파일 시점에 오류 체크 가능
	private final MemberRepository memberRepository;
	
	// 생성자 인잭션으로 repository 사용을 추천
	// lombok이 제공하는 @RequiredArgsConstructor를 쓰면 final이 붙은 필드에 대해서만 생성자를 생성하고, 
	// 스프링은 파라미터가 하나인 생성자에 autowired를 묵시적으로 수행한다.
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원 가입
	public Long join(Member member) {
		validateDuplicatieMember(member);
		memberRepository.save(member);
		return member.getId(); // 영속성 컨텍스트가 DB에 저장한 후 엔티티를 관리하므로 id를 받아올 수 있다.
	}

	// 회원 전체 조회
	// 읽기에는 readOnly를 true로 넣으면 성능 최적화에 도움이 된다.
	@Transactional(readOnly = true)
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	// 회원 단건 조회
	@Transactional(readOnly = true)
	public Member findOne(Long memberId) {
		return memberRepository.find(memberId);
	}
	
	// 회원 수정
	public void update(Long id, String name) {
		Member member = memberRepository.find(id);
		member.setUsername(name);
	}

	private void validateDuplicatieMember(Member member) {
		// Exception
		List<Member> findMembers = memberRepository.findByName(member.getUsername());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
}
