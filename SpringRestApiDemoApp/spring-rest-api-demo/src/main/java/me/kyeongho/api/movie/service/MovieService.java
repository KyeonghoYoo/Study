package me.kyeongho.api.movie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.kyeongho.api.exception.ClientNoContentException;
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
		return getListOrderByUserRating(findByQuery(query));
	}
	
	public Movie searchHighestRatingMovie(final String query) {
		return getHighestRatingMovie(findByQuery(query));
	}

	private List<Movie> findByQuery(String query) {
		List<Movie> result = movieQueryRepository.findByQuery(query)
				.flux()
				.toStream().findFirst()
				.get()
				.getItems().stream()
				.map(Movie::new)
				.collect(Collectors.toList());
		
		if(result.size() == 0) {
			throw new ClientNoContentException();
		}
		
		return result;
	}
	
	private List<Movie> getListOrderByUserRating(List<Movie> movieList) {
		return movieList.stream()
					.filter(movie -> !Float.valueOf(movie.getUserRating()).equals(0.0f))
					.sorted((m1, m2) -> Float.compare(m2.getUserRating(), m1.getUserRating()))
					.collect(Collectors.toList());
	}
	
	private Movie getHighestRatingMovie(List<Movie> movieList) {
		return movieList.stream()
						.max((m1, m2) -> Float.compare(m1.getUserRating(), m2.getUserRating()))
						.orElseThrow(ClientNoContentException::new);
	}
}
