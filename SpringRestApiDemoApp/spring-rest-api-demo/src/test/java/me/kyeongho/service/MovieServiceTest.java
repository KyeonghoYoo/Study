package me.kyeongho.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.kyeongho.api.movie.repository.Movie;
import me.kyeongho.api.movie.service.MovieService;

@SpringBootTest
public class MovieServiceTest {

	@Autowired MovieService movieService;
	
	@DisplayName("영화 검색 API 요청 테스트")
	@Test
	void findByQuery() {
		
		List<Movie> result = movieService.search("반지의 제왕");
		
		assertNotNull(result);
	}
}
