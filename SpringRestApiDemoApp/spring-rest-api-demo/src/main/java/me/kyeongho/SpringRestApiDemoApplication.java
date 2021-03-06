package me.kyeongho;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootApplication
@EnableCaching
public class SpringRestApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiDemoApplication.class, args);
	}

	// JPAQueryFactory 빈 등록
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
}
