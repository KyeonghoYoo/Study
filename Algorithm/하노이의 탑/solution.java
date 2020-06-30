import java.util.ArrayList;
import java.util.Arrays;

class solution {
	public int[][] solution(int n) {
		
		ArrayList<int[]> answer_list = new ArrayList<>();
		hanoi(answer_list, n, 1, 2, 3);
		int[][] answer = new int[answer_list.size()][2];
		for (int i = 0; i < answer_list.size(); i++) {
			answer[i] = answer_list.get(i);
		}
		return answer;
	}
	public void hanoi(ArrayList<int[]> answer_list, int n, int i, int j, int k) {
		if (n == 1) {
			int[] arr = { i, k };
			answer_list.add(arr);
			return;
		}
		hanoi(answer_list, n - 1, i, k, j);
		int[] arr = { i, k };
		answer_list.add(arr);
		hanoi(answer_list, n - 1, j, i, k);
		return;
	}
}