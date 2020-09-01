import java.util.*;

class Solution {
	// build_frame의 원소는 [x, y, a, b]형태
	// x, y는 기둥, 보를 설치 또는 삭제할 교차점의 좌표, a는 설치 또는 삭제할 구조물의 종류를 나타내며, 0은 기둥, 1은 보
	// b는 구조물을 설치할 지, 혹은 삭제할 지를 나타내며 0은 삭제, 1은 설치를 나타냅니다.

	// return 하는 배열의 원소는 [x, y, a] 형식
	// x, y는 기둥, 보의 교차점 좌표, a는 구조물의 종류를 나타내며, 0은 기둥, 1은 보
	public int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};
		// 아래의 Map들을 추출해 정답을 담을 List를 선언한다.
		List<int[]> answerList = new ArrayList<int[]>();

		// 설치 순서대로 설치할 기둥과 보의 정보를 담을 Map을 선언, pillarMap은 기둥의 정보를, beamMap은 보의 정보를 담는다.
		// Key는 x, y 좌표를 담을 것이며 키의 형태는 "x,y"로 한다.
		// value에는 구조물의 형태를 지정하고, 0은 기둥, 1은 보로 지정한다.
		Map<String, Integer> pillarMap = new HashMap<String, Integer>();
		Map<String, Integer> beamMap = new HashMap<String, Integer>();

		for (int[] element : build_frame) {
			int x = element[0];
			int y = element[1];
			int sort = element[2];
			int action = element[3];
			// key로 사용될 좌표
			String pos = "" + x + "," + y;

			// x나 y 좌표가 n보다 클 경우 다음 요소로 넘긴다.
			if (x > n || y > n)
				continue;

			// 먼저 구조물의 종류부터 파악한다.
			if (sort == 0) {
				// 기둥이라면 y가 n - 1보다 크다면 실행하지 않고 넘긴다.
				if (y > n - 1)
					continue;
				// 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
				// 구조물을 설치할 지, 혹은 삭제할 지를 파악한다.
				if (action == 1) {
					// 설치 시
					// 바닥에 있거나, 다른 기둥 위에 있거나, 보의 한쪽 끝 부분 위에 있는지 검사한다.
					if (isValidPillar(x, y, pillarMap, beamMap)) {
						pillarMap.put(pos, 0);
					}
				} else {
					// 삭제 시, 영향을 끼칠 수 있는 기둥과 보에 대해 이상이 없을 지 검사한다.
					// 먼저 해당 기둥이 존재하는지 검사한다.
					if (pillarMap.containsKey(pos)) {
						// -1로 value를 변경해 임시로 삭제한다.
						pillarMap.replace(pos, -1);

                        if ((pillarMap.containsKey("" + x + "," + (y + 1)) ? isValidPillar(x, y + 1, pillarMap, beamMap) : true) 
						&& (beamMap.containsKey("" + x + "," + (y + 1)) ? isValidBeam(x, y + 1, pillarMap, beamMap) : true) 
						&& (beamMap.containsKey("" + (x - 1) + "," + (y + 1)) ? isValidBeam(x - 1, y + 1, pillarMap, beamMap) : true)) {

							// 기둥의 위에 있는 기둥과 기둥 꼭대기에 위치한 양 옆의 보들이 이상이 없다면 삭제 처리한다.
							pillarMap.remove(pos);
						} else {
							// 하나라도 이상이 있다면 임시 삭제 행위를 취소한다.
							pillarMap.replace(pos, 0);
						}
					}
				}
			} else {
				// 보라면 x좌표가 n - 1보다 클 때, 혹은 y가 0이면 다음 요소로 넘긴다.
				if (x > n - 1 || y == 0)
					continue;
				// 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
				// 구조물을 설치할 지, 혹은 삭제할 지를 파악한다.
				if (action == 1) {
					// 설치 시
					// 보의 한쪽 끝 부분이 기둥위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있는지 검사한다.
					if (isValidBeam(x, y, pillarMap, beamMap)) {
						beamMap.put(pos, 1);
					}
				} else {
					// 삭제 시, 영향을 끼칠 수 있는 기둥과 보에 대해 이상이 없을 지 검사한다.
					// 먼저 해당 보가 존재하는지 검사한다.
					if (beamMap.containsKey(pos)) {
						// -1로 value를 변경해 임시로 삭제한다.
						beamMap.replace(pos, -1);

						if ((pillarMap.containsKey("" + x + "," + y) ? isValidPillar(x, y, pillarMap, beamMap) : true)
						&& (pillarMap.containsKey("" + (x + 1) + "," + y) ? isValidPillar(x + 1, y, pillarMap, beamMap) : true)
						&& (beamMap.containsKey("" + (x - 1) + "," + y) ? isValidBeam(x - 1, y, pillarMap, beamMap) : true) 
						&& (beamMap.containsKey("" + (x + 1) + "," + y) ? isValidBeam(x + 1, y, pillarMap, beamMap) : true)) {
							// 보의 양쪽에 있는 기둥들과 양 옆의 보들이 이상이 없다면 삭제 처리한다.
							beamMap.remove(pos);
						} else {
							// 하나라도 이상이 있다면 임시 삭제 행위를 취소한다.
							beamMap.replace(pos, 1);
						}
					}
				}
			}
		}

		// 최종적으로 추출된 기둥과 보를 List에 담는 처리
		for (String key : pillarMap.keySet()) {
			String[] pos = key.split(",");
			int[] answerArray = { Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), pillarMap.get(key) };
			answerList.add(answerArray);
		}

		for (String key : beamMap.keySet()) {
			String[] pos = key.split(",");
			int[] answerArray = { Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), beamMap.get(key) };
			answerList.add(answerArray);
		}

		// List를 Stream으로 변환하여 정렬 처리 후, Array로 변환하여 answer에 담아 반환한다.
		answer = answerList.stream().sorted((o1, o2) -> {
			if (o1[0] != o2[0]) {
				// x좌표 오름차순 정렬 처리
				return o1[0] - o2[0];
			} else if (o1[1] != o2[1]) {
				// y좌표 오름차순 정렬 처리
				return o1[1] - o2[1];
			} else {
				// 기둥과 보 정렬 처리
				return o1[2] - o2[2];
			}
		}).toArray(int[][]::new);

		return answer;
	}

	// 바닥 위에 있거나 기둥이 다른 기둥 위에 있거나, 보의 한쪽 끝 부분 위에 있는지 검사한다.
	public boolean isValidPillar(int x, int y, Map<String, Integer> pillarMap, Map<String, Integer> beamMap) {
		if (y == 0) {
			return true;
		} else if (pillarMap.containsKey("" + x + "," + (y - 1)) && pillarMap.get("" + x + "," + (y - 1)) == 0) {
			return true;
		} else if (beamMap.containsKey("" + x + "," + y) && beamMap.get("" + x + "," + y) == 1
				|| beamMap.containsKey("" + (x - 1) + "," + y) && beamMap.get("" + (x - 1) + "," + y) == 1) {
			return true;
		}
		return false;
	}

	// 보의 한쪽 끝 부분이 기둥위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있는지 검사하는 함수
	public boolean isValidBeam(int x, int y, Map<String, Integer> pillarMap, Map<String, Integer> beamMap) {
		if (pillarMap.containsKey("" + x + "," + (y - 1)) && pillarMap.get("" + x + "," + (y - 1)) == 0
				|| pillarMap.containsKey("" + (x + 1) + "," + (y - 1)) && pillarMap.get("" + (x + 1) + "," + (y - 1)) == 0) {
			return true;
		} else if (beamMap.containsKey("" + (x - 1) + "," + y) && beamMap.get("" + (x - 1) + "," + y) == 1
				&& beamMap.containsKey("" + (x + 1) + "," + y) && beamMap.get("" + (x + 1) + "," + y) == 1) {
			return true;
		}
		return false;
	}
}