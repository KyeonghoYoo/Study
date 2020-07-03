import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public int solution(int[] priorities, int location) {
		int answer = 0;
		boolean is_print = false;
		boolean is_there_big = false;
		Queue<Integer> queue = new LinkedList<Integer>();
	
		for (int e : priorities) {
			queue.add(e);
		}
		
		while (!is_print) {
			is_there_big = false;
			int priority = queue.poll();
			for (int i = 0; i < queue.size(); i++) {
				int e = queue.poll();
				if (e > priority) {
					is_there_big = true;
				}
				queue.add(e);
			}
			if (is_there_big) {
				queue.add(priority);
				if (location == 0) {
					location = queue.size();
				}
			} else {
				answer++;
				if (location == 0) {
					is_print = true;
				}
			}
			location--;
		}

		return answer;
	}
}
