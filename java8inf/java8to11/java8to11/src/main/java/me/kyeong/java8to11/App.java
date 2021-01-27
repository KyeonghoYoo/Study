package me.kyeong.java8to11;

import java.util.function.Function;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// 익명 내부 클래스 anonymous inner class
        RunSomething runSomething = new RunSomething() {
			
			@Override
			public int doIt(int number) {
				return number + 10;
			}
		};
		// 람다 표현식을 이용하여 코드를 간결하게
		RunSomething runSomthing_lambda = (number) -> number + 10;
		RunSomething runSomthing_lambda2 = (number) -> {
			System.out.println("Hello");
			System.out.println("Kyeongho");
			return number + 10;
		};
		
		Plust10 plust10 = new Plust10();
		Integer result = plust10.apply(10);
		System.out.println("result = " + result);
		
		Function<Integer, Integer> plus10_func = (i) -> i + 10;
		System.out.println("result = " + plus10_func.apply(10));
		
		Function<Integer, Integer> multiply2 = (i) -> i * 2;
		
		Function<Integer, Integer> composed_multiply2AndPlust10 = plus10_func.compose(multiply2);
		composed_multiply2AndPlust10.apply(10); // 결과 -> 30
		
		Function<Integer, Integer> plust10AndMultiply2 = plus10_func.andThen(multiply2);
		plust10AndMultiply2.apply(10); // 결과 40
    }
}
