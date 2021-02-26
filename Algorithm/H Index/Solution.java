import java.util.Arrays;

public class Solution {
	public int solution(int[] citations) {
		int answer = 0;
		
		for(int i = 1; i <= citations.length; i++) {
			int condNum = i;
			long articleCount = Arrays.stream(citations)
					.filter(citation -> citation >= condNum)
					.count();
			
			if(articleCount >= i)
				answer = i > answer ? i : answer;
		}
		
		return answer;
	}
	
	public int solution2(int[] citations) {
		int answer = 0;
		
		for (int h : citations) {
			long articleCount = Arrays.stream(citations)
					.filter(c -> c >= h)
					.count();
			
			if(articleCount >= h) {
				answer = h > answer ? h : answer;
			}
		}
		
		return answer;
	}
}
