package me.kyeong.java8to11.annotation;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("간장")
public class AnnotationRunner {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("kyeongho");
		
		Chicken[] chickens = AnnotationRunner.class.getAnnotationsByType(Chicken.class);
		Arrays.stream(chickens)
				.forEach(c -> {
					System.out.println(c.value());
				});
		
		ChickenContainer chickenContainer = AnnotationRunner.class.getAnnotation(ChickenContainer.class);
		Arrays.stream(chickenContainer.value())
				.forEach(c -> {
					System.out.println(c);
				});
		
	}
	
	static class FeelsLikeChicken<@Chicken("") T> {
		
		// 메소드의 Type Parameter는 반환 타입 전에 선언
		public static <@Chicken("") C> void print(C c) {
			System.out.println(c);
		}
	}
}
