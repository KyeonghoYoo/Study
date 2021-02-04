package me.kyeongho.api.movie.repository;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMovieSerach {
	
	private String lastBuildDate;
	
	private int total;
	
	private int start;
	
	private int display;
	
	private List<Item> items;
	
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Item
	{
		private String title;
		
		private String link;
		
		private String actor;
		
		private String director;
		
		private float userRating;
		
		// ...TODO: 필드 추가
	}
}
