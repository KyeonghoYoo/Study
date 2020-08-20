import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];
		// 주식가격이 떨어지지 않은 기간을 셀 변수 선언
		int cnt = 0;
		Stack<List<Integer>> stack = new Stack<>();
		
		for (int i = 0; i < prices.length; i++) {
			// (i + 1)초 시점의 주식가격을 담을 변수를 선언하여 담아준다.
			int price = prices[i];
			
			// (i + 1)초 시점과 해당 시점의 주식가격을 새 리스트로 선언한다.
			List<Integer> list = new ArrayList<Integer>();
			list.add(i);
			list.add(price);
			if (stack.isEmpty()) {
				// 스택이 빈 경우에는 스택에 리스트를 push()한다.
				stack.push(list);
			} else if (stack.peek().get(1) <= price) {
				// stack의 제일 위에 있는 요소의 주식가격이 현재 주식가격과 같거나 작을 경우에는 주식가격이 떨어지지 않았으므로
				// 현재 시점과 주식가격을 push()한다.
				stack.push(list);
			} else {
				// 그 외의 경우에는 주식가격이 떨어졌음을 의미하므로
                // 가격이 떨어진 시점과 현지 서점의 차를 게산하여 answer[]에 담아준다.
				while (!stack.isEmpty() && stack.peek().get(1) > price) {
					List<Integer> el = stack.pop();
					answer[el.get(0)] = i - el.get(0); 
				}
				// 그 후 stack에 해당 list를 push()한다.
				stack.push(list);
			}
		}
		
		// stack에 요소가 남아있으면 끝까지 가격이 떨어지지 않은 주식가격들이므로
		// 모두 계산하여 answer[]에 담아주고 반환하여 끝낸다.
		while(!stack.isEmpty()) {
			List<Integer> el = stack.pop();
			answer[el.get(0)] = (prices.length - 1) - el.get(0); 
		}
		return answer;
	}
}