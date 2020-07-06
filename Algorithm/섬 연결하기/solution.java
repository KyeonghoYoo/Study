import java.util.Arrays;
import java.util.Comparator;

class solution {
	static int[] parent;

	public int solution(int n, int[][] costs) {
		int answer = 0;
		parent = new int[n];

		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}

		});
        
		for (int i = 0; i < n; ++i)
			parent[i] = i;

		for (int[] is : costs) {
			if (find(is[0]) != find(is[1])) {
				union(is[0], is[1]);
				answer += is[2];
			}
		}

		return answer;
	}

	public int find(int n) {
		if (parent[n] == n)
			return n;
		return find(parent[n]);
	}

	public void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB)
			parent[rootB] = rootA;
	}
}