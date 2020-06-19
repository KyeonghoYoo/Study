import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class solution {
    public int solution(String s) {
		int answer = 0;
		ArrayList<Integer> answers = new ArrayList<Integer>();

		for (int i = 1; i <= s.length() / 2; i++) {
			String str = "";
			int count = 1;
			Queue<String> queue = new LinkedList<String>();
			
			for (int j = 0; j < s.length(); j += i) {
				if(j + i > s.length()) {
					queue.offer(s.substring(j));
				} else {
					queue.offer(s.substring(j, j + i));
				}
			}
			
			for (int j = queue.size(); j > 0; j--) {
				String e = queue.poll();
				if (e.equals(queue.peek())) {
					count++;
				} else {
					str = str.concat((count != 1 ? count : "") + e);
					count = 1;
				}
			}
			answers.add(str.length());
		}
		answers.add(s.length());
		answer = answers.get(0);
		for (int e : answers) {
			if (answer > e) {
				answer = e;
			}
		}
		return answer;
    }
}