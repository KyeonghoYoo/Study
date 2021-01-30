package me.kyeong.java8to11.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureRunner2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
			System.out.println("Hello " + Thread.currentThread().getName());
			return "Hello";
		});
		
		// 아래와 같이 두 개의 futrue를 이어서 조합할 수 있다.
		CompletableFuture<String> future = hello.thenCompose(CompletableFutureRunner2::getMsgFuture);
		
		System.out.println(future.get());
		
		// 둘이 연관관계가 없어, 각각 비동기적으로 실행하는 방법
		
		CompletableFuture<String> msFuture = getMsgFuture("MS");
		CompletableFuture<String> appleFuture = getMsgFuture("APPLE");
		
		CompletableFuture<String> combinedFutrue = msFuture.thenCombine(appleFuture, (ms, apple) -> {
			System.out.println("BiFunction " + Thread.currentThread().getName());
			return ms + ", " + apple;
		});
		System.out.println(combinedFutrue.get());
		
		// 다 끝났을 때 추가적인 콜백을 실행할 때
		CompletableFuture.allOf(msFuture, appleFuture)
				.thenAccept((result) -> {
					System.out.println(result);
				}); // 문제가 있음, 모든 future들의 결과 값의 타입이 동일하단 보장이 없다. 그리고 쓰레드 중 오류가 일어날 수도 있다.
		
		// 위의 문제를 해결하는 법, 복잡함
		List<CompletableFuture<String>> futures = Arrays.asList(msFuture, appleFuture);
		CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);
		CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
				.thenApply(v -> {
					return futures.stream() 
							.map(f -> f.join())
							.collect(Collectors.toList()); 
				});// thenApply가 실행되는 시점은 futures가 모두 종료된 후이다. 따라서 join()이나 get()을 사용할 수 있다.
		
		System.out.println("================");
		results.get().forEach(result -> System.out.println(result));
		
		CompletableFuture<Void> futrue_anyOf = CompletableFuture.anyOf(msFuture, appleFuture).thenAccept(result -> {
			System.out.println(result);
		});
		futrue_anyOf.get();
		
		//// 에러 발생시 예외처리를 콜백으로 하는 법
		boolean throwError = true;
		
		// exceptionally() 사용
		CompletableFuture<String> exFuture_exceptionally = CompletableFuture.supplyAsync(() -> {
			if(throwError) {
				throw new IllegalArgumentException();
			}
			System.out.println("Hello " + Thread.currentThread().getName());
			return "Hello";
		}).exceptionally(ex -> {
			return "Error!";
		});
		
		System.out.println(exFuture_exceptionally.get());
		
		// handle() 사용
		CompletableFuture<String> exFuture_handle = CompletableFuture.supplyAsync(() -> {
			if(throwError) {
				throw new IllegalArgumentException();
			}
			System.out.println("Hello " + Thread.currentThread().getName());
			return "Hello";
		}).handle((result, ex) -> {
			if(ex != null) {
				System.out.println(ex);
				return "ERROR!";
			}
			return result;
		}); // BiFunction으로 첫번째에는 정상적인 반환값, 두번째 인자는 에러발생시 값이 들어옴
		System.out.println(exFuture_handle.get());
		
		
	}

	private static CompletableFuture<String> getMsgFuture(String message) {
		return CompletableFuture.supplyAsync(() -> {
			System.out.println(message + " " + Thread.currentThread().getName());
			return message;
		});
	}
}
