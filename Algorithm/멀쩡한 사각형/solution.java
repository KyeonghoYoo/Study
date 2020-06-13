import java.math.BigInteger;

public class solution {

	public static int getGcd(int num1, int num2) {
		BigInteger b1 = BigInteger.valueOf(num1);
		BigInteger b2 = BigInteger.valueOf(num2);
		
		return b1.gcd(b2).intValue();
	}
    public long solution(int w, int h) {
        long answer = 1;
        int gcd = getGcd(w, h);
        
        answer = w + h - (w + h - gcd);
        
        return answer;
    }
}
