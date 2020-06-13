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
