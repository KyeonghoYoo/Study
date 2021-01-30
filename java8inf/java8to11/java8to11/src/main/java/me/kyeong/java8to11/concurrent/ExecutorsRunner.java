package me.kyeong.java8to11.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsRunner {

	public static void main(String[] args) {
//		ExecutorService executorService = Executors.newSingleThreadExecutor();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(); // ExecutorService를 상속 받아 만들어진 API
		scheduledExecutorService.schedule(getRunnable("Hi"), 3, TimeUnit.SECONDS);
		scheduledExecutorService.scheduleAtFixedRate(getRunnable("Greeting"), 1, 2, TimeUnit.SECONDS);
		
		executorService.submit(getRunnable("Hello"));
		executorService.submit(getRunnable("kyeongho"));
		executorService.submit(getRunnable("minho"));
		executorService.submit(getRunnable("sukyeong"));
		executorService.submit(getRunnable("foo"));
		
		// 명시적으로 shutdown을 시켜줘야 프로세스가 종료됨. 아니면 계속 대기 상태
		// graceful shutdown이라고 함. 깔끔하게 일을 마치고 명시적으로 닫는다라는 뉘앙스
		executorService.shutdown();
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduledExecutorService.shutdown();
		
		
	}

	private static Runnable getRunnable(String msg) {
		return () -> {
			System.out.println(msg + ": " + Thread.currentThread().getName());
		};
	}
}
