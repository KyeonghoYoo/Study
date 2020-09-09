import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

// 더 맵게  https://programmers.co.kr/learn/courses/30/lessons/42626
class Solution3 {
	// 우선순위 큐를 사용했을 떄
	public int solution(int[] scoville, int K) {
		int answer = 0;
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		queue.addAll(Arrays.stream(scoville).sorted().boxed().collect(Collectors.toList()));
		
		while(queue.peek() < K) {
			// 가장 맵지 않은 음식의 스코빌 지수
			int first = queue.poll();
			// 두 번째로 맵지 않은 음식의 스코빌 지수
			int second = queue.poll();
			int newScoville =  first + (second * 2);
			queue.add(newScoville);
			answer++;
			if(queue.size() <= 1) {
				return -1;
			}
		}
		
		return answer;
	}
}