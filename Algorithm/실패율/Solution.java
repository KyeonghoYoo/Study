import java.util.Arrays;
import java.util.Comparator;

public class Solution {
	public int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		Arrays.sort(stages);
		double[][] arr_stuckRate = new double[N][2];
		for(int i = 0; i < answer.length; i++) {
			answer[i] = i + 1;
		}

		int cnt = 0;
		for (int i = 0; i < stages.length; i++) {
			cnt++;
			if (i + 1 < stages.length) {
				if (stages[i] != stages[i + 1]) {
					double user_clear = stages.length - i + cnt - 1;
					answer[stages[i] - 1] = 0;
					arr_stuckRate[stages[i] - 1][0] = stages[i];
					arr_stuckRate[stages[i] - 1][1] = cnt / user_clear;
					cnt = 0;
				}
			} else if (stages[i] != N + 1) {
				answer[stages[i] - 1] = 0;
				double user_clear = stages.length - i + cnt - 1;
				arr_stuckRate[stages[i] - 1][0] = stages[i];
				arr_stuckRate[stages[i] - 1][1] = cnt / user_clear;
				cnt = 0;
			}
		}

		Arrays.sort(answer);
		Arrays.sort(arr_stuckRate, new Comparator<double[]>() {
			@Override
			public int compare(double[] o1, double[] o2) {
				return Double.compare(o2[1], o1[1]);
			}
		});

		for (int i = 0; i < arr_stuckRate.length; i++) {
			double[] ds = arr_stuckRate[i];
			if(ds[0] != 0) {
				answer[i] = (int)ds[0];
			} else {
				break;
			}
		}
		return answer;
	}
}
