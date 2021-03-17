public class Solution {
	
    public int solution(int n, int[][] computers) {
    	int answer = 0;
    	boolean[] visited = new boolean[n];

    	for(int i = 0; i < n; i++) {
    		answer += dfs(n, i, computers, visited);
    	}
    	
        return answer;
    }
    
    public int dfs(int n, int i, int[][] computers, boolean[] visited) {
    	if(visited[i]) {
    		return 0;
    	}
    	
    	visited[i] = true;
    	for(int j = 0; j < n; j++) {
    		if(computers[i][j] == 1)
    			dfs(n, j, computers, visited);
    	}
    	
    	return 1;
    }
}
