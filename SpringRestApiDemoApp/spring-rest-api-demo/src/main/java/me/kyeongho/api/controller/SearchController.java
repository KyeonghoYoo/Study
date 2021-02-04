package me.kyeongho.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.kyeongho.api.movie.repository.Movie;
import me.kyeongho.api.movie.service.MovieService;
import me.kyeongho.common.BaseResponse;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

	private final MovieService movieService;
	
	@GetMapping("/movie")
	public BaseResponse<List<Movie>> searchMovie(@RequestParam("query") String query) {
		return new BaseResponse<List<Movie>>(movieService.search(query));
	}
	
}
