package me.kyeongho.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import me.kyeongho.dto.MemberDto;
import me.kyeongho.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
	
	public List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
	
	@Query(name = "Member.findByUsername")
	public List<Member> findByUsername(@Param("username") String username);
	
	@Query("select m from Member m where m.username = :username and m.age = :age")
	List<Member> findUser(@Param("username") String username, @Param("age") int age);
	
	@Query("select m.username from Member m")
	List<String> findUsernameList();
	
	@Query("select new me.kyeongho.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
	List<MemberDto> findMemberDto();
	
	@Query("select m from Member m where m.username in :names")
	List<Member> findByNames(@Param("names") Collection<String> names);
	
	List<Member> findListByUsername(String username);
	
	Member findMemberByUsername(String username);
	
	Optional<Member> findOptionalByUsername(String username);
	
	Page<Member> findByAge(int age, Pageable pageable);
	
	// 카운터 쿼리 분리
	@Query(value = "select m from Member m left join m.team t where m.age >= ?1",
			countQuery = "select count(m) from Member m")
	Page<Member> findByAge2(int age, Pageable pageable);

	@Modifying(clearAutomatically = true)
	@Query("update Member m set m.age = m.age + 1 where m.age >= :age")
	int bulkAgePlus(@Param("age") int age);
	
	@Query("select m from Member m join fetch m.team")
	List<Member> findMemberFetchJoin();
	
	//// @EntityGraph를 이용한 join fetch 적용
	@Override
	@EntityGraph(attributePaths = {"team"})
	List<Member> findAll();
	
	@EntityGraph(attributePaths = {"team"})
	@Query("select m from Member m")
	List<Member> findMemberEntityGraph();
	
	@EntityGraph(attributePaths = {"team"})
	List<Member> findEntityGraphByUsername(@Param("username") String username);
	
	@EntityGraph("Member.all")
	List<Member> findEntityNamedEntityGraphByUsername(@Param("username") String username);
	
	//// JPA 힌트(Hint) & 락(Lock)
	@QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
	Member findReadOnlyByUsername(String username);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<Member> findLockByUsername(String username);
}
