public class Solution {
    public int[] solution(int n, int s) {
    	int[] answer = new int[n];
    	int division = s / n;
    	int remainder = s % n;
    	
       	if(division == 0) {
    		return new int[] {-1};
    	}
    	
    	int i = 0;
    	for (; i < n - remainder; i++) {
    		answer[i] = division;
		}
    	for(; i < n; i++) {
    		answer[i] = division + 1;
    	}
    	
        return answer;
    }
}
