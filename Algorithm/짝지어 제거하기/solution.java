import java.util.Stack;

public class solution {
	public static void main(String[] args) {
		System.out.println(solution2("baabaa"));
		System.out.println(solution2("cdcd"));
	}

	// 효율성 테스트 통과 X
	public static int solution(String s) {
		int j = 0;

		for (int i = s.length(); i != 0; i = s.length()) {
			if (j != i - 1 && s.charAt(j) == s.charAt(j + 1)) {
				s = s.replaceFirst("" + s.charAt(j) + s.charAt(j + 1), "");
				j = j - 1 < 0 || s.length() <= j ? 0 : j;
			} else if (j == i - 1) {
				return 0;
			} else {
				j += 1;
			}
		}
		return 1;
	}
	// 최종 답안
	public static int solution2(String s) {
		Stack<String> stack = new Stack<String>();
		
		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty()) {
				stack.push(String.valueOf(s.charAt(i)));
			} else if (String.valueOf(s.charAt(i)).equals(stack.peek())) {
				stack.pop();
			} else {
				stack.push(String.valueOf(s.charAt(i)));
			}
		}
		if (stack.isEmpty()) return 1;
		else return 0;
	}
}
