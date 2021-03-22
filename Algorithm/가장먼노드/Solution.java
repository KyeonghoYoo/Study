import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        boolean[] visit = new boolean[n];

        List<List<Integer>> edgeList = Arrays.stream(edge)
        			.map(e -> List.of(e[0], e[1]))
        			.collect(Collectors.toList());
        
        HashSet<Integer> preNodeSet = new HashSet<>(List.of(1));
        visit[0] = true;
        
        while (!edgeList.isEmpty()) {
        	HashSet<Integer> dumySet = new HashSet<>(preNodeSet);
			
			preNodeSet = edgeList.stream()
					.filter(e -> {
						if(dumySet.contains(e.get(0)) || dumySet.contains(e.get(1))) {
							if(visit[e.get(0) - 1] && visit[e.get(1) - 1])
								return false;
							return true;
						}
						return false;
					})
					.map(e -> {
						if(visit[e.get(0) - 1]) {
							visit[e.get(1) - 1] = true;
							return e.get(1);
						} 
						visit[e.get(0) - 1] = true;
						return e.get(0);
					})
					.collect(Collectors.toCollection(HashSet::new));
			System.out.println(preNodeSet.toString());
        	System.out.println(Arrays.toString(visit));
			
        	edgeList.removeIf(e -> visit[e.get(0) - 1] && visit[e.get(1) - 1]);
        	
        	answer = preNodeSet.size();
		}

        return answer;
    }
}
