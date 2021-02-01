import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
	    ArrayList<Integer> answer = new ArrayList<>();
		
		Queue<List<Integer>> queue = new ArrayDeque<>();
		
		for (int i = 0; i < speeds.length; i++) {
			int p = progresses[i];
			int s = speeds[i];
			
			queue.add(Arrays.asList(p, s));
		}
		
		while(!queue.isEmpty()) {
			int count = 1; // 배포가 완료된 기능들을 count함.
			List<Integer> p_s = queue.poll();
			
			// 최우선 기능이 개발되기 까지의 일수를 구함
			Integer completionDay = p_s.stream()
						.reduce((p, s) -> {
							return (100 - p) % s == 0 ? (100 - p) / s : (100 - p) / s + 1; 
						})
						.get();
			
			// 각 기능들마다 progress를 completionDay만큼 증진 시켜줌
			queue.forEach(e -> e.set(0, e.stream().reduce((p, s) -> p + (s * completionDay)).get()));
			
			// 각 progress를 검증한다. 100이 넘으면 배포한 것으로 처리
			boolean isDone = false;
			
			while(!isDone && !queue.isEmpty()) {
				if(queue.peek().get(0) >= 100) {
					queue.poll();
					count++;
				} else {
					isDone = true;
				}
			}
			
			// 한 번의 배포가 완료되었으므로 정답 리스트에 추가
			answer.add(count);
		}
		
	
		return answer.stream()
				.mapToInt(Integer::intValue).toArray();
    }
}