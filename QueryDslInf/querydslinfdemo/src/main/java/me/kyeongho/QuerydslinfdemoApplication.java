package me.kyeongho;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootApplication
public class QuerydslinfdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuerydslinfdemoApplication.class, args);
	}
	
	// JPAQueryFactory 빈 등록
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
}
