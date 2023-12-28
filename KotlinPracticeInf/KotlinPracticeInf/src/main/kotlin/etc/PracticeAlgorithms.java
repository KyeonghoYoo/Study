package etc;

import java.util.*;
import java.util.stream.Collectors;

public class PracticeAlgorithms {

    public static void main(String[] args) {


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



}