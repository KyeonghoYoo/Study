package me.kyeongho;

import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringdatajpademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpademoApplication.class, args);
	}

	
	@Bean
	public AuditorAware<String> auditorProvider() {
		// createdBy, lastModifiedBy에 자동적으로 생성자, 수정자 아이디를 주입해줄 로직을 짜면됨.
		// 스프링 Security나 Http 세션, 쿠키 등 인증 로직을 이용하여 사용자의 아이디를 꺼내와서 Optional.of()로 반환하면 된다.
		return () -> Optional.of(UUID.randomUUID().toString());
	}
}
