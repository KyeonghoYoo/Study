import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public String solution(String number, int k) {
		String answer = "";
		int cnt = 0;
		List<Integer> number_charArray = number.chars().boxed().map(e -> e - 48).collect(Collectors.toList());
		int pre = Integer.MAX_VALUE;
		int size = number_charArray.size();

		for (int i = 0; i < size; i++) {
			int cur = number_charArray.get(i);

			if (pre < cur) {
				number_charArray.remove(i - 1);
				size = number_charArray.size();
				cnt++;
				if(k <= cnt) {
					break;
				}
				if(i - 2 < 0) {
					pre = Integer.MAX_VALUE;
					i -= 2;
					continue;
				} else {
					pre = number_charArray.get(i - 2);
					i -= 2;
					continue;
				}
			}
			pre = cur;
		}
        
        while(cnt < k) {
			number_charArray.remove(number_charArray.size() - 1);
			cnt++;
		}
        
		answer = number_charArray.stream().map(e -> String.valueOf(e)).collect(Collectors.joining());
		return answer;
    }
}