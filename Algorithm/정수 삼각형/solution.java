class solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int depth = triangle.length;
        int[][] memo = new int[triangle.length][triangle.length];
 
        answer = dp(0, 0, depth, 0, triangle, memo);
        return answer;
    }
 
    public int dp(int i, int j, int depth, int sum, int[][] triangle, int[][] memo) {
        if (i == depth - 1) {
            return triangle[i][j];
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        memo[i][j] = Math.max(triangle[i][j] + dp(i + 1, j, depth, sum + triangle[i][j], triangle, memo),
                triangle[i][j] + dp(i + 1, j + 1, depth, sum + triangle[i][j], triangle, memo));
 
        return memo[i][j];
    }
}
