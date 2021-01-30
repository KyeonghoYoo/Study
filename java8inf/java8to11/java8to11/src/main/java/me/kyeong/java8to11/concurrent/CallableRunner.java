package me.kyeong.java8to11.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableRunner {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		Callable<String> hello = () -> {
			Thread.sleep(2000L);
			return "Hello";
		};
		Callable<String> java = () -> {
			Thread.sleep(1000L);
			return "java";
		};
		Callable<String> kyeongho = () -> {
			Thread.sleep(3000L);
			return "kyeongho";
		};
		// 다 끝날 때까지 기다림, BlockingCall
		List<Future<String>> invokeAll = executorService.invokeAll(Arrays.asList(hello, java, kyeongho));

		for (Future<String> f : invokeAll) {
			System.out.println(f.get()); 
		}
		
		ExecutorService executorService2 = Executors.newFixedThreadPool(3);
		// 가장 먼저 끝난 쓰레드(Callable)의 값이 반환됨.
		String invokeAnyResult = executorService2.invokeAny(Arrays.asList(hello, java, kyeongho));
		System.out.println(invokeAnyResult);
		
		
		Future<String> helloFuture = executorService.submit(hello);
		System.out.println(helloFuture.isDone());
		System.out.println("Started!");
		
		
		

		String result = helloFuture.get(); // callable이 끝날 때까지 기다림
		System.out.println(result);
		
		helloFuture.cancel(true); // true면 interrupt를 발생시키고 종료함. false면 interrupt를 발생시키지 않고 종료함.
		
		System.out.println(helloFuture.isDone());
		System.out.println("End!");
		executorService.shutdown();
		
	}
}
