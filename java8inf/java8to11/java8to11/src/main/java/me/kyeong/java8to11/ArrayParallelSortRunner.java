package me.kyeong.java8to11;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ArrayParallelSortRunner {

	public static void main(String[] args) {
		final int size = 1500;
		int[] numbers = new int[size];
		Random random = new Random();
		
		IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
		long start = System.nanoTime();
		
		Arrays.sort(numbers); // -> O(n log(n)) 공간 O(n)
		System.out.println("serial sorting took " + (System.nanoTime() - start));
		
		IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
		Arrays.parallelSort(numbers); // 알고리즘 효율성은 같다. 다만 병럴로 처리가 되기 때문에 더 빠르게 정렬됨.
		System.out.println("parallel sorting took " + (System.nanoTime() - start));
		
	}
}
