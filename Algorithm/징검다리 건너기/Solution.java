import java.util.Arrays;

// 징검다리 건너기  https://programmers.co.kr/learn/courses/30/lessons/64062
class Solution {
	public int Solution1(int[] stones, int k) {
		int answer = 0;
		boolean available = true;

		while (available) {
			int cnt = 0;

			for (int i = 0; i < stones.length; i++) {
				int el = stones[i];

				if (el == 0) {
					cnt += 1;
					if (cnt >= k) {
						available = false;
						break;
					}
				} else {
					stones[i] -= 1;
					cnt = 0;
				}

			}

			if (available) {
				answer += 1;
			}
		}

		return answer;
	}

	public int Solution2(int[] stones, int k) {
		int answer = Integer.MAX_VALUE;
		// 최대로 건널 수 있는 인원수를 이분탐색으로 구하기 위해 min은 1명 
		// max는 stones의 요소 중 건널 수 있는 횟수가 가장 큰 돌의 숫자를 넣는다.
		int min = 1;
		int max = Arrays.stream(stones).max().getAsInt();

		
		System.out.println("max = " + max);
		System.out.println("min = " + min);
		System.out.println("mid = " + (min + max) / 2);
		
		while (min < max) {
			// cnt는 넘어야 하는 돌의 수를 구하기위해,
			// mid는 min과 max의 가운데 값을 담는다.
			int cnt = 0;
			int mid = (min + max) / 2;
			
			// strema.map()을 활용해 각 돌의 건널 수 있는 횟수를 mid만큼 빼서 toArray()를 통해
			// Array로 바꿔준다.
			int[] stones2 = Arrays.stream(stones).map(e -> e - mid).toArray();
			
			// mid 인원수가 돌다리를 건널 수 있는지 검사
			for (int i = 0; i < stones2.length; i++) {
				int e = stones2[i];
				if(e <= 0) {
					cnt += 1;
					if(cnt >= k) {
						break;
					}
				} else {
					cnt = 0;
				}
			}
			
			if(cnt >= k) {
				// 건널 수 없다면 max값에 mid를 담아준다.
				max = mid;
				answer = max;
			} else {
				//건널 수 있다면 min에 min + 1을 담아준다.
				min = mid + 1;
				answer = min;
			}
			
			System.out.println("max = " + max);
			System.out.println("min = " + min);
			System.out.println("mid = " + (min + max) / 2);
			
			
		}
		
		// 이분탐색이 끝난 인원수를 반환한다.
		return answer;
	}
}