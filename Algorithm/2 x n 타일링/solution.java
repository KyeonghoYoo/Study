public class solution34 {
	int o = 0;
	int m = 0;
	public int solution(int n) {
		int[] memo = new int[60000];
		int answer = func(0, n, memo);
		return answer;
	}

	private int func(int width, int n, int[] memo) {
		if (width == n) {
			return 1;
		} else if (width > n) {
			return 0;
		}
		o++;
		if(memo[width] == 0) {
			memo[width] = (func(width + 1, n, memo) + func(width + 2, n, memo)) % 1000000007;
		}
		return memo[width];

	}

	private int fibo(int num) {
		if (num == 0) {
			return 0;
		} else if (num == 1) {
			return 1;
		}
		m++;
		return fibo(num - 1) + fibo(num - 2);
	}
}
