import java.util.Arrays;

class solution {
	public long solution(int n, int[] times) {
		Arrays.sort(times);
		long answer = Long.MAX_VALUE;
		long left = 0;
		long right = (long) times[times.length - 1] * (long) n;
		long mid = 0;
		long passedCnt = 0;

		while (left < right) {
			passedCnt = 0;
			mid = (right + left) / 2;
			for (int time : times) {
				passedCnt += mid / time;
			}
			if (passedCnt >= n) {				
				answer = Long.min(answer, mid);
				right = mid;
			} else if(passedCnt < n) {
				left = mid + 1;
			}
		}
		return answer;
	}
}