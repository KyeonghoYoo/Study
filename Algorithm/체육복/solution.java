import java.util.LinkedList;


class solution {
    public int solution(int n, int[] lost, int[] reserve) {
		int answer = n - lost.length;
		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> list2 = new LinkedList<Integer>();

		for (int e : lost) {
			list.add(e);
		}
		for (int e : reserve) {
			list2.add(e);
		}

		// 도난당한 학생과 여벌 챙겨온 학생 중복시 제거
		for (Integer e : list) {
			if (list2.contains(e)) {
				list2.set(list2.indexOf(e), -1);
				list.set(list.indexOf(e), -1);
				answer++;
			}
		}

		for (Integer e : list) {
			if (list2.contains(Integer.valueOf(e.intValue() + 1))) {
				answer++;
				list2.remove(Integer.valueOf(e.intValue() + 1));
			} else if (list2.contains(Integer.valueOf(e.intValue() - 1))) {
				answer++;
				list2.remove(Integer.valueOf(e.intValue() - 1));
			}
		}

		return answer;
    }
}