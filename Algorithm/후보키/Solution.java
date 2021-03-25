import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
	public int solution(String[][] relation) {
		List<Set<Integer>> candidateKeyList = new ArrayList<>();

		for (int i = 1; i <= relation[0].length; i++) {
			int[] columnIndexArray = Stream.iterate(0, n -> n + 1).limit(relation[0].length)
					.filter(n -> {
						if(candidateKeyList.contains(Set.of(n)))
							return false;
						return true;
					})
					.mapToInt(Integer::intValue).toArray();
			if (columnIndexArray.length < i)
				break;
			func(0, i, columnIndexArray, relation, new int[] {}, candidateKeyList);
		}
        
		return candidateKeyList.size();
	}

	public void func(int depth, int max, int[] columnIndexArray, String[][] relation, int[] combined,
			List<Set<Integer>> candidateKeyList) {
		if (depth >= max) {
			List<List<String>> forAddingCandidateKey = new ArrayList<>();
			for (String[] tuple : relation) {
				List<String> list = new ArrayList<>();

				for (int i : combined)
					list.add(tuple[i]);

				if (forAddingCandidateKey.contains(list))
					return;
				
				forAddingCandidateKey.add(list);
			}
			
			Set<Integer> collect = Arrays.stream(combined).boxed().collect(Collectors.toSet());
			
			for (Set<Integer> set : candidateKeyList) {
				if(collect.containsAll(set))
					return;
			}
			
			candidateKeyList.add(collect);
			return;
		}

		while (columnIndexArray.length != 0) {
			int[] copyOf = Arrays.copyOf(columnIndexArray, columnIndexArray.length);
			int[] new_columnIndexArray = Arrays.stream(columnIndexArray)
					.filter(columnIndex -> columnIndex != copyOf[0])
					.toArray();
			int[] new_combined = List.of(combined, new int[] { columnIndexArray[0] }).stream()
					.flatMapToInt(Arrays::stream)
					.toArray();
            
			func(depth + 1, max, new_columnIndexArray, relation, new_combined, candidateKeyList);
			columnIndexArray = new_columnIndexArray;
		}
	}
}