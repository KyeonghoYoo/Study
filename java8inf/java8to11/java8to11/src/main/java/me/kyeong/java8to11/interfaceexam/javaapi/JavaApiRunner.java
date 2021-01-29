package me.kyeong.java8to11.interfaceexam.javaapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class JavaApiRunner {
	public static void main(String[] args) {
		List<String> name = new ArrayList<>();
		name.add("kyeongho");
		name.add("sukyeong");
		name.add("minho");
		name.add("foo");
		
		name.forEach(System.out::println);
		
		System.out.println("===============");
		
		Spliterator<String> spliterator = name.spliterator();
		Spliterator<String> trySplit = spliterator.trySplit();
		
		while(spliterator.tryAdvance(System.out::println));
		
		System.out.println("===============");
		
		while(trySplit.tryAdvance(System.out::println));
		
		System.out.println("===============");
		
		Stream<String> map = name.stream()
				.map(String::toUpperCase);
		map.forEach(System.out::println);
		
		long count = name.stream()
				.map(s -> s.toUpperCase())
				.filter(s -> s.startsWith("K"))
				.count();
		
		System.out.println(count);
		
		System.out.println("===============");
		
		name.removeIf(s -> s.startsWith("k"));
		
		name.forEach(System.out::println);
		
		System.out.println("===============");
		
		// 정렬 Comparator 함수형 인터페이스 구현체 주입
		name.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
	}
}
