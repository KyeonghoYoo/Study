import java.util.ArrayList;
import java.util.List;

class Solution {
	public int solution(String str1, String str2) {
		int answer = 0;
		// 자카드 유사도를 담기 위한 변수
		double jaccardSimilarity = 0;
		// 교집합을 담기 위한 List
		List<String> intersectionList = new ArrayList<String>();
		// 합집합을 담기 위한 List
		List<String> unionList = new ArrayList<String>();
		// 대소문자 구분을 하지 않기 위한 toLowerCase() 처리
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		List<String> list1 = makeStrList(str1);
		List<String> list2 = makeStrList(str2);
		// 합집합 list에 list2를 깊은 복사(deep copy)하여 넣는다.
		unionList.addAll(list2);
		for (String el : list1) {
			if (list2.contains(el)) {
				// list2에 포함된 글자 쌍이라면 교집합 list에 add()한다.
                // 그 후 list2에 해당 글자 쌍을 삭제한다.
				intersectionList.add(el);
                list2.remove(el);
			} else {
				// 포함되있지 않은 글자 쌍이라면 합집합 list에 add()한다.
				unionList.add(el);
			}
		}
        // 교집합의 수와 합집합의 수로 자카드 유사도를 계산하여 담는다.
		jaccardSimilarity = intersectionList.size() != unionList.size() ? (double) intersectionList.size() / (double) unionList.size() : 1;
		// 문제에서 안내한대로 자카드 유사도에 65536을 곱한 후, 정수로 바꿔 반환한다.
		answer = (int) (jaccardSimilarity * 65536);
        
		return answer;
	}
	// 문자열을 두 글자씩 끊어 다중집합을 만드는 함수
	public List<String> makeStrList(String str) {
		List<String> list = new ArrayList<String>();
		// 글자 쌍을 만들기 위한 변수
		String el = "";
		// 글자 쌍을 만들어 list에 추가하는 반복문
		for (char ch : str.toCharArray()) {
			if ('a' <= ch && ch <= 'z') {
				// ch가 영문자인 경우, 글자 쌍에 해당 문자를 붙인다.
				if (el.length() < 2) {
					el = el.concat(String.valueOf(ch));
				}
			} else {
				// ch가 영문자가 아닌 경우, 글자 쌍을 ""로 초기화한다.
				el = "";
			}
			if (2 <= el.length()) {
				// 글자 쌍이 두 문자로 완성된 경우, 리스트에 추가하고 두번째 문자만 남긴다.
				list.add(el);
				el = el.substring(1);
			}
		}
		return list;
	}
}