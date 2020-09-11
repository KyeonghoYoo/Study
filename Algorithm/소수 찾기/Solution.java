import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
	public int solution(String numbers) {
		int answer = 0;
		// 순서가 없고 중복을 허락하지 않는 자료구조인 Set을 활용하여 조합된 숫자를 담는다.
		Set<Integer> combinedNumSet = new HashSet<Integer>();
		
		for (int i = 0; i < numbers.length(); i++) {
			combinedNumSet.addAll(Arrays.stream(getCombinedNums(numbers.length(), "" + i).split(",")).map(e -> {
				String result = "";
				for (char ch : e.toCharArray()) {
					result += numbers.charAt(ch - '0');
				}
				return Integer.parseInt(result);
			}).collect(Collectors.toList()));
		}
        
        // strema의 filter()를 활용하여, Set에 담긴 숫자 중 소수인 것만 count하여 answer에 담는다.
		answer = (int) combinedNumSet.stream().filter(e -> isPrimeNum(e)).count();
		return answer;
	}
	// 각 숫자들을 이용하여 조합할 수 있는 모든 숫자를 "num1,num2,num3,num4,..."형태의 String으로 반환한다
	public String getCombinedNums(int length, String combinedStr) {
		String result = combinedStr;
		for (int i = 0; i < length; i++) {
			if (!combinedStr.contains("" + i)) {
				result += "," + getCombinedNums(length, combinedStr + i);
			}
		}
		return result;
	}
	
	// 입력값이 소수인지 판별하여 boolean 값을 반환하는 함수
	public boolean isPrimeNum(int num) {
		if (num <= 1)
			return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false; // 소수가 아니라면 false 리턴
		}
		return true; // 소수라면 true 리턴
	}
}