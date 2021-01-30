package me.kyeong.java8to11.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletableFutureRunner {

	/*
	 * CompletableFuture를 사용하면 비동기 프로그래밍이 가능케한다.
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		Future<String> future = executorService.submit(() -> "hello");
		
		// TODO
		
		String result = future.get();
		System.out.println(result);
		// TODO -> 이 곳의 코드는 get() 이후에 실행할 수 있다.
		
		//// CompletableFutrue
		
		CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("keyongho");
		
		System.out.println(completableFuture.get());
		
		// 비동기로 Runnable을 실행함.
		CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
			System.out.println("Hello " + Thread.currentThread().getName());
		});
		
		// 값을 반환하기 위해 Supllier 함수형 인터페이스를 사용.
		CompletableFuture<String> supplierAsyncFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Hello " + Thread.currentThread().getName());
			return "Hello";
		});
		
		System.out.println(supplierAsyncFuture.get());
		
		//// 콜백 제공하기
		CompletableFuture<String> supplierAsyncFuture_callback_function = CompletableFuture.supplyAsync(() -> {
			System.out.println("function " + Thread.currentThread().getName());
			return "function";
		}, executorService).thenApply((s) -> {
			System.out.println("Function 콜백 실행");
			System.out.println(Thread.currentThread().getName());
			System.out.println("Function 콜백 종료");
			return s.toUpperCase();
		}); // 받아온 값으로 정제하여 리턴할 때, thenApply()
		
		CompletableFuture<Void> supplierAsyncFuture_callback_consumer = CompletableFuture.supplyAsync(() -> {
			System.out.println("consumer " + Thread.currentThread().getName());
			return "consumer";
		}, executorService).thenAccept((s) -> {
			System.out.println("Consumer 콜백 실행");
			System.out.println(Thread.currentThread().getName());
			System.out.println(s.toUpperCase());
			System.out.println("Consumer 콜백 종료");
		}); // 받아온 값으로 리턴없이 로직만 실행할 때. thenAccept()
	
		CompletableFuture<Void> supplierAsyncFuture_callback_runnable = CompletableFuture.supplyAsync(() -> {
			System.out.println("runnable " + Thread.currentThread().getName());
			return "runnable";
		}).thenRunAsync(() -> {
			System.out.println("Runnable 콜백 실행");
			System.out.println(Thread.currentThread().getName());
			System.out.println("Runnable 콜백 종료");
		}, executorService); 
			// 파라미터를 받지 않고 반환도 없이 로직만 실행할 때. thenRunnable()
			// 만약 콜백 메소드를 다른 쓰레드 풀에서 사용하고 싶다하면 then..Async()를 사용하면 됨.
		
		
		executorService.awaitTermination(500L, TimeUnit.MILLISECONDS);
	}
}
