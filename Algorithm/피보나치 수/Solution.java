class Solution {
    public  int solution(int n) {
        int answer = 0;
        int[] memo = new int[100000];
        answer = fibo(n, memo);
        
        return answer;
    }
    
    public  int fibo(int n, int[] memo){
        if(n == 1){
            return 1;
        } else if(n <= 0){
            return 0;
        }
        
        if(memo[n - 1] != 0) {
            return memo[n - 1];
        }
        memo[n - 1] = (fibo(n - 2, memo) + fibo(n - 1, memo)) % 1234567;

        return memo[n - 1];
    }
}