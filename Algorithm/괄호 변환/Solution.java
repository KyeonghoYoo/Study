class Solution {
	public String solution(String p) {
		String answer = "";
		answer = func(p);
		return answer;
	}

	public String func(String w) {
		// 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환한다.
		if (w.length() == 0) {
			return "";
		}
		int i = -1;
		int cnt = 0;

		String u = "";
		String v = "";
		// 2. 문자열 w를 "균형잡힌 괄호 문자열" 두 문자열 u, v로 분리한다.
		do {
			i++;
			cnt = w.charAt(i) == '(' ? cnt + 1 : cnt - 1;
		} while (cnt != 0);

		u = w.substring(0, i + 1);
		v = w.substring(i + 1);
		
		if (isValid(u)) {
			// 3. 문자열 u가 "올바른 괄호 문자열"일 경우 문자열 v에 대해 1단계부터 다시 수행한다.
			// 	3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환한다.
			return u + func(v);
		} else {
			// 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행한다.
			// 	4-1. 빈 문자열에 첫 번째 문자로 '('를 붙인다.
			//	4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙인다.
			// 	4-3. ')'를 다시 붙인다.
			// 	4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙인다. 
			//  4-5. 생성된 문자열을 반환한다.
			String temp = "(" + func(v) + ")";
			return temp + makeValid(u) ;
		}
	}

	public boolean isValid(String s) {
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			cnt = s.charAt(i) == '(' ? cnt + 1 : cnt - 1;
			if (cnt < 0) {
				return false;
			}
		}

		if (cnt == 0) {
			return true;
		} else {
			return false;
		}
	}

	public String makeValid(String s) {
		int cnt = 0;
		String result = "";
		// 첫 번째와 마지막 문자를 제거하기 위해 i는 1부터 s.length() -1 까지 조건을 준다.
		for (int i = 1; i < s.length() - 1; i++) {
			result = s.charAt(i) == '(' ? result + ")" : result + "(";
		}
		return result;
	}
}