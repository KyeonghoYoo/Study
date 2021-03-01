import java.util.stream.IntStream;


public class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int w =(brown + yellow) / 2;
        int h = 2;
        
        while(h < brown + yellow) {
        	h += 1;
            w = (brown + yellow) / h;
        	
        	if(yellow == (w - 2) * (h - 2)) {
        		return IntStream.of(w, h).toArray();
        	}
        }
        
        return answer;
    }
}
