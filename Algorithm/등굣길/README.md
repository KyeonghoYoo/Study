# 등굣길

Created: Aug 5, 2020 6:03 PM

### **문제 설명**

계속되는 폭우로 일부 지역이 물에 잠겼습니다. 물에 잠기지 않은 지역을 통해 학교를 가려고 합니다. 집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다.

아래 그림은 m = 4, n = 3 인 경우입니다.

![https://grepp-programmers.s3.amazonaws.com/files/ybm/056f54e618/f167a3bc-e140-4fa8-a8f8-326a99e0f567.png](https://grepp-programmers.s3.amazonaws.com/files/ybm/056f54e618/f167a3bc-e140-4fa8-a8f8-326a99e0f567.png)

가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1, 1)로 나타내고 가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)으로 나타냅니다.

격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles이 매개변수로 주어집니다. 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.

### 제한사항

- 격자의 크기 m, n은 1 이상 100 이하인 자연수입니다.
    - m과 n이 모두 1인 경우는 입력으로 주어지지 않습니다.
- 물에 잠긴 지역은 0개 이상 10개 이하입니다.
- 집과 학교가 물에 잠긴 경우는 입력으로 주어지지 않습니다.

### 입출력 예

<table style="border-collapse: collapse; width: 100%;" border="1"><tbody><tr><td><span style="color: #333333;">m</span></td><td><span style="color: #333333;">n</span></td><td><span style="color: #333333;">puddles</span></td><td><span style="color: #333333;">return</span></td></tr><tr><td>4</td><td>3</td><td>[[2, 2]]</td><td>4</td></tr></tbody></table>

### 입출력 예 설명

![https://grepp-programmers.s3.amazonaws.com/files/ybm/32c67958d5/729216f3-f305-4ad1-b3b0-04c2ba0b379a.png](https://grepp-programmers.s3.amazonaws.com/files/ybm/32c67958d5/729216f3-f305-4ad1-b3b0-04c2ba0b379a.png)

---

**풀이 #**

```java
import java.util.Arrays;

class Solution {
	public int solution(int m, int n, int[][] puddles) {
		int answer = 0;
		// dp를 적용하기 위한 저장 배열
		int[][] memo = new int[n][m];
		// 오름차순 정렬
		Arrays.sort(puddles, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
		answer = func(0, 0, m, n, puddles, memo);

		return answer;
	}

	public int func(int i, int j, int m, int n, int[][] puddles, int[][] memo) {
		if (i == n - 1 && j == m - 1) {
			// 도착시 1 반환
			return 1;
		}
		if (memo[i][j] == 0) {
			// 물웅덩이를 지나는지 검사
			for (int[] is : puddles) {
				if (is[0] == j + 1 && is[1] == i + 1) {
					memo[i][j] = -1;
					return 0;
				}
			}
			//// 오버플로우 방지를 위해 1000000007로 나머지 연산
            // 아래로 이동
			if (i != n - 1) {
				memo[i][j] += func(i + 1, j, m, n, puddles, memo) % 1000000007;
			}
			//오른쪽으로 이동
			if (j != m - 1) {
				memo[i][j] += func(i, j + 1, m, n, puddles, memo) % 1000000007;
			}
		} else if (memo[i][j] == -1) {
			// 물웅덩이 0 반환
			return 0;
		}
		return memo[i][j] % 1000000007;
	}
}
```