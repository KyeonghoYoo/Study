package me.kyeongho.api.movie;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieSearchCondition {

	private String query;
	
	private boolean orderByUserRating = false;
}
