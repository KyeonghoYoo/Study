public class solution {
	public String solution(int n) {
		String answer = "";
		int i = 1;
		int j = 3;
		int temp = 0;
		int cnt = 0;
		
		while (n > 0) {
			if (n % j != 0) {
				temp = n % j;
				n -= temp;
			} else if (n % j == 0) {
				n -= j;
				temp = j;
			}
			temp = temp / i;
			if (temp == 1) {
				answer = answer.concat("1");
			} else if (temp == 2) {
				answer = answer.concat("2");
			} else if (temp == 3) {
				answer = answer.concat("4");
			}
			i *= 3;
			j *= 3;
			cnt++;
		}
		answer = new StringBuffer(answer).reverse().toString();
		return answer;
	}
}
