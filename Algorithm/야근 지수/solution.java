import java.util.Arrays;

class solution {
    public long solution(int n, int[] works) {
		long answer = 0;
		int index = works.length - 1;
        
        Arrays.sort(works);
        
		while (n > 0) {
			if (works[index] == 0)
				break;
			works[index] -= 1;
			n -= 1;
			for (int i = index;  i - 1 >= 0 && works[i] < works[i - 1] ; i--) {
				int temp = works[i];
				works[i] = works[i - 1];
				works[i - 1] = temp;
			}
		}
        
		for (int i : works) {
			answer += (int) Math.pow((double) i, 2);
		}

		return answer;
    }
}