import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
    public String solution(int[] numbers) { 
    	String answer = Arrays.stream(numbers)
    							.mapToObj(String::valueOf)
    							.sorted((num1, num2) -> Integer.valueOf(num2 + num1) - Integer.valueOf(num1 + num2))
    							.collect(Collectors.joining());
        return  answer.startsWith("0") ? "0" : answer;
    }
}
