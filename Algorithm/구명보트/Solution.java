import java.util.Arrays;

class Solution {
	public  int solution(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);
		int cnt = 0;
		int front = 0;
		int back = people.length - 1;
		
		while(front < back) {
			int el1 = people[front];
			int el2 = people[back];
			
			if(el1 + el2 <= limit) {
				front += 1;
				cnt += 1;
			}
			back -= 1;
		}
		answer = people.length - cnt;
		return answer;
	}
}