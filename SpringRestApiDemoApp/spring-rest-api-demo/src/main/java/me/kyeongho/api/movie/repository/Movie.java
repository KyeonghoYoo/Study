package me.kyeongho.api.movie.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.kyeongho.api.movie.repository.ResponseMovieSerach.Item;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
	
	private String title;
	
	private String link;
	
	private String actor;
	
	private String director;
	
	private float userRating;
	
	// ...TODO: 필드 추가
	
	public Movie(Item item) {
		this.title = item.getTitle();
		this.link = item.getLink();
		this.actor = item.getActor();
		this.director = item.getDirector();
		this.userRating = item.getUserRating();
	}
}
