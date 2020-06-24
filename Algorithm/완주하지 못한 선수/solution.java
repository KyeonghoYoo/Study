import java.util.HashMap;
import java.util.Map;

class solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> hm = new HashMap<String, Integer>();
        
        for (String name : participant) {
			if(hm.containsKey(name)) {
	        	hm.put(name, hm.get(name) + 1);
			} else {
				hm.put(name, 1);
			}
		}
        for (String name : completion) {
        	if(hm.containsKey(name)) {
    			hm.put(name, hm.get(name) - 1);
        	}
		}
        for (String o : hm.keySet()) {
			answer = hm.get(o) == 1 ? o : answer;
		}
        return answer;
    }
}