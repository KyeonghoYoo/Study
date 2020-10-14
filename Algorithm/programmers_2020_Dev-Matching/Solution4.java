import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// 데브매칭 4번 문제 풀이
public class Solution4 {
	public static void main(String[] args) throws ParseException {
		String[] votes = { "AVANT", "PRIDO", "SONATE", "RAIN", "MONSTER", "GRAND", "SONATE", "AVANT", "SONATE", "RAIN",
				"MONSTER", "GRAND", "SONATE", "SOULFUL", "AVANT", "SANTA" };
		String[] votes2 = {"AAD", "AAA", "AAC", "AAB"};
		System.out.println("solution1 ---");
		System.out.println(solution(votes, 2));
		System.out.println();
		System.out.println("solution1 ---");
		System.out.println(solution(votes2, 4));
		System.out.println();

	}

	public static String solution(String[] votes, int k) {
		String answer = "";
		HashMap<String, Integer> hm = new HashMap<>();
		
		for (String string : votes) {
			hm.put(string, hm.getOrDefault(string, 0) + 1);
		}
		List<String> keyList = hm.keySet().stream().sorted((o1, o2) -> {
			int voteCnt_o1 = hm.get(o1);
			int voteCnt_o2 = hm.get(o2);
			if(voteCnt_o1 == voteCnt_o2) {
				int i = 0;
				for(i = 0; i < (o1.length() <= o2.length() ? o1.length() : o2.length()) - 1; i++) {
					if(o1.charAt(i) != o2.charAt(i)) break;
				}
				return o1.charAt(i) != o2.charAt(i) ? o1.charAt(i) - o2.charAt(i) : o1.length() - o2.length();
			}
			return voteCnt_o2 - voteCnt_o1;
		}).collect(Collectors.toList());

		for (String string : keyList) {
			System.out.println(string + " = " + hm.get(string));
		}
		
		int sum = 0;
		for (int i = 0; i < k; i++) {
			String key = keyList.get(i);
			sum += hm.get(key);
		}
		System.out.println();
		System.out.println("sum = " + sum);
		System.out.println();
		int sum2 = 0;
		for (int i = keyList.size() - 1; i >= 0; i--) {
			String key = keyList.get(i);
			sum2 += hm.get(key);
			if(sum2 >= sum) {
				break;
			}
			answer = key;
		}
		return answer;
	}

}
