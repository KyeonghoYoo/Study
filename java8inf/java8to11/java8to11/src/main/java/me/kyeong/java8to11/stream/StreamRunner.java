package me.kyeong.java8to11.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamRunner {
	
	public static void main(String[] args) {
		List<String> name = new ArrayList<>();
		name.add("kyeongho");
		name.add("sukyeong");
		name.add("minho");
		name.add("foo");
		System.out.println("====================");
		Stream<String> stringStream = name.stream()
				.map(s -> {
					// 출력되지 않는다. 중개형 오퍼레이터들은 터미널 오퍼레이터가 실행되기 전까진 실행되지 않는다. lazy하게 처리가 된다.
					System.out.println(s);
					return s.toUpperCase();
				});
		System.out.println("====================");
		// 원본은 바뀌지 않는다.
		name.forEach(System.out::println);
		
		// 스트림을 이용하여 병렬적으로 요소들을 처리할 수 있다.
		// 데이터가 정말 방대한 경우에는 성능상 도움이 될 수 있다.
		// 병렬 처리도 쓰레드의 컨텍스트 스위칭 비용 등, 성능을 잡아먹는 요소가 있기 때문에 무조건적으로 성능 향상이 되진 않는다.
		name.parallelStream()
				.map(s -> {
					System.out.println(s + " " + Thread.currentThread().getName());
					return s.toUpperCase();	
				})
				.collect(Collectors.toList());
	}
}
