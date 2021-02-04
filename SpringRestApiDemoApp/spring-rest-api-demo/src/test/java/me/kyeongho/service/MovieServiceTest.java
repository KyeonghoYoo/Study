package me.kyeongho.service;

import static org.assertj.core.api.Assertions.assertThat;
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
	void 영화검색() {
		
		List<Movie> result = movieService.search("반지의 제왕");
		
		assertNotNull(result);
	}
	
	@DisplayName("영화 검색 반환값을 UserRating 내림차순 정렬")
	@Test
	void 유저점수_내림차순_정렬() {
		
		List<Movie> result = movieService.searchOrderByUserRating("반지의 제왕");
		
		assertThat(result)
				.usingComparatorForElementFieldsWithNames((Float m1, Float m2) -> Float.compare(m2, m1), 
													"userRating");
	}
}
