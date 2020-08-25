# 징검다리 건너기

Created: Aug 24, 2020 8:32 PM

### **문제 설명**

**[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]**

카카오 초등학교의 니니즈 친구들이 라이언 선생님과 함께 가을 소풍을 가는 중에 **징검다리**가 있는 개울을 만나서 건너편으로 건너려고 합니다. 라이언 선생님은 니니즈 친구들이 무사히 징검다리를 건널 수 있도록 다음과 같이 규칙을 만들었습니다.

- 징검다리는 일렬로 놓여 있고 각 징검다리의 디딤돌에는 모두 숫자가 적혀 있으며 디딤돌의 숫자는 한 번 밟을 때마다 1씩 줄어듭니다.
- 디딤돌의 숫자가 0이 되면 더 이상 밟을 수 없으며 이때는 그 다음 디딤돌로 한번에 여러 칸을 건너 뛸 수 있습니다.
- 단, 다음으로 밟을 수 있는 디딤돌이 여러 개인 경우 무조건 가장 가까운 디딤돌로만 건너뛸 수 있습니다.

니니즈 친구들은 개울의 왼쪽에 있으며, 개울의 오른쪽 건너편에 도착해야 징검다리를 건넌 것으로 인정합니다.니니즈 친구들은 한 번에 한 명씩 징검다리를 건너야 하며, 한 친구가 징검다리를 모두 건넌 후에 그 다음 친구가 건너기 시작합니다.

디딤돌에 적힌 숫자가 순서대로 담긴 배열 stones와 한 번에 건너뛸 수 있는 디딤돌의 최대 칸수 k가 매개변수로 주어질 때, 최대 몇 명까지 징검다리를 건널 수 있는지 return 하도록 solution 함수를 완성해주세요.

### **[제한사항]**

- 징검다리를 건너야 하는 니니즈 친구들의 수는 무제한 이라고 간주합니다.
- stones 배열의 크기는 1 이상 200,000 이하입니다.
- stones 배열 각 원소들의 값은 1 이상 200,000,000 이하인 자연수입니다.
- k는 1 이상 stones의 길이 이하인 자연수입니다.

---

### **[입출력 예]**

<table style="border-collapse: collapse; width: 100%;" border="1"><tbody><tr><td><span style="color: #333333;">stones</span></td><td><span style="color: #333333;">k</span></td><td><span style="color: #333333;">result</span></td></tr><tr><td>[2, 4, 5, 3, 2, 1, 4, 2, 5, 1]</td><td>3</td><td>3</td></tr></tbody></table>

### **입출력 예에 대한 설명**

---

**입출력 예 #1**

첫 번째 친구는 다음과 같이 징검다리를 건널 수 있습니다.

![https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/4560e242-cf83-4e77-a14c-174f3831499d/step_stones_104.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/4560e242-cf83-4e77-a14c-174f3831499d/step_stones_104.png)

첫 번째 친구가 징검다리를 건넌 후 디딤돌에 적힌 숫자는 아래 그림과 같습니다.

두 번째 친구도 아래 그림과 같이 징검다리를 건널 수 있습니다.

![https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/d64f29ac-3e35-4fd3-91fa-4d70e3b6c80a/step_stones_101.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/d64f29ac-3e35-4fd3-91fa-4d70e3b6c80a/step_stones_101.png)

두 번째 친구가 징검다리를 건넌 후 디딤돌에 적힌 숫자는 아래 그림과 같습니다.

세 번째 친구도 아래 그림과 같이 징검다리를 건널 수 있습니다.

![https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/369bc8a1-7017-4135-a499-505247ab9cfc/step_stones_102.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/369bc8a1-7017-4135-a499-505247ab9cfc/step_stones_102.png)

세 번째 친구가 징검다리를 건넌 후 디딤돌에 적힌 숫자는 아래 그림과 같습니다.

네 번째 친구가 징검다리를 건너려면, 세 번째 디딤돌에서 일곱 번째 디딤돌로 네 칸을 건너뛰어야 합니다. 하지만 k = 3 이므로 건너뛸 수 없습니다.

![https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/e44e0a83-e637-48ad-858c-4c135c3b078f/step_stones_103.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/e44e0a83-e637-48ad-858c-4c135c3b078f/step_stones_103.png)

따라서 최대 3명이 디딤돌을 모두 건널 수 있습니다.

---

**풀이 1#** 정확성 테스트 통과, 효율성 테스트 불통과

```java
class Solution {
    public int solution(int[] stones, int k) {
		int answer = 0;
		boolean available = true;

		while (available) {
			int cnt = 0;

			for (int i = 0; i < stones.length; i++) {
				int el = stones[i];

				if (el == 0) {
					cnt += 1;
					if (cnt >= k) {
						available = false;
						break;
					}
				} else {
					stones[i] -= 1;
					cnt = 0;
				}
			}

			if (available) {
				answer += 1;
			}
		}
		return answer;
    }
}
```

- 문제에서 안내하는 내용대로 인원 한 명 한 명씩 돌다리를 건너게해서 답을 구하는 방식으로 알고리즘을 짰음.
- 답을 도출하는데는 문제가 없었으나, 건널 수 있는 인원이 늘어나면 늘어날수록 시간 비용이 많이듬.

**풀이 2#** 이분탐색으로 해결하여 효율성 테스트 통과

```java
import java.util.Arrays;
class Solution {
		int answer = Integer.MAX_VALUE;
		// 최대로 건널 수 있는 인원수를 이분탐색으로 구하기 위해 min은 1명 
		// max는 stones의 요소 중 건널 수 있는 횟수가 가장 큰 돌의 숫자를 넣는다.
		int min = 1;
		int max = Arrays.stream(stones).max().getAsInt();
		
		while (min < max) {
			// cnt는 넘어야 하는 돌의 수를 구하기위해,
			// mid는 min과 max의 가운데 값을 담는다.
			int cnt = 0;
			int mid = (min + max) / 2;
			
			// strema.map()을 활용해 각 돌의 건널 수 있는 횟수를 mid만큼 빼서 toArray()를 통해
			// Array로 바꿔준다.
			int[] stones2 = Arrays.stream(stones).map(e -> e - mid).toArray();
			
			// mid 인원수가 돌다리를 건널 수 있는지 검사
			for (int i = 0; i < stones2.length; i++) {
				int e = stones2[i];
				if(e <= 0) {
					cnt += 1;
					if(cnt >= k) {
						break;
					}
				} else {
					cnt = 0;
				}
			}
			
			if(cnt >= k) {
				// 건널 수 없다면 max값에 mid를 담아준다.
				max = mid;
				answer = max;
			} else {
				//건널 수 있다면 min에 min + 1을 담아준다.
				min = mid + 1;
				answer = min;
			}
		}
		
		// 이분탐색이 끝난 인원수를 반환한다.
		return answer;
	}
}
```

- min을 1명, max를 돌다리 중 가장 많이 건널 수 있는 횟수를 담아 이분탐색으로 최적의 인원을 찾아내는 방식으로 짰음.
- 풀이1에서 문제가 됐던 시간 비용이 해결되어 효율성 테스트까지 통과할 수 있었음.