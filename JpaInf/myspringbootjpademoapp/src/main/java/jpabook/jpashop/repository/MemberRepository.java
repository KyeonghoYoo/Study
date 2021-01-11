package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.member.Member;

@Repository
public class MemberRepository {

	// Spring Boot Data JPA를 사용하면 EntityManager에 대해 @PersistenceContext를 사용하지 않고
	// @Autowired를 적용할 수 있음, 그럼 @RequiredArgsConstructer를 이욜한 간결한 생성자 인잭션 생성이 가능해짐
	@PersistenceContext
	private EntityManager em;
	
	// 저장
	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}

	// 단건 조회
	public Member find(Long id) {
		return em.find(Member.class, id);
	}

	// 전체 조회
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	// 이름으로 조회
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
}
