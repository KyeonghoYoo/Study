package me.kyeongho.api.movie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.kyeongho.api.movie.repository.Movie;
import me.kyeongho.api.movie.repository.ResponseMovieSerach;
import me.kyeongho.api.movie.repository.query.MovieQueryRepository;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieQueryRepository movieQueryRepository;
	
	public List<Movie> search(final String query) {
		
		Mono<ResponseMovieSerach> result = movieQueryRepository.findByQuery(query);
		
		ResponseMovieSerach response = result.flux().toStream().findFirst().get();
		
		return response.getItems().stream()
				.map(Movie::new)
				.collect(Collectors.toList());
	}
}
