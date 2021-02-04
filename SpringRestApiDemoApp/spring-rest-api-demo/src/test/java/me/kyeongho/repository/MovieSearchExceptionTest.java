package me.kyeongho.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import lombok.extern.slf4j.Slf4j;
import me.kyeongho.api.movie.repository.ResponseMovieSerach;
import me.kyeongho.api.movie.repository.query.MovieQueryRepository;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = { "kyeongho.api.naver.client.id = WORNGID",
									"kyeongho.api.naver.client.secret = WORNGSECET"})
public class MovieSearchExceptionTest {

	@Autowired MovieQueryRepository queryRepository;
	
	@Test
	void 네이버API_인증실패() {
		assertThrows(IllegalStateException.class, () -> {
			ResponseMovieSerach response = queryRepository.findByQuery("반지의 제왕")
				.block();
			log.info(response.toString());
		});
	}
	
}
