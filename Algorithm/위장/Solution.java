import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
 
class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        Map<String, List<String>> hm = new HashMap<String, List<String>>();
 
        // 종류별로 HashMap에 List로 담는다.
        for (String[] el : clothes) {
            String key = el[1];
            String value = el[0];
            if (hm.containsKey(key)) {
                hm.get(key).add(value);
            } else {
                hm.put(key, new ArrayList<String>());
                hm.get(key).add(value);
            }
        }
 
        // keySet을 stream을 이용해서 Set->List로 바꿔준다.
        // 중간에 sorted()를 이용해 List 크기별로 내림차순 정렬을 해줌.
        List<String> sortedKeyList = hm.keySet()
                .stream()
                .sorted((o1, o2) -> hm.get(o2).size() - hm.get(o1).size())
                .collect(Collectors.toList());
        // dp 적용을 위한 HashMap 선언
        Map<String, Integer> memo = new HashMap<String, Integer>();
 
        for (int i = 0; i < sortedKeyList.size(); i++) {
            answer += func(i, 0, sortedKeyList, hm, memo);
        }
 
        return answer;
    }
 
    public int func(int i, int sum, List<String> sortedKeyList, Map<String, List<String>> hm,
            Map<String, Integer> memo) {
        String key = sortedKeyList.get(i);
        
        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        
        List<String> list = hm.get(key);
        for (String string : list) {
            sum += 1;
            System.out.println(sum + ". " + string);
            for (int j = i + 1; j < sortedKeyList.size(); j++) {
                sum += func(j, 0, sortedKeyList, hm, memo);
            }
        }
        memo.put(key, sum);
        return sum;
    }
}