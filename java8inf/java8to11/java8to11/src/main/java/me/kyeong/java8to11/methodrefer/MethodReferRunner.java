package me.kyeong.java8to11.methodrefer;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferRunner {
	public static void main(String[] args) {
		UnaryOperator<String> hi = (s) -> "hi" + s;
		// static 메소드를 참조하는 방법
		UnaryOperator<String> hi_MethodRefer = Greeting::hi;

		// 인스턴스의 메소드를 참조하는 방법
		Greeting greeting = new Greeting();

		UnaryOperator<String> hello_MethodRefer = greeting::hello;

		Supplier<Greeting> newGreeting = Greeting::new;
		Greeting greetingInstence1 = newGreeting.get();

		Function<String, Greeting> kyeonghoGreeting = Greeting::new;
		Greeting greetingInstence2 = kyeonghoGreeting.apply("Kyeongho");

		// 실행

		System.out.println(hi_MethodRefer.apply("Kyeongho"));
		System.out.println(hello_MethodRefer.apply("Kyeongho"));

		System.out.println(greetingInstence1.hello("Kyeongho"));
		System.out.println(greetingInstence2.getName());

		System.out.println("-----------------");
		// 불특정 다수의 인스턴스를 받는 방법
		String[] names = { "sukyeong", "minho", "kyeongho" };
		Arrays.sort(names, String::compareToIgnoreCase);

		Arrays.stream(names).forEach(System.out::println);
	}
}
