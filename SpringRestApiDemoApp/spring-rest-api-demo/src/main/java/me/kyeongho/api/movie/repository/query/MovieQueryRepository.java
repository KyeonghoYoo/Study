package me.kyeongho.api.movie.repository.query;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import me.kyeongho.api.movie.repository.ResponseMovieSerach;
import me.kyeongho.common.NaverApiClientProperties;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MovieQueryRepository {
	
	private final NaverApiClientProperties clientProperties;
	
	private final WebClient webClient;
	
	public Mono<ResponseMovieSerach> findByQuery(final String query) {
		WebClient movieWebClient = webClient.mutate()
				.defaultHeader("X-Naver-Client-Id", clientProperties.getId())
				.defaultHeader("X-Naver-Client-Secret", clientProperties.getSceret())
				.baseUrl(clientProperties.getMovieurl())
				.build();
		 
		return movieWebClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.queryParam("query", query)
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::isError, ex -> ex.createException().flatMap(Mono::error))
				.bodyToMono(ResponseMovieSerach.class);
	}
}
