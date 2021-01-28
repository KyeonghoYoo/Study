package me.kyeong.java8to11.lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LambdaRunner {
	public static void main(String[] args) {
		LambdaRunner runner = new LambdaRunner();
		
		runner.run();
		
	}
	
	private void run() {
		int baseNumber = 10;
		
		// 로컬 클래스
		class LocalClass {
			void printBaseNumber() {
				int baseNumber = 11;
				System.out.println(baseNumber);
			}
		}
		
		// 익명 클래스
		Consumer<Integer> integerConsumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				int baseNumber = 11;
				System.out.println(baseNumber);
			}
		};
		
		
		// 람다
		IntConsumer printInt = (i) -> {
			System.out.println(i + baseNumber);
		};
		
		printInt.accept(10);
	}
}
