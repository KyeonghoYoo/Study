import java.util.HashSet;
import java.util.Set;

class Solution {
public int[] solution(int[] numbers) {
        int[] answer = {};
        
        // 순서가 존재하지 않고 중복을 허락하지 않는 자료구조인 Set을 활용하여, 중복을 없앤다.
        Set<Integer> sumSet = new HashSet<Integer>();
        
        for (int i = 0; i < numbers.length; i++) {
			int num1 = numbers[i];
			for (int j = 0; j < numbers.length; j++) {
				// 같은 요소에 담긴 수끼리는 더 하지 않도록 조건문으로 처리한다.
				if(i != j) {
					// 같은 인덱스에 담기지 않은 num 끼리 더하여 sumSet에 추가한다.
					int num2 = numbers[j];
					sumSet.add(num1 + num2);
				}
			}
		}
        // sumSet을 stream으로 변환하여 sorted()를 통해 오름차순으로 정렬해준 뒤,
        // mapToInt()로 Integer -> int 처리 후, Array로 변환하여 answer에 담아 반환한다.
        answer = sumSet.stream().sorted((o1, o2) -> o1 - o2).mapToInt(e -> e.intValue()).toArray();
        return answer;
    }
}