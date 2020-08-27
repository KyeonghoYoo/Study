import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
	String[] answer = new String[files.length];
		answer = Arrays.stream(files).sorted((o1, o2) -> {
			// 1. HEAD 부분을 기준으로 사전 순으로 정렬한다.
			// 대소문자 구분을 하지 않기 위한 toLowerCase() 처리
			o1 = o1.toLowerCase();
			o2 = o2.toLowerCase();
			char[] o1CharArr = o1.toCharArray();
			char[] o2CharArr = o2.toCharArray();
			
			String o1HEAD = "";
			String o2HEAD = "";
			
			String o1NUMBER = "";
			String o2NUMBER = "";
			
			// o1의 HEAD 추출 처리
			for (int i = 0; i < o1CharArr.length; i++) {
				char o1Char = o1CharArr[i];
				if (('0' <= o1Char && o1Char <= '9')) {
					o1HEAD = o1.substring(0, i);
					o1NUMBER = o1.substring(i);
					break;
				}
			}
			// o2의 HEAD 추출 처리
			for (int i = 0; i < o2CharArr.length; i++) {
				char o2Char = o2CharArr[i];
				if (('0' <= o2Char && o2Char <= '9')) {
					o2HEAD = o2.substring(0, i);
					o2NUMBER = o2.substring(i);
					break;
				}
			}
			
			// o1의 NUMBER 추출 처리
			for (int i = 0; i < o1NUMBER.length(); i++) {
				char o1Char = o1NUMBER.charAt(i);
				if (!('0' <= o1Char && o1Char <= '9')) {
					o1NUMBER = o1NUMBER.substring(0, i);
					break;
				}
			}
			
			// o2의 NUMBER 추출 처리
			for (int i = 0; i < o2NUMBER.length(); i++) {
				char o2Char = o2NUMBER.charAt(i);
				if (!('0' <= o2Char && o2Char <= '9')) {
					o2NUMBER = o2NUMBER.substring(0, i);
					break;
				}
			}
			if(!o1HEAD.equals(o2HEAD)) {				
				// 각 HEAD가 서로 같지 않다면 사전순으로 정렬한다.
				return o1HEAD.compareTo(o2HEAD);
			} else {
				// HEAD가 같다면 각 NUMBER를 비교하여 오름차순으로 정렬한다.
				return Integer.parseInt(o1NUMBER) - Integer.parseInt(o2NUMBER);
			}			
		}).toArray(String[]::new);
		return answer;
    }
}