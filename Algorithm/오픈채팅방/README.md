# 오픈채팅방

Created: Sep 2, 2020 3:17 PM

### **문제 설명**

# **오픈채팅방**

카카오톡 오픈채팅방에서는 친구가 아닌 사람들과 대화를 할 수 있는데, 본래 닉네임이 아닌 가상의 닉네임을 사용하여 채팅방에 들어갈 수 있다.

신입사원인 김크루는 카카오톡 오픈 채팅방을 개설한 사람을 위해, 다양한 사람들이 들어오고, 나가는 것을 지켜볼 수 있는 관리자창을 만들기로 했다. 채팅방에 누군가 들어오면 다음 메시지가 출력된다.

[닉네임]님이 들어왔습니다.

채팅방에서 누군가 나가면 다음 메시지가 출력된다.

[닉네임]님이 나갔습니다.

채팅방에서 닉네임을 변경하는 방법은 다음과 같이 두 가지이다.

- 채팅방을 나간 후, 새로운 닉네임으로 다시 들어간다.
- 채팅방에서 닉네임을 변경한다.

닉네임을 변경할 때는 기존에 채팅방에 출력되어 있던 메시지의 닉네임도 전부 변경된다.

예를 들어, 채팅방에 Muzi와 Prodo라는 닉네임을 사용하는 사람이 순서대로 들어오면 채팅방에는 다음과 같이 메시지가 출력된다.

Muzi님이 들어왔습니다.Prodo님이 들어왔습니다.

채팅방에 있던 사람이 나가면 채팅방에는 다음과 같이 메시지가 남는다.

Muzi님이 들어왔습니다.Prodo님이 들어왔습니다.Muzi님이 나갔습니다.

Muzi가 나간후 다시 들어올 때, Prodo 라는 닉네임으로 들어올 경우 기존에 채팅방에 남아있던 Muzi도 Prodo로 다음과 같이 변경된다.

Prodo님이 들어왔습니다.Prodo님이 들어왔습니다.Prodo님이 나갔습니다.Prodo님이 들어왔습니다.

채팅방은 중복 닉네임을 허용하기 때문에, 현재 채팅방에는 Prodo라는 닉네임을 사용하는 사람이 두 명이 있다. 이제, 채팅방에 두 번째로 들어왔던 Prodo가 Ryan으로 닉네임을 변경하면 채팅방 메시지는 다음과 같이 변경된다.

Prodo님이 들어왔습니다.Ryan님이 들어왔습니다.Prodo님이 나갔습니다.Prodo님이 들어왔습니다.

채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열 record가 매개변수로 주어질 때, 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return 하도록 solution 함수를 완성하라.

### 제한사항

- record는 다음과 같은 문자열이 담긴 배열이며, 길이는 `1` 이상 `100,000` 이하이다.
- 다음은 record에 담긴 문자열에 대한 설명이다.
    - 모든 유저는 [유저 아이디]로 구분한다.
    - [유저 아이디] 사용자가 [닉네임]으로 채팅방에 입장 -  (ex. )

        Enter [유저 아이디] [닉네임]

        Enter uid1234 Muzi

    - [유저 아이디] 사용자가 채팅방에서 퇴장 -  (ex. )

        Leave [유저 아이디]

        Leave uid1234

    - [유저 아이디] 사용자가 닉네임을 [닉네임]으로 변경 -  (ex. )

        Change [유저 아이디] [닉네임]

        Change uid1234 Muzi

    - 첫 단어는 Enter, Leave, Change 중 하나이다.
    - 각 단어는 공백으로 구분되어 있으며, 알파벳 대문자, 소문자, 숫자로만 이루어져있다.
    - 유저 아이디와 닉네임은 알파벳 대문자, 소문자를 구별한다.
    - 유저 아이디와 닉네임의 길이는 `1` 이상 `10` 이하이다.
    - 채팅방에서 나간 유저가 닉네임을 변경하는 등 잘못 된 입력은 주어지지 않는다.

### 입출력 예

<table style="border-collapse: collapse; width: 100%;" border="1"><tbody><tr><td><span style="color: #333333;">record</span></td><td><span style="color: #333333;">result</span></td></tr><tr><td>["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]</td><td>["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]</td></tr></tbody></table>
### 입출력 예 설명

입출력 예 #1문제의 설명과 같다.

---

**풀이 #**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    	public  String[] solution(String[] record) {
		String[] answer = {};
		// "userId%님이 들어왔습니다.", "userId%님이 나갔습니다."처럼 %를 구분자로 사용하여 userId와 뒤에 action을 Log로 저장하는 List
		List<String> logs = new ArrayList<String>();
		// 유저 정보를 담는 Map, Key는 유저 아이디이며 value는 닉네임을 저장한다.
		Map<String, String> userInfoMap = new HashMap<String, String>();

		for (String element : record) {
			String[] extractedElement = element.split(" ");
			String action = extractedElement[0];
			String userId = extractedElement[1];
			
			if (action.equals("Enter")) {
				String nickname = extractedElement[2];
				// 유저가 채팅방에 들어온 경우, logs에 해당 userId가 들어왔음을 추가한다.
				// 채팅방을 나가서 변경하여 들어온 경우에도 해당 userId를 key로 하여 기존 닉네임을 덮어쓴다.
				userInfoMap.put(userId, nickname);
				logs.add(userId + "%" + "님이 들어왔습니다.");
			} else if (action.equals("Leave")) {
				// 유저가 채딩방에서 나간 경우, logs에 해당 userId가 나갔음을 추가한다.
				logs.add(userId + "%" + "님이 나갔습니다.");
			} else {
				// 닉네임을 변경한 경우, 기존 userId의 nickname을 덮어 저장한다.
				String nickname = extractedElement[2];
				userInfoMap.put(userId, nickname);
			}
		}
		
		// logs를 Stream으로 변환한 후, map()을 통해 logs의 요소에 userId를 userInfoMap에 저장된 nickname으로 대체하여 Array로 변환한다.
		answer = logs.stream().map(e -> {
			String userId = e.substring(0, e.indexOf("%"));
			return e.replace(userId + "%", userInfoMap.get(userId));
		}).toArray(String[]::new);
		
		return answer;
    }
}
```