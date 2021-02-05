package me.kyeongho.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.extern.slf4j.Slf4j;
import me.kyeongho.api.controller.SearchController;
import me.kyeongho.api.movie.repository.ResponseMovieSerach;
import me.kyeongho.api.movie.repository.query.MovieQueryRepository;
import me.kyeongho.common.error.ErrorResponse;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = { "kyeongho.api.naver.client.id = WORNGID",
									"kyeongho.api.naver.client.secret = WORNGSECET"})
public class MovieSearchExceptionTest {

	@Autowired MovieQueryRepository queryRepository;

	@Autowired ApplicationContext context;
	
	@Autowired SearchController searchController;
	
	WebTestClient webTestClient;
	
	@Test
	void 네이버API_인증실패() {
		assertThrows(WebClientResponseException.class, () -> {
			ResponseMovieSerach response = queryRepository.findByQuery("반지의 제왕")
				.flux()
				.toStream()
				.findFirst().get();
		});
	}
	
	@Test
	void 네이버API_인증반환값_검증() {
		webTestClient = WebTestClient
						.bindToController(searchController)
						.build();
		
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder
							.path("/api/v1/search/movie")
							.queryParam("query", "반지의 제왕")
							.build())
			.exchange()
			.expectStatus().is5xxServerError();
	}
	
}
