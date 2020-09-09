import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 더 맵게  https://programmers.co.kr/learn/courses/30/lessons/42626
class Solution2 {
	// 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
	// 1번째로 이분탐색으로 풀어보자
	public int solution(int[] scoville, int K) {
		int answer = 0;
		// 스코빌지수를 오름차순 정렬
		Arrays.sort(scoville);
		// 한 번도 섞을 필요 없이 가장 작은 스코빌 지수가 K이상이라면 0을 반환한다.
		if (scoville[0] >= K) {
			return 0;
		}
		// 최대로 섞는 횟수
		int max = scoville.length - 1;
		// 최소로 섞는 횟수
		int min = 1;
		// 중간 값
		int mid = (min + max) / 2;
		answer = mid;

		while (min < max) {
			// stream으로 변환하여 음식들의 스코빌지수를 오름차순으로 정렬 후 List로 바꿔준다.
			List<Integer> scovilleList = Arrays.stream(scoville).boxed().collect(Collectors.toList());

			// mid 만큼 음식을 섞는다.
			for (int i = 0; scovilleList.size() > 1 && i < mid; i++) {
				int mixedNewFood = scovilleList.get(0) + (scovilleList.get(1) * 2);
				scovilleList.set(0, mixedNewFood);
				scovilleList.remove(1);

				for (int j = 0; j + 1 < scovilleList.size() && scovilleList.get(j) > scovilleList.get(j + 1); j++) {
					int temp = scovilleList.get(j);
					scovilleList.set(j, scovilleList.get(j + 1));
					scovilleList.set(j + 1, temp);
				}
			}

			if (scovilleList.get(0) < K) {
				// mid 만큼 섞었을때 조건을 만족하지 않는 경우, 섞는 횟수를 늘린다.
				min = mid + 1;

			} else {
				// mid 만큼 섞었을때 조건을 만족하는 경우, 섞는 횟수를 줄인다.
				max = mid;
				answer = answer < mid ? answer : mid;
			}

			mid = (min + max) / 2;
		}
		if (min >= scoville.length)
			return -1;
		return answer;
	}
}