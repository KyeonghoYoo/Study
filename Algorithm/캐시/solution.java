import java.util.LinkedList;
import java.util.Queue;

public class solution {
	public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        
		int answer = 0;
		Queue<String> cache = new LinkedList<String>();

		for (String city : cities) {
			if (cache.size() < cacheSize) {
				if(cache.contains(city.toLowerCase())) {
					cache.remove(city.toLowerCase());
					cache.offer(city.toLowerCase());
					answer += 1;
				} else {
					cache.offer(city.toLowerCase());
					answer += 5;
				}
			} else if (cache.contains(city.toLowerCase())) {
				cache.remove(city.toLowerCase());
				cache.offer(city.toLowerCase());
				answer += 1;
			} else {
				cache.poll();
				cache.offer(city.toLowerCase());
				answer += 5;
			}
		}

		return answer;
	}
}
