package me.kyeongho.api.movie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.kyeongho.api.movie.repository.Movie;
import me.kyeongho.api.movie.repository.query.MovieQueryRepository;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieQueryRepository movieQueryRepository;
	
	public List<Movie> search(final String query) {
		return findByQuery(query);
	}
	
	public List<Movie> searchOrderByUserRating(final String query) {
		return getListOrderRating(findByQuery(query));
	}
	
	private List<Movie> findByQuery(String query) {
		
		return movieQueryRepository.findByQuery(query)
				.flux()
				.toStream()
				.findFirst()
				.get()
				.getItems().stream()
				.map(Movie::new)
				.collect(Collectors.toList());
	}
	
	private List<Movie> getListOrderRating(List<Movie> movieList) {
		return movieList.stream()
					.filter(movie -> !Float.valueOf(movie.getUserRating()).equals(0.0f))
					.sorted((m1, m2) -> Float.compare(m2.getUserRating(), m1.getUserRating()))
					.collect(Collectors.toList());
	}
}
