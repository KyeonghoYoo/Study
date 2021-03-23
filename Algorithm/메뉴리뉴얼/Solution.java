import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
	public String[] solution(String[] orders, int[] course) {
		List<String> answerList = new ArrayList<>();

		HashMap<String, Integer> coursesCntMap = new HashMap<>();

		for (String order : orders) {
			char[] orderCharArray = order.toCharArray();
			Arrays.sort(orderCharArray);
			func(0, "", orderCharArray, course, coursesCntMap);
		}

		Set<String> keySet = coursesCntMap.keySet();

		for (int c : course) {
			List<String> keyList = keySet.stream()
					.filter(key -> key.length() == c)
					.collect(Collectors.toList());

			Optional<Integer> maxCntOpt = keyList.stream()
					.map(k -> coursesCntMap.get(k))
					.max(Integer::compareTo);

			if(maxCntOpt.isEmpty()) {
				continue;
			}
			
			Integer maxCnt = maxCntOpt.get();
			
			if(maxCnt <= 1) {
				continue;
			}
			
			List<String> forAddToAnswer = keyList.stream()
					.filter(k -> coursesCntMap.get(k) == maxCnt)
					.collect(Collectors.toList());

			answerList.addAll(forAddToAnswer);

		}

		answerList.sort(String::compareTo);
		return answerList.toArray(String[]::new);
	}

	public void func(int i, String combined, char[] orderCharArray, int[] course, Map<String, Integer> coursesCntMap) {
		for (int c : course) {
			if (combined.length() == c) {
				if (coursesCntMap.containsKey(combined)) {
					coursesCntMap.compute(combined, (k, v) -> v + 1);
				} else {
					coursesCntMap.put(combined, 1);
				}
			}
		}

		for (; i < orderCharArray.length; i++) {
			func(i + 1, combined + Character.toString(orderCharArray[i]), orderCharArray, course, coursesCntMap);
		}
	}
}
