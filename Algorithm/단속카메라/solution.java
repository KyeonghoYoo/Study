import java.util.Arrays;
import java.util.Comparator;

//단속카메라
public class solution {

	public static void main(String[] args) {
		int[][] routes = { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } };
		System.out.println(solution(routes));
	}

	public static int solution(int[][] routes) {
		int answer = 0;
		Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < routes.length; i++) {
			if (routes[i][1] < min || max < routes[i][0]) {
				answer++;
				min = routes[i][0];
				max = routes[i][1];
			} else {
				min = routes[i][0] > min ? routes[i][0] : min;
				max = routes[i][1] < max ? routes[i][1] : max;
			}
		}

		return answer;
	}
}
