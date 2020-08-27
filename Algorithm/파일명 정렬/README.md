# [3차] 파일명 정렬

Created: Aug 27, 2020 10:21 PM

Link : [https://programmers.co.kr/learn/courses/30/lessons/17686](https://programmers.co.kr/learn/courses/30/lessons/17686)

### **문제 설명**

# **파일명 정렬**

세 차례의 코딩 테스트와 두 차례의 면접이라는 기나긴 블라인드 공채를 무사히 통과해 카카오에 입사한 무지는 파일 저장소 서버 관리를 맡게 되었다.

저장소 서버에는 프로그램의 과거 버전을 모두 담고 있어, 이름 순으로 정렬된 파일 목록은 보기가 불편했다. 파일을 이름 순으로 정렬하면 나중에 만들어진 ver-10.zip이 ver-9.zip보다 먼저 표시되기 때문이다.

버전 번호 외에도 숫자가 포함된 파일 목록은 여러 면에서 관리하기 불편했다. 예컨대 파일 목록이 [img12.png, img10.png, img2.png, img1.png]일 경우, 일반적인 정렬은 [img1.png, img10.png, img12.png, img2.png] 순이 되지만, 숫자 순으로 정렬된 [img1.png, img2.png, img10.png, img12.png"] 순이 훨씬 자연스럽다.

무지는 단순한 문자 코드 순이 아닌, 파일명에 포함된 숫자를 반영한 정렬 기능을 저장소 관리 프로그램에 구현하기로 했다.

소스 파일 저장소에 저장된 파일명은 100 글자 이내로, 영문 대소문자, 숫자, 공백(" ), 마침표(.), 빼기 부호(")만으로 이루어져 있다. 파일명은 영문자로 시작하며, 숫자를 하나 이상 포함하고 있다.

파일명은 크게 HEAD, NUMBER, TAIL의 세 부분으로 구성된다.

- HEAD는 숫자가 아닌 문자로 이루어져 있으며, 최소한 한 글자 이상이다.
- NUMBER는 한 글자에서 최대 다섯 글자 사이의 연속된 숫자로 이루어져 있으며, 앞쪽에 0이 올 수 있다. `0`부터 `99999` 사이의 숫자로, `00000`이나 `0101` 등도 가능하다.
- TAIL은 그 나머지 부분으로, 여기에는 숫자가 다시 나타날 수도 있으며, 아무 글자도 없을 수 있다.

<table style="border-collapse: collapse; width: 100%; height: 76px;" border="1"><tbody><tr style="height: 19px;"><td style="height: 19px;"><span style="color: #333333;">파일명</span></td><td style="height: 19px;"><span style="color: #333333;">HEAD</span></td><td style="height: 19px;"><span style="color: #333333;">NUMBER</span></td><td style="height: 19px;"><span style="color: #333333;">TAIL</span></td></tr><tr style="height: 19px;"><td style="height: 19px;">foo9.txt</td><td style="height: 19px;">foo</td><td style="height: 19px;">9</td><td style="height: 19px;">.txt</td></tr><tr style="height: 19px;"><td style="height: 19px;">foo010bar020.zip</td><td style="height: 19px;">foo</td><td style="height: 19px;">010</td><td style="height: 19px;">bar020.zip</td></tr><tr style="height: 19px;"><td style="height: 19px;">F-15</td><td style="height: 19px;">F-</td><td style="height: 19px;">15</td><td style="height: 19px;">(빈 문자열)</td></tr></tbody></table>

파일명을 세 부분으로 나눈 후, 다음 기준에 따라 파일명을 정렬한다.

- 파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬한다. 이때, 문자열 비교 시 대소문자 구분을 하지 않는다. `MUZI`와 `muzi`, `MuZi`는 정렬 시에 같은 순서로 취급된다.
- 파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우, NUMBER의 숫자 순으로 정렬한다. 9 < 10 < 0011 < 012 < 13 < 014 순으로 정렬된다. 숫자 앞의 0은 무시되며, 012와 12는 정렬 시에 같은 같은 값으로 처리된다.
- 두 파일의 HEAD 부분과, NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지한다. `MUZI01.zip`과 `muzi1.png`가 입력으로 들어오면, 정렬 후에도 입력 시 주어진 두 파일의 순서가 바뀌어서는 안 된다.

무지를 도와 파일명 정렬 프로그램을 구현하라.

### **입력 형식**

입력으로 배열 `files`가 주어진다.

- `files`는 1000 개 이하의 파일명을 포함하는 문자열 배열이다.
- 각 파일명은 100 글자 이하 길이로, 영문 대소문자, 숫자, 공백(" .")만으로 이루어져 있다. 파일명은 영문자로 시작하며, 숫자를 하나 이상 포함하고 있다.

    ), 마침표(

    ), 빼기 부호(

- 중복된 파일명은 없으나, 대소문자나 숫자 앞부분의 0 차이가 있는 경우는 함께 주어질 수 있다. (`muzi1.txt`, `MUZI1.txt`, `muzi001.txt`, `muzi1.TXT`는 함께 입력으로 주어질 수 있다.)

### **출력 형식**

위 기준에 따라 정렬된 배열을 출력한다.

### **입출력 예제**

입력: ["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"]

출력: ["img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"]

입력: ["F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"]

출력: ["A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"]

---

**풀이 #**

```java
import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
	String[] answer = new String[files.length];
		answer = Arrays.stream(files).sorted((o1, o2) -> {
			// 1. HEAD 부분을 기준으로 사전 순으로 정렬한다.
			// 대소문자 구분을 하지 않기 위한 toLowerCase() 처리
			o1 = o1.toLowerCase();
			o2 = o2.toLowerCase();
			char[] o1CharArr = o1.toCharArray();
			char[] o2CharArr = o2.toCharArray();
			
			String o1HEAD = "";
			String o2HEAD = "";
			
			String o1NUMBER = "";
			String o2NUMBER = "";
			
			// o1의 HEAD 추출 처리
			for (int i = 0; i < o1CharArr.length; i++) {
				char o1Char = o1CharArr[i];
				if (('0' <= o1Char && o1Char <= '9')) {
					o1HEAD = o1.substring(0, i);
					o1NUMBER = o1.substring(i);
					break;
				}
			}
			// o2의 HEAD 추출 처리
			for (int i = 0; i < o2CharArr.length; i++) {
				char o2Char = o2CharArr[i];
				if (('0' <= o2Char && o2Char <= '9')) {
					o2HEAD = o2.substring(0, i);
					o2NUMBER = o2.substring(i);
					break;
				}
			}
			
			// o1의 NUMBER 추출 처리
			for (int i = 0; i < o1NUMBER.length(); i++) {
				char o1Char = o1NUMBER.charAt(i);
				if (!('0' <= o1Char && o1Char <= '9')) {
					o1NUMBER = o1NUMBER.substring(0, i);
					break;
				}
			}
			
			// o2의 NUMBER 추출 처리
			for (int i = 0; i < o2NUMBER.length(); i++) {
				char o2Char = o2NUMBER.charAt(i);
				if (!('0' <= o2Char && o2Char <= '9')) {
					o2NUMBER = o2NUMBER.substring(0, i);
					break;
				}
			}
			if(!o1HEAD.equals(o2HEAD)) {				
				// 각 HEAD가 서로 같지 않다면 사전순으로 정렬한다.
				return o1HEAD.compareTo(o2HEAD);
			} else {
				// HEAD가 같다면 각 NUMBER를 비교하여 오름차순으로 정렬한다.
				return Integer.parseInt(o1NUMBER) - Integer.parseInt(o2NUMBER);
			}			
		}).toArray(String[]::new);
		return answer;
    }
}
```