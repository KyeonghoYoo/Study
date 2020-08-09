# 영어 끝말잇기

Created: Aug 9, 2020 11:44 PM

### **문제 설명**

1부터 n까지 번호가 붙어있는 n명의 사람이 영어 끝말잇기를 하고 있습니다. 영어 끝말잇기는 다음과 같은 규칙으로 진행됩니다.

1. 1번부터 번호 순서대로 한 사람씩 차례대로 단어를 말합니다.
2. 마지막 사람이 단어를 말한 다음에는 다시 1번부터 시작합니다.
3. 앞사람이 말한 단어의 마지막 문자로 시작하는 단어를 말해야 합니다.
4. 이전에 등장했던 단어는 사용할 수 없습니다.
5. 한 글자인 단어는 인정되지 않습니다.

다음은 3명이 끝말잇기를 하는 상황을 나타냅니다.

tank → kick → know → wheel → land → dream → mother → robot → tank

위 끝말잇기는 다음과 같이 진행됩니다.

- 1번 사람이 자신의 첫 번째 차례에 tank를 말합니다.
- 2번 사람이 자신의 첫 번째 차례에 kick을 말합니다.
- 3번 사람이 자신의 첫 번째 차례에 know를 말합니다.
- 1번 사람이 자신의 두 번째 차례에 wheel을 말합니다.
- (계속 진행)

끝말잇기를 계속 진행해 나가다 보면, 3번 사람이 자신의 세 번째 차례에 말한 tank 라는 단어는 이전에 등장했던 단어이므로 탈락하게 됩니다.

사람의 수 n과 사람들이 순서대로 말한 단어 words 가 매개변수로 주어질 때, 가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지를 구해서 return 하도록 solution 함수를 완성해주세요.

### 제한 사항

- 끝말잇기에 참여하는 사람의 수 n은 2 이상 10 이하의 자연수입니다.
- words는 끝말잇기에 사용한 단어들이 순서대로 들어있는 배열이며, 길이는 n 이상 100 이하입니다.
- 단어의 길이는 2 이상 50 이하입니다.
- 모든 단어는 알파벳 소문자로만 이루어져 있습니다.
- 끝말잇기에 사용되는 단어의 뜻(의미)은 신경 쓰지 않으셔도 됩니다.
- 정답은 [ 번호, 차례 ] 형태로 return 해주세요.
- 만약 주어진 단어들로 탈락자가 생기지 않는다면, [0, 0]을 return 해주세요.

---

### 입출력 예

<table style="border-collapse: collapse; width: 100%;" border="1"><tbody><tr><td><span style="color: #333333;">n</span></td><td><span style="color: #333333;">words</span></td><td><span style="color: #333333;">result</span></td></tr><tr><td>3</td><td>[tank,<span>&nbsp;</span>kick,<span>&nbsp;</span>know,<span>&nbsp;</span>wheel,<span>&nbsp;</span>land,<span>&nbsp;</span>dream,<span>&nbsp;</span>mother,<span>&nbsp;</span>robot,<span>&nbsp;</span>tank]</td><td>[3,3]</td></tr><tr><td>5</td><td>[hello,<span>&nbsp;</span>observe,<span>&nbsp;</span>effect,<span>&nbsp;</span>take,<span>&nbsp;</span>either,<span>&nbsp;</span>recognize,<span>&nbsp;</span>encourage,<span>&nbsp;</span>ensure,<span>&nbsp;</span>establish,<span>&nbsp;</span>hang,<span>&nbsp;</span>gather,<span>&nbsp;</span>refer,<span>&nbsp;</span>reference,<span>&nbsp;</span>estimate,<span>&nbsp;</span>executive]</td><td>[0,0]</td></tr><tr><td>2</td><td>[hello,<span>&nbsp;</span>one,<span>&nbsp;</span>even,<span>&nbsp;</span>never,<span>&nbsp;</span>now,<span>&nbsp;</span>world,<span>&nbsp;</span>draw]</td><td>[1,3]</td></tr></tbody></table>

### 입출력 예 설명

입출력 예 #13명의 사람이 끝말잇기에 참여하고 있습니다.

- 1번 사람 : tank, wheel, mother
- 2번 사람 : kick, land, robot
- 3번 사람 : know, dream, `tank`

와 같은 순서로 말을 하게 되며, 3번 사람이 자신의 세 번째 차례에 말한 `tank`라는 단어가 1번 사람이 자신의 첫 번째 차례에 말한 `tank`와 같으므로 3번 사람이 자신의 세 번째 차례로 말을 할 때 처음 탈락자가 나오게 됩니다.

입출력 예 #25명의 사람이 끝말잇기에 참여하고 있습니다.

- 1번 사람 : hello, recognize, gather
- 2번 사람 : observe, encourage, refer
- 3번 사람 : effect, ensure, reference
- 4번 사람 : take, establish, estimate
- 5번 사람 : either, hang, executive

와 같은 순서로 말을 하게 되며, 이 경우는 주어진 단어로만으로는 탈락자가 발생하지 않습니다. 따라서 [0, 0]을 return하면 됩니다.

입출력 예 #32명의 사람이 끝말잇기에 참여하고 있습니다.

- 1번 사람 : hello, even, `now`, draw
- 2번 사람 : one, never, world

와 같은 순서로 말을 하게 되며, 1번 사람이 자신의 세 번째 차례에 'r'로 시작하는 단어 대신, n으로 시작하는 `now`를 말했기 때문에 이때 처음 탈락자가 나오게 됩니다.

---

풀이 #

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    	public int[] solution(int n, String[] words) {
		int[] answer = new int[2];
		int cnt = 0;
		Map<String, Integer> hm = new HashMap<String, Integer>();
		char startCh = 0;
		char endCh = words[0].charAt(0);

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			startCh = word.charAt(0);

			cnt = cnt % n == 0 ? 1 : cnt + 1;

			if (hm.containsKey(word) || startCh != endCh) {
				answer[0] = i % n == 0 ? 1 : (i % n) + 1;
				answer[1] = (i / n) + 1;
                return answer;
			} else {
				hm.put(word, 0);
			}
			endCh = word.charAt(word.length() - 1);
		}
		return answer;
	}
}
```