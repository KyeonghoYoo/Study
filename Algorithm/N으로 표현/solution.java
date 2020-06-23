import java.util.LinkedList;
import java.util.Queue;

public class solution {
    private static int answer = -1;
    
	public int solution(int N, int number) {
		recur(N, number, 0, String.valueOf(N));
		return answer;
	}
    
    private static void recur(int N, int number, int i, String result) {
		if (i >= 8) return;

		int sum = 0;
		String num = "";
		Queue<Character> queue = new LinkedList<Character>();
		
		for (int j = 0; j < result.length(); j++) {
			queue.offer(result.charAt(j));
		}

		while (!queue.isEmpty()) {
			Character e = queue.poll();
			switch (e) {
			case '+':
				sum += num != "" ? Integer.parseInt(num) : 0 + Integer.parseInt(String.valueOf(queue.poll()));
				num = "";
				break;
			case '-':
				sum -= num != "" ? Integer.parseInt(num) : 0 + Integer.parseInt(String.valueOf(queue.poll()));
				num = "";
				break;
			case '*':
				sum *= num != "" ? Integer.parseInt(num) : 0 + Integer.parseInt(String.valueOf(queue.poll()));
				num = "";
				break;
			case '/':
				sum /= num != "" ? Integer.parseInt(num) : 0 + Integer.parseInt(String.valueOf(queue.poll()));
				num = "";
				break;
			default:
				num += String.valueOf(e);
				break;
			}
		}

		if (sum == number) {
			answer = answer > i || answer == -1 ? i : answer;
			return;
		}

		recur(N, number, i + 1, result + N);
		recur(N, number, i + 1, result + "+" + N);
		recur(N, number, i + 1, result + "-" + N);
		recur(N, number, i + 1, result + "*" + N);
		recur(N, number, i + 1, result + "/" + N);
		return;
	}
}