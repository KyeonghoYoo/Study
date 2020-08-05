import java.util.Arrays;

class Solution {
	public int solution(int m, int n, int[][] puddles) {
		int answer = 0;
		// dp를 적용하기 위한 저장 배열
		int[][] memo = new int[n][m];
		// 오름차순 정렬
		Arrays.sort(puddles, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
		
		answer = func(0, 0, m, n, puddles, memo);

		return answer;
	}

	public int func(int i, int j, int m, int n, int[][] puddles, int[][] memo) {
		// 도착시 1 반환
		if (i == n - 1 && j == m - 1) {
			return 1;
		}
		if (memo[i][j] == 0) {
			// 물웅덩이를 지나는지 검사
			for (int[] is : puddles) {
				if (is[0] == j + 1 && is[1] == i + 1) {
					memo[i][j] = -1;
					return 0;
				}
			}
			//// 오버플로우 방지를 위해 1000000007로 나머지 연산
			// 아래로 이동
			if (i != n - 1) {
				memo[i][j] += func(i + 1, j, m, n, puddles, memo) % 1000000007;
			}
			//오른쪽으로 이동
			if (j != m - 1) {
				memo[i][j] += func(i, j + 1, m, n, puddles, memo) % 1000000007;
			}
		} else if (memo[i][j] == -1) {
			return 0;
		}
		return memo[i][j] % 1000000007;
	}
}