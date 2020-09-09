import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 더 맵게  https://programmers.co.kr/learn/courses/30/lessons/42626
class Solution1 {
	// 선형으로 풀었을 때
	public int solution(int[] scoville, int K) {
		int answer = 0;
		List<Integer> scovilleList = Arrays.stream(scoville).sorted().boxed().collect(Collectors.toList());
		if (scovilleList.size() == 0) {
			return -1;
		}

		while (scovilleList.size() > 1 && scovilleList.get(0) < K) {
			answer++;
			int mixedNewFood = scovilleList.get(0) + (scovilleList.get(1) * 2);
			scovilleList.set(0, mixedNewFood);
			scovilleList.remove(1);

			for (int i = 0; i + 1 < scovilleList.size() && scovilleList.get(i) > scovilleList.get(i + 1); i++) {
				int temp = scovilleList.get(i);
				scovilleList.set(i, scovilleList.get(i + 1));
				scovilleList.set(i + 1, temp);
			}
			System.out.println(scovilleList.toString());
		}

		return scovilleList.get(0) < K ? -1 : answer;
	}
}