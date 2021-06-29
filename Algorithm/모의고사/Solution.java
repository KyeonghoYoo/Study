import java.util.Arrays;

public class Solution {
    public int[] solution(int[] answers) {
        int[] mathBoom1 = {1, 2, 3, 4, 5};
        int[] mathBoom2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] mathBoom3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; 
        
        int[][] hitCnt = {{1, 0}, {2, 0}, {3, 0}};
        
        for (int i = 0; i < answers.length; i++) {
        	if(mathBoom1[i % mathBoom1.length] == answers[i]) {
        		hitCnt[0][1]++;
        	}
        	if(mathBoom2[i % mathBoom2.length] == answers[i]) {
        		hitCnt[1][1]++;
        	}
        	if(mathBoom3[i % mathBoom3.length] == answers[i]) {
        		hitCnt[2][1]++;
        	}
		}
        
        int maxCnt = Arrays.stream(hitCnt)
        		.mapToInt(hc -> hc[1])
        		.max()
        		.getAsInt();
        
                
        return Arrays.stream(hitCnt)
        		.filter(hc -> hc[1] == maxCnt)
        		.mapToInt(hc -> hc[0])
        		.toArray();
    }
}
