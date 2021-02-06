package me.kyeongho.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import me.kyeongho.api.exception.ClientNoContentException;
import me.kyeongho.api.movie.repository.ResponseMovieSerach;
import me.kyeongho.api.movie.repository.query.MovieQueryRepository;
import me.kyeongho.api.movie.service.MovieService;
import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MovieServiceExceptionTest {
	
	@MockBean MovieQueryRepository movieQueryRepository;
	
	@Autowired MovieService movieService;
	
	@DisplayName("검색된 데이터(items)가 비어있을 경우")
	@Test
	void testNoContentException() {
		given(movieQueryRepository.findByQuery(anyString()))
				.willReturn(getEmptyItemsResponseMovieSearch());
		
		assertThrows(ClientNoContentException.class, () -> {
			movieService.search("반지의 제왕");
		});
		
	}

	private Mono<ResponseMovieSerach> getEmptyItemsResponseMovieSearch() {
		return Mono.just(new ResponseMovieSerach(
				LocalDateTime.now().toString(),
				0,
				1,
				0,
				new ArrayList<>()));
	}
	
}
