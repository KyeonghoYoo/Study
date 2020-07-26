import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
class solution {
    public String solution(String sentence) {
		String answer = "";
		char[] arr_chars = sentence.toCharArray();
		// invalid 검사용 arr_chars 복사본
		char[] copyArr_chars = Arrays.copyOf(arr_chars, arr_chars.length);
		Map<Character, List<Integer>> hm = new HashMap<Character, List<Integer>>();
		for (int i = 0; i < arr_chars.length; i++) {
			char c = arr_chars[i];
			if (97 <= c && c <= 122) {
				if (hm.containsKey(c)) {
					hm.get(c).add(i);
				} else {
					List<Integer> list = new ArrayList<Integer>();
					list.add(i);
					hm.put(c, list);
				}
			}
		}
		Set<Integer> set_index = new HashSet<Integer>();
		for (Character c : hm.keySet()) {
			List<Integer> list = hm.get(c);
			// list.size()의 값이 2면 규칙2에 해당하는 문자, 그렇지 않으면 규칙1에 해당하는 문자
			if (list.size() == 2) {
				if(list.get(0) - 1 >= 0)
					set_index.add(list.get(0) - 1);
				if(list.get(1) - 1 < arr_chars.length - 1)
					set_index.add(list.get(1) - 1);
				list.forEach(el -> {
					arr_chars[el] = 0;
				});
			} else {
				if(list.get(0) - 2 >= 0)
					set_index.add(list.get(0) - 2);
				if(list.get(list.size() - 1) < arr_chars.length - 1)
					set_index.add(list.get(list.size() - 1) + 1);
				for (int i = 0; i < list.size(); i++) {
					Integer el = list.get(i);
					if (el - 2 >= 0 && el + 2 < arr_chars.length) {
						if (c != copyArr_chars[el - 2] && hm.containsKey(copyArr_chars[el - 2])) {
							if (hm.get(copyArr_chars[el - 2]).size() != 2) {
								return "invalid";
							}
						}
						if (c != copyArr_chars[el + 2] && hm.containsKey(copyArr_chars[el + 2])) {
							if (hm.get(copyArr_chars[el + 2]).size() != 2) {
								return "invalid";
							}
						}
					}
					arr_chars[el] = 0;
				}
			}
		}
		boolean isContinue = false;
		for (int i = 0; i < arr_chars.length; i++) {
			char c = arr_chars[i];
			if (c != 0) {
				answer = answer.concat(String.valueOf(c));
				if(set_index.contains(i) && i < arr_chars.length - 2)
					answer = answer.concat(" ");
			}
		}
		System.out.println(answer);
		return answer;
    }
}