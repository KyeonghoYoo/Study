import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author 유경호
 * @quiz 길찾기(다이아몬드)
 * @since 2020-07-31
 * @link https://level.goorm.io/exam/43145/%EA%B8%B8%EC%B0%BE%EA%B8%B0-%EB%8B%A4%EC%9D%B4%EC%95%84%EB%AA%AC%EB%93%9C/quiz/1
 **/
public class solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int inputInt = Integer.parseInt(input);
		String[] inputArr = new String[(inputInt * 2) - 1];
		int[][] inputIntArr = new int[inputArr.length][];
		// 다이아몬드 배열에 대한 input을 받는 로직
		for (int i = 0; i < inputArr.length; i++) {
			inputArr[i] = br.readLine();
			// 입력받은 String을 Stream을 활용해 int 배열로 변환해줌.
			inputIntArr[i] = Pattern.compile(" ").splitAsStream(inputArr[i]).mapToInt(e -> Integer.parseInt(e))
					.toArray();

		}

		// dp 구현을 위한 배열 선언과 각 크기 할당
		int[][] memo = new int[inputIntArr.length][];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = new int[inputIntArr[i].length];
		}

		System.out.println(dp(0, 0, inputInt, 0, inputIntArr, memo));
	}

	/*
	 * sum : 합 depth : 다이아몬드 가운데 지점의 index 
	 * i : 2차원 배열의 세로 index 
	 * j : 2차원 배열의 가로 index
	 * inputIntArr : 다이아몬드 배열 memo : dp 구현을 위한 memo 배열
	 */
	private static int dp(int i, int j, int depth, int sum, int[][] inputIntArr, int[][] memo) {
		if (j < 0 || j > inputIntArr[i].length - 1) {
			return 0;
		}
		if (i == (depth * 2) - 2) {
			return inputIntArr[i][j];
		}

		if (memo[i][j] != 0) {
			return memo[i][j];
		}
		if (i < depth - 1) {
			memo[i][j] = Math.max(inputIntArr[i][j] + dp(i + 1, j, depth, sum + inputIntArr[i][j], inputIntArr, memo),
					inputIntArr[i][j] + dp(i + 1, j + 1, depth, sum + inputIntArr[i][j], inputIntArr, memo));
		} else {
			memo[i][j] = Math.max(inputIntArr[i][j] + dp(i + 1, j, depth, sum + inputIntArr[i][j], inputIntArr, memo),
					inputIntArr[i][j] + dp(i + 1, j - 1, depth, sum + inputIntArr[i][j], inputIntArr, memo));
		}

		return memo[i][j];
	}
}
