package me.kyeongho.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.kyeongho.api.movie.MovieSearchCondition;
import me.kyeongho.api.movie.repository.Movie;
import me.kyeongho.api.movie.service.MovieService;
import me.kyeongho.common.BaseResponse;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

	private final MovieService movieService;
	
	@GetMapping("/movie")
	public BaseResponse<List<Movie>> searchMovie(final MovieSearchCondition condition) throws Exception {
		String query = condition.getQuery();
		
		if(condition.isOrderByUserRating()) {
			return new BaseResponse<List<Movie>>(movieService.searchOrderByUserRating(query));
		} else {
			return new BaseResponse<List<Movie>>(movieService.search(query));
		}
	}
	
}
