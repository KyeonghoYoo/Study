import java.util.HashMap;
import java.util.Map;

class solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> hm = new HashMap<String, Integer>();
        
        for (String name : participant) {
			hm.put(name, 1);
		}
        return answer;
    }
}