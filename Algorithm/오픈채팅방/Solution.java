import java.util.*;

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