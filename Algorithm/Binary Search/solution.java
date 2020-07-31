package me.ykh.algorithm.groom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author 유경호
 * @quiz Binary Search
 * @since 2020-07-31
 * @link https://level.goorm.io/exam/43064/binary-search/quiz/1
 **/
public class solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String input2 = br.readLine();
		String input3 = br.readLine();
		int[] arr = Pattern.compile(" ").splitAsStream(input2).mapToInt(e -> Integer.parseInt(e)).toArray();
		Arrays.sort(arr);
		int goal = Integer.parseInt(input3);

		int left = 0;
		int right = arr.length - 1;
		int mid = (left + right) / 2;

		while (left < right) {
			
			if (arr[mid] >= goal) {
				right = mid;
			} else {
				left = mid + 1;
			}
			mid = (left + right) / 2;
			
		}
		System.out.println("arr[" + mid + "] = " + arr[mid]);
		System.out.println(arr[mid] == goal ? mid : "X");
	}
}
