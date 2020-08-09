import java.util.HashMap;
import java.util.Map;

class Solution {
    	public int[] solution(int n, String[] words) {
		int[] answer = new int[2];
		int cnt = 0;
		Map<String, Integer> hm = new HashMap<String, Integer>();
		char startCh = 0;
		char endCh = words[0].charAt(0);

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			startCh = word.charAt(0);

			cnt = cnt % n == 0 ? 1 : cnt + 1;

			if (hm.containsKey(word) || startCh != endCh) {
				answer[0] = i % n == 0 ? 1 : (i % n) + 1;
				answer[1] = (i / n) + 1;
                return answer;
			} else {
				hm.put(word, 0);
			}
			endCh = word.charAt(word.length() - 1);
		}
		return answer;
	}
}