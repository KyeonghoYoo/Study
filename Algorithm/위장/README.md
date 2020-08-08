# 위장

Created: Aug 9, 2020 5:30 AM
Tags: level 2, 프로그래머스

**문제 설명**

스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.

예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.

[Untitled](https://www.notion.so/15a5dfdf3eeb47c7a362c3806fdef506)

스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.

### 제한사항

- clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
- 스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
- 같은 이름을 가진 의상은 존재하지 않습니다.
- clothes의 모든 원소는 문자열로 이루어져 있습니다.
- 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
- 스파이는 하루에 최소 한 개의 의상은 입습니다.

### 입출력 예

[Untitled](https://www.notion.so/aa1063078ddc4882a948b75afb0fe099)

### 입출력 예 설명

예제 #1headgear에 해당하는 의상이 yellow_hat, green_turban이고 eyewear에 해당하는 의상이 blue_sunglasses이므로 아래와 같이 5개의 조합이 가능합니다.

```
1. yellow_hat
2. blue_sunglasses
3. green_turban
4. yellow_hat + blue_sunglasses
5. green_turban + blue_sunglasses
```

예제 #2face에 해당하는 의상이 crow_mask, blue_sunglasses, smoky_makeup이므로 아래와 같이 3개의 조합이 가능합니다.

```
1. crow_mask
2. blue_sunglasses
3. smoky_makeup
```

---

**풀이 #**

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	public int solution(String[][] clothes) {
		int answer = 0;
		Map<String, List<String>> hm = new HashMap<String, List<String>>();

		// 종류별로 HashMap에 List로 담는다.
		for (String[] el : clothes) {
			String key = el[1];
			String value = el[0];
			if (hm.containsKey(key)) {
				hm.get(key).add(value);
			} else {
				hm.put(key, new ArrayList<String>());
				hm.get(key).add(value);
			}
		}

		// keySet을 stream을 이용해서 Set->List로 바꿔준다.
		// 중간에 sorted()를 이용해 List 크기별로 내림차순 정렬을 해줌.
		List<String> sortedKeyList = hm.keySet()
				.stream()
				.sorted((o1, o2) -> hm.get(o2).size() - hm.get(o1).size())
				.collect(Collectors.toList());
		// dp 적용을 위한 HashMap 선언
		Map<String, Integer> memo = new HashMap<String, Integer>();

		for (int i = 0; i < sortedKeyList.size(); i++) {
			answer += func(i, 0, sortedKeyList, hm, memo);
		}

		return answer;
	}

	public int func(int i, int sum, List<String> sortedKeyList, Map<String, List<String>> hm,
			Map<String, Integer> memo) {
		String key = sortedKeyList.get(i);
		
		if(memo.containsKey(key)) {
			return memo.get(key);
		}
		
		List<String> list = hm.get(key);
		for (String string : list) {
			sum += 1;
			System.out.println(sum + ". " + string);
			for (int j = i + 1; j < sortedKeyList.size(); j++) {
				sum += func(j, 0, sortedKeyList, hm, memo);
			}
		}
		memo.put(key, sum);
		return sum;
	}
}
```