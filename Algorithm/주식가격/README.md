# 주식가겨

Created: Aug 20, 2020 10:41 PM
Tags: 프로그래머스, level2

### **문제 설명**

초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

### 제한사항

- prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
- prices의 길이는 2 이상 100,000 이하입니다.

### 입출력 예

<table style="border-collapse: collapse; width: 100%;" border="1"><tbody><tr><td><span style="color: #333333;">prices</span></td><td><span style="color: #333333;">return</span></td></tr><tr><td>[1, 2, 3, 2, 3]</td><td>[4, 3, 1, 1, 0]</td></tr></tbody></table>

### 입출력 예 설명

- 1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
- 2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
- 3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
- 4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
- 5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.

※ 공지 - 2019년 2월 28일 지문이 리뉴얼되었습니다.

---

**풀이 #**

```java
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
```