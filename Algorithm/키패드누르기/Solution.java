import static java.lang.Math.abs;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	public String solution(int[] numbers, String hand) {
		final String h = hand.equals("left") ? "L" : "R";
		
		String answer = "";

		Map<Integer, int[]> numbersPosMap = new HashMap<Integer, int[]>();
		for (int i = 0 ; i < 12; i++) {
			numbersPosMap.put(i + 1, new int[] {i / 3, i % 3});
		}
		numbersPosMap.put(0, numbersPosMap.get(11));

		int[] l_pos = numbersPosMap.get(10);
		int[] r_pos = numbersPosMap.get(12);
		
		for (int number : numbers) {
			switch (number) {
			case 1:
			case 4:
			case 7:
				answer = answer.concat("L");
				l_pos = numbersPosMap.get(number);
				break;
			case 3:
			case 6:
			case 9:
				answer = answer.concat("R");
				r_pos = numbersPosMap.get(number);
				break;
			default:
				int[] numberPos = numbersPosMap.get(number);
				
				int distanceLToN = abs(numberPos[0] - l_pos[0]) + abs(numberPos[1] - l_pos[1]);
				int distanceRToN = abs(numberPos[0] - r_pos[0]) + abs(numberPos[1] - r_pos[1]);
				
				int compareResult = Integer.compare(distanceLToN, distanceRToN);
				if(compareResult == 0) {
					if(hand.equals("left")) {
						answer = answer.concat("L");
						l_pos = numbersPosMap.get(number);
					} else {
						answer = answer.concat("R");
						r_pos = numbersPosMap.get(number);
					}
				} else if (compareResult < 0) {
					answer = answer.concat("L");
					l_pos = numbersPosMap.get(number);
				} else {
					answer = answer.concat("R");
					r_pos = numbersPosMap.get(number);
				}
				
				break;
			}
		}
		
		return answer;
	}
}
