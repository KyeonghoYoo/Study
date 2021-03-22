import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
    public int[] solution(String s) {
        List<Integer> resultList = new ArrayList<>();
        
        Matcher matcher = Pattern.compile("([0-9]+[,]?)+").matcher(s);
        List<List<Integer>> tupleList = new ArrayList<>();

        while (matcher.find()) {
        	String group = matcher.group();
        	
        	String[] split = group.split(",");
        	
        	tupleList.add(Arrays.stream(split).map(Integer::valueOf).collect(Collectors.toList()));
		}
        

        tupleList.sort((t1, t2) -> t1.size() - t2.size());
        
        for (List<Integer> list : tupleList) {
        	list.removeAll(resultList);
        	resultList.addAll(list);
		}

        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }
}
