package me.kyeongho.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.kyeongho.api.movie.repository.Movie;
import me.kyeongho.api.movie.repository.ResponseMovieSerach;
import me.kyeongho.api.movie.repository.ResponseMovieSerach.Item;
import me.kyeongho.api.movie.repository.query.MovieQueryRepository;
import me.kyeongho.api.movie.service.MovieService;
import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

	@Autowired MovieService movieService;
	
	@Mock MovieQueryRepository mockMovieQueryRepository;
	
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
	
	@DisplayName("유저 점수가 없는 데이터를 정제하는지 테스트")
	@Test
	void 유저점수_없는_데이터_정제() {
		final int expectedSize = 4;
		
		given(mockMovieQueryRepository.findByQuery(anyString()))
				.willReturn(getItemsResponseMovieSearchIncudeNoUserRaiting());
		MovieService newMovieService = new MovieService(mockMovieQueryRepository);
		
		List<Movie> result = newMovieService.searchOrderByUserRating("반지의 제왕");
		
		
		assertThat(result.size()).isEqualTo(expectedSize);
	}
	
	private Mono<ResponseMovieSerach> getItemsResponseMovieSearchIncudeNoUserRaiting() {
		ResponseMovieSerach responseMovieSearchList = new ResponseMovieSerach(
				LocalDateTime.now().toString(),
				0,
				1,
				0,
				new ArrayList<>());
		
		List<Item> items = responseMovieSearchList.getItems();
		items.add(new Item("sample1", "", "", "", "", "", "", 0.0f));
		items.add(new Item("sample2", "", "", "", "", "", "", 1.0f));
		items.add(new Item("sample3", "", "", "", "", "", "", 2.0f));
		items.add(new Item("sample4", "", "", "", "", "", "", 3.0f));
		items.add(new Item("sample5", "", "", "", "", "", "", 0.0f));
		items.add(new Item("sample6", "", "", "", "", "", "", 5.0f));
		
		return Mono.just(responseMovieSearchList);
	}
}
