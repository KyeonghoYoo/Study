package etc;

import java.util.*;
import java.util.stream.Collectors;

public class PracticeAlgorithms {

    public static void main(String[] args) {
        System.out.println(steppingStone(25, new int[]{ 2, 14, 11, 21, 17 }, 2));
    }


    /**
     * [PCCP 기출문제] 2번 / 석유 시추
     *
     * @param land
     * @return
     */
    public int solution1(int[][] land) {
        int n = land.length;
        int m = land[0].length;

        int oilId = 1;
        int[][] oilIdArray = new int[n][m];
        boolean[][] isVisited = new boolean[n][m];
        Map<Integer, Integer> oilSizeMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !isVisited[i][j]) {
                    int oilSize = measureOilSizeUsingBfs(n, m, i, j, land, oilIdArray, isVisited, oilId);
                    oilSizeMap.put(oilId++, oilSize);
                }
            }
        }

        int[] oilSizeArray = new int[m];
        for (int j = 0; j < m; j++) {
            Set<Integer> oilIdSet = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (oilIdArray[i][j] != 0) {
                    oilIdSet.add(oilIdArray[i][j]);
                }
            }
            oilSizeArray[j] = oilIdSet.stream()
                    .mapToInt(oilSizeMap::get)
                    .sum();
        }

        return Arrays.stream(oilSizeArray).max().getAsInt();
    }

    /**
     * 시추 함수
     */
    public int measureOilSizeUsingBfs(int n, int m, int i, int j, int[][] land, int[][] oilIdArray, boolean[][] isVisited, int oilId) {
        int size = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        isVisited[i][j] = true;
        oilIdArray[i][j] = oilId;

        while (!queue.isEmpty()) {
            int[] curruntPosition = queue.poll();
            int nextI = curruntPosition[0];
            int nextJ = curruntPosition[1];

            if (nextJ + 1 < m && land[nextI][nextJ + 1] == 1 && !isVisited[nextI][nextJ + 1]) {
                queue.add(new int[]{nextI, nextJ + 1});
                isVisited[nextI][nextJ + 1] = true;
                oilIdArray[nextI][nextJ + 1] = oilId;
                size++;
            }
            if (nextI + 1 < n && land[nextI + 1][nextJ] == 1 && !isVisited[nextI + 1][nextJ]) {
                queue.add(new int[]{nextI + 1, nextJ});
                isVisited[nextI + 1][nextJ] = true;
                oilIdArray[nextI + 1][nextJ] = oilId;
                size++;
            }
            if (nextJ - 1 >= 0 && land[nextI][nextJ - 1] == 1 && !isVisited[nextI][nextJ - 1]) {
                queue.add(new int[]{nextI, nextJ - 1});
                isVisited[nextI][nextJ - 1] = true;
                oilIdArray[nextI][nextJ - 1] = oilId;
                size++;
            }
            if (nextI - 1 >= 0 && land[nextI - 1][nextJ] == 1 && !isVisited[nextI - 1][nextJ]) {
                queue.add(new int[]{nextI - 1, nextJ});
                isVisited[nextI - 1][nextJ] = true;
                oilIdArray[nextI - 1][nextJ] = oilId;
                size++;
            }
        }

        return size;
    }

    public int solution(int[] nums) {
        int maximumSize = nums.length / 2;
        Set<Integer> kindSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        return Math.min(kindSet.size(), maximumSize);
    }

    /**
     * 큰 수 만들기
     * @param number
     * @param k
     * @return
     */
    public String solution3of1(String number, int k) {

        int chanceToRemove = k;
        while (chanceToRemove > 0) {
            String tempStr = "";
            char[] chars = number.toCharArray();
            for (int i = 0; i < chars.length - 1; i++) {
                if (chanceToRemove > 0 && chars[i] < chars[i + 1]) {
                    tempStr += chars[i];
                    chanceToRemove--;
                } else if (!tempStr.isEmpty()){
                    break;
                }
            }
            number = number.replaceFirst(tempStr, "");
        }
        String answer = number;
        return answer;
    }
    public String solution3of2(String number, int k) {

        int countShouldBeRemain = number.length() - k;


        return number;
    }

    public static int BracketCombinations(int num) {
        int[][] memo = new int[num][num];
        return num;
    }

    public static int calculateCombinations(int to_W, int to_H, int[][] memo) {
        if (to_W == 0 && to_H == 0) {
            return 1;
        }
        if (memo[to_W][to_H] != 0) {
            return memo[to_W][to_H];
        }
        int total = 0;

        if (to_W > 0) {
            total += calculateCombinations(to_W - 1, to_H + 1, memo);
        }
        if (to_H > 0) {
            total += calculateCombinations(to_W, to_H - 1, memo);
        }
        memo[to_W][to_H] = total;
        return total;
    }

    public static String TreeConstructor(String[] strArr) {

        return "true";
    }

    /**
     *
     * 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수를 순서대로 1차원 정수 배열에 담아 return
     *
     * 제한 사항
     * - 1 ≤ edges의 길이 ≤ 1,000,000
     *     - edges의 원소는 [a,b] 형태이며, a번 정점에서 b번 정점으로 향하는 간선이 있다는 것을 나타냅니다.
     *     - 1 ≤ a, b ≤ 1,000,000
     * - 문제의 조건에 맞는 그래프가 주어집니다.
     * - 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2이상입니다.
     *
     * 도넛 모양 그래프 ; n개의 정점, n개의 간선
     * 막대 모양 그래프 ; n개의 정점, n-1개의 간선
     * 8자 모양 그래프 ; 2n + 1개의 정점, 2n + 2개의 간선
     * 생성한 정점 ; 각 그래프의 임이의 정점 하나로 향하는 간선이 연결 돼있음.
     *
     *  [[4, 11], [1, 12], [8, 3], [12, 7], [4, 2], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]]	[4, 0, 1, 2]
     *
     *
     */
    public int[] donutAndBarGraph(int[][] edges) {
        int[] answer = new int[4];

        int edgeCount = Arrays.stream(edges)
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt();

        /*
         * edgesInfo = [[나가는 간선 갯수, 들어 오는 간선 갯수]]
         */
        int[][] edgesInfo = extractEdgesInfo(edges, edgeCount);

        int targetEdgeNumber = findTargetEdgeNumber(edgesInfo);
        answer[0] = targetEdgeNumber + 1;

        Arrays.stream(edges)
                .filter(edge -> edge[0] == targetEdgeNumber)
                .forEach(edge -> edgesInfo[edge[1] - 1][1] -= 1);

        /*
         * 생성된 정점과 연결된 그래프들의 총 갯수
         */
        int graphTotalCount = edgesInfo[targetEdgeNumber][0];

        int barGraphCount = 0;
        int eightGraphCount = 0;

        for (int i = 0; i < edgesInfo.length; i++) {
            if (i == targetEdgeNumber)
                continue;
            int[] edgeInfo = edgesInfo[i];
            if (edgeInfo[0] == 0) {
                barGraphCount += 1;
            } else if (edgeInfo[0] == 2 || edgeInfo[1] == 2) {
                eightGraphCount += 1;
            }
        }

        int donutGraphCount = graphTotalCount - (barGraphCount + eightGraphCount);
        answer[1] = donutGraphCount;
        answer[2] = barGraphCount;
        answer[3] = eightGraphCount;

        return answer;
    }

    private static int[][] extractEdgesInfo(int[][] edges, int maxEdge) {
        /*
         * edgesInfo[정점 숫자] = [[나가는 간선 갯수, 들어 오는 간선 갯수]]
         */
        int[][] edgesInfo = new int[maxEdge][2];

        for (int[] edge : edges) {
            edgesInfo[edge[0] - 1][0] += 1;
            edgesInfo[edge[1] - 1][1] += 1;
        }

        return edgesInfo;
    }

    private static int findTargetEdgeNumber(int[][] edgeInfos) {
        for (int i = 0; i < edgeInfos.length; i++ ) {
            int[] edgeInfo = edgeInfos[i];
            if (edgeInfo[0] > 1 && edgeInfo[1] == 0)
                return i;
        }
        throw new IllegalStateException("임의로 생성된 정점이 없습니다.");
    }


    public static int interceptTargetMissile(int[][] targets) {
        int answer = 0;

        int[][] sortedTargets = Arrays.stream(targets)
                .sorted((target1, target2) -> {
                    if (target1[0] == target2[0]) {
                        return Integer.compare(target1[1], target2[1]);
                    } else {
                        return Integer.compare(target1[0], target2[0]);
                    }
                })
                .toArray(int[][]::new);


        int[] targetRange = { Integer.MIN_VALUE, Integer.MAX_VALUE };

        for (int[] target : sortedTargets) {
            if (targetRange[1] <= target[0]) {
                answer += 1;
                targetRange[0] = Integer.MIN_VALUE;
                targetRange[1] = Integer.MAX_VALUE;
            }

            if (targetRange[0] < target[0]) {
                targetRange[0] = target[0];
            }
            if (target[1] < targetRange[1]) {
                targetRange[1] = target[1];
            }
        }

        return answer;
    }
    /*
     *
     *
     */

    public static int steppingStone(int distance, int[] rocks, int n) {
        rocks = Arrays.stream(rocks)
                .sorted()
                .toArray();

        int[][] distancesBetweenWithPosition = extractDistancesBetweenWithPosition(distance, rocks);

        int[][] sortedDistancesBetweenWithPosition = Arrays.stream(distancesBetweenWithPosition)
                .sorted(Comparator.comparingInt(o -> o[0]))
                .toArray(int[][]::new);

        Arrays.stream(distancesBetweenWithPosition).
                forEach(ints -> System.out.println(ints[0]));
        Arrays.stream(sortedDistancesBetweenWithPosition).
                forEach(ints -> System.out.println(ints[0]));

        return sortedDistancesBetweenWithPosition[n + 2][0];
    }

    /*
     * 시작지점부터 도착 지점까지의 사이 거리를 배열로 담음, 크기는 rocks.length + 1 임.
     */
    private static int[][] extractDistancesBetweenWithPosition(int distance, int[] rocks) {
        int[][] distancesBetween = new int[rocks.length + 1][2];

        distancesBetween[0][0] = rocks[0];
        distancesBetween[0][1] = 0;

        int prePositionOfRock = rocks[0];

        for (int i = 1; i < rocks.length; i++) {
            distancesBetween[i][0] = rocks[i] - prePositionOfRock;
            distancesBetween[i][1] = i;
            prePositionOfRock = rocks[i];
        }

        distancesBetween[rocks.length][0] = distance - rocks[rocks.length - 1];
        distancesBetween[rocks.length][1] = rocks.length;

        return distancesBetween;
    }
}