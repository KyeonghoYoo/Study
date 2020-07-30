import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class solution {
    public int[] solution(String[] genres, int[] plays) {
		List<Integer> answer = new ArrayList<Integer>();

		Map<String, List<List<Integer>>> hm = new HashMap<String, List<List<Integer>>>();
		System.out.println("해쉬맵 삽입 시작");
		for (int i = 0; i < genres.length; i++) {
			String genre = genres[i];
			int play = plays[i];
			List<Integer> list = new ArrayList<Integer>();
			list.add(i);
			list.add(play);
			System.out.println("i = " + i + ", play = " + play);
			if (hm.containsKey(genre)) {
				hm.get(genre).add(list);
			} else {
				hm.put(genre, new ArrayList<List<Integer>>());
				hm.get(genre).add(list);
			}
		}
        System.out.println("해쉬맵 삽입 끝");
        
        System.out.println("장르별 곡 정렬 시작");
		for (String s : hm.keySet()) {
			hm.get(s).sort((o1, o2) -> o2.get(1) == o1.get(1) ? o1.get(0) - o2.get(0) : o2.get(1) - o1.get(1));
		}
        System.out.println("장르별 곡 정렬 끝");

		System.out.println("정답 삽입 시작");
		for (String s : hm.keySet().stream().sorted((o1, o2) -> {
			int sum1 = 0;
			int sum2 = 0;
			for (List<Integer> e : hm.get(o1)) {
				sum1 += e.get(1);
			}
			for (List<Integer> e : hm.get(o2)) {
				sum2 += e.get(1);
			}
			return sum2 - sum1;
		}).collect(Collectors.toList())) {
			List<List<Integer>> list = hm.get(s);
			int range = list.size() > 2 ? 2 : list.size();

			for (int i = 0; i < range; i++) {
				answer.add(list.get(i).get(0));
			}
		}
		System.out.println("정답 삽입 끝");

		return answer.stream().mapToInt(e -> e).toArray();
    }
}