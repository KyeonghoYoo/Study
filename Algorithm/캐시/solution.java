import java.util.LinkedList;
import java.util.Queue;

public class solution {
	// 대소문자 구분 X
	public int solution1(int cacheSize, String[] cities) {
		int answer = 0;
		Queue<String> cache = new LinkedList<String>();

		for (String city : cities) {
			if (cache.size() < cacheSize) {
				cache.offer(city);
				answer += 5;
			} else if (cache.contains(city)) {
				cache.remove(city);
				cache.offer(city);
				answer += 1;
			} else {
				cache.poll();
				cache.offer(city);
				answer += 5;
			}
		}

		return answer;
	}

	// 대소문자 구분 O
	public int solution2(int cacheSize, String[] cities) {
		int answer = 0;
		Queue<String> cache = new LinkedList<String>();

		for (String city : cities) {
			if (cache.size() < cacheSize) {
				cache.offer(city);
				answer += 5;
			} else {
				boolean hit = false;
				for (int i = 0; i < cache.size(); i++) {
					String e = cache.poll();
					cache.offer(e);
					if (city.equalsIgnoreCase(e)) {
						hit = true;
					}
				}
				if (hit) {
					cache.remove(city);
					cache.offer(city);
					answer += 1;
				} else {
					cache.poll();
					cache.offer(city);
					answer += 5;
				}
			}
		}

		return answer;
	}
}
