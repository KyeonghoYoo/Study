import java.util.ArrayDeque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public int[] solution(String[] operations) {
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		
		for(String oper : operations) {
			Integer operValue = Integer.valueOf(oper.substring(2));
			if(oper.startsWith("I")) {
				queue.add(operValue);
			} else {
				if(operValue.equals(1)) {
					queue.pollLast();
				} else {
					queue.pollFirst();
				}
			}
	
			queue = queue.stream()
							.sorted((i1, i2) -> i1 - i2)
							.collect(Collectors.toCollection(ArrayDeque<Integer>::new));
		}
		
		if(queue.isEmpty()) {
			return IntStream.of(0, 0).toArray();
		} else {
			return IntStream.of(queue.peekLast(), queue.peekFirst()).toArray();
		}
	}
}
