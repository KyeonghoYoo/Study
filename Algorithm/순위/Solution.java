import java.util.Arrays;

public class Solution {
	public int solution(int n, int[][] results) {
		final int WIN = 1;
		final int LOSE = -1;
		
		int[][] records = new int[n][n];
		
		for (int[] result : results) {
			int winner = result[0] - 1;
			int loser = result[1] - 1;
			
			records[winner][loser] = WIN;
			records[loser][winner] = LOSE;
		}
		
		boolean change = false;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(records[i][j] == 0) {
					continue;
				}
				
				for(int k = 0; k < n; k++) {
					if(records[i][k] != 0 || records[j][k] == 0 || i == k) {
						continue;
					}
					
					if(records[i][j] == WIN) {
						if(records[j][k] == WIN) {
							records[i][k] = WIN;
							records[k][i] = LOSE;
							change = true;
						}
						
					} else {
						if(records[j][k] == LOSE) {
							records[i][k] = LOSE;
							records[k][i] = WIN;
							change = true;
						}
					}
				}
			}
			
			if(change) {
				change = false;
				i = 0;
			}
		}
		
		return (int) Arrays.stream(records).filter(record -> Arrays.stream(record).filter(r -> r != 0).count() == n - 1).count();
	}
}
