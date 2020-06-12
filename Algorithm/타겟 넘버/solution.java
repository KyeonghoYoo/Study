package me.koobin.algorism.programers;
// n개의 음이 아닌 정수가 있습니다. 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

public class solution {
	private static int answer = 0;
	public static void main(String[] args) {
		int[] numbers = {1, 1, 1, 1, 1};
		System.out.println(solution(numbers, 3));
	}
	
    public static int solution(int[] numbers, int target) {
        int answer = 0;
      makeNum(numbers.length - 1, 0, numbers, target);
        
        
        return answer;
    }
    
    private static void makeNum(int i, int sum, int[] numbers, int target) {
    	if(i < 0) {
        	if(sum == target) {
        		answer++;
        	}
        	return;
    	}
    	makeNum(i - 1, sum + numbers[i], numbers, target);
    	makeNum(i - 1, sum - numbers[i], numbers, target);
    	return;
    }
}
