# 구명보트

Created: Oct 7, 2020 11:07 PM
Tags: 프로그래머스

**문제 설명**

무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다. 구명보트는 작아서 한 번에 최대 **2명**씩 밖에 탈 수 없고, 무게 제한도 있습니다.

예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의 무게 제한이 100kg이라면 2번째 사람과 4번째 사람은 같이 탈 수 있지만 1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.

구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.

사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때, 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.

**제한사항**

- 무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
- 각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
- 구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
- 구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.

**입출력 예**

<table style="border-collapse: collapse; width: 100%;" border="1"><tbody><tr><td><span style="color: #333333;">people</span></td><td><span style="color: #333333;">limit</span></td><td><span style="color: #333333;">return</span></td></tr><tr><td>[70, 50, 80, 50]</td><td>100</td><td>3</td></tr><tr><td>[70, 80, 50]</td><td>100</td><td>3</td></tr></tbody></table>

---

**풀이 #**

```java
import java.util.Arrays;
 
class Solution {
    public int solution(int[] people, int limit) {
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
```

- 먼저 오름차순으로 정렬을 해준다.
- 가장 큰 값과 가장 작은 값을 검사하면서 limit 이하인 경우에 count한다.
    - limit 초과일 경우 그 다음 가장 큰 값을 다음 값으로 갱신한다
    - limit 이하인 경우에는 그 다음 가장 큰 값과 그 다음 가장 작은 값으로 둘 다 갱신한다.
- 반복문이 종료되면 count한 값을 사람 수에서 제외하여 반환한다.

이 문제는 최대한 효율적으로 그리디한 방식으로 해결하도록 유도한다. 나도 처음엔 배열 전체를 순회하는 식으로 풀었었는데, 정확성 테스트는 통과하여도 효율성 테스트에서 통과하지 못하였다. 문제를 해결하는데 있어 최소한의 요소만을 뽑아내는 능력이 중요한 알고리즘 유형이라고 생각된다.