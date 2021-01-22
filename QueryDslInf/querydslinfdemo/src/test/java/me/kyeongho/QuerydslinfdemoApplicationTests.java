package me.kyeongho;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import me.kyeongho.entity.Hello;
import me.kyeongho.entity.QHello;

@SpringBootTest
@Transactional
class QuerydslinfdemoApplicationTests {

	@PersistenceContext
	EntityManager em;
	
	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = new QHello("h");
		
		Hello result = query
				.selectFrom(qHello)
				.fetchOne();
		
		System.out.println("result.id = " + hello.getId());
		
		assertThat(result).isEqualTo(hello);
		assertThat(result.getId()).isEqualTo(hello.getId());
	}

}
