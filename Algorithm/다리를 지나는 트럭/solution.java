import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 1;

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int e : truck_weights) {
			queue.add(e);
		}
		List<int[]> arrayLists = new ArrayList<int[]>();

		int cur_weight = 0;
		while (!(queue.isEmpty() && arrayLists.isEmpty())) {
			try {
				if (cur_weight + queue.peek() <= weight) {
					int[] arr = { queue.peek(), 1 };
					arrayLists.add(arr);
					cur_weight += queue.poll();
				}
			} catch (Exception e) {

			}
			for (int i = 0; i < arrayLists.size(); i++) {
				int[] e = arrayLists.get(i);
				if (e[1] >= bridge_length) {
					cur_weight -= e[0];
					arrayLists.remove(i);
					i--;
				} else {
					e[1]++;
				}
			}
			answer++;	
		}
		return answer;
    }
}