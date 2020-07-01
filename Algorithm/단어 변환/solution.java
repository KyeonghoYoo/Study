import java.util.Arrays;

public class solution {
	public int solution(String begin, String target, String[] words) {
		int answer = 0;
		Arrays.sort(words);

		answer = func(begin, target, words, 0);
		System.out.println("solution_answer = " + answer);

		return answer != Integer.MAX_VALUE ? answer : 0;
	}


	public int func(String begin, String target, String[] words, int i) {
		if (begin.equals(target))
			return i;
		int answer = Integer.MAX_VALUE;

		System.out.println("begin = " + begin);
		for (int j = 0; j < words.length; j++) {
			int hit_cnt = 0;
			int hit_cnt2 = 0;
			for (int k = 0; k < begin.length(); k++) {
				char c3 = words[j].charAt(k);
				char c1 = begin.charAt(k);
				char c2 = target.charAt(k);
				if (c1 == c3) {
					hit_cnt++;
				}
				if (c2 == c3) {
					hit_cnt2++;
				}

			}
			if (hit_cnt == begin.length() - 1) {
				if (i <= hit_cnt2) {
					System.out.println("words[" + j + "] = " + words[j] + ", i + 1 = " + (i + 1));
					int result = func(words[j], target, words, i + 1);
					answer = answer > result ? result : answer;
				}
			}
		}
		return answer != Integer.MAX_VALUE ? answer : Integer.MAX_VALUE;
	}
}