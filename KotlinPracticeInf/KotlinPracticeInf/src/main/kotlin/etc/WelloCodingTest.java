package etc;

import java.util.ArrayList;
import java.util.List;

public class WelloCodingTest {

    public static void main(String[] args) {
        solution("1234", "5678");
    }

    public int solution1(int num) {
        int answer = 0;

        int digit = findDigit(num);

        int[] frontNumbers = new int[digit / 2];
        int[] rearNumbers = new int[digit / 2];

        return answer;
    }

    private static int findDigit(int num) {
        int digit = 1;
        while (num > 10) {
            num /= 10;
            digit += 1;
        }
        return digit;
    }

    public static String solution(String a, String b) {
        String answer = "";

        List<String> numbers = new ArrayList<>();

        while (a.length() > 18 && b.length() > 18) {
            long sum = Long.parseLong(a.substring(a.length() - 19))
                    + Long.parseLong(a.substring(b.length() - 19));
            numbers.add(String.valueOf(sum));

            a = a.substring(0, a.length() - 19);
            b = b.substring(0, b.length() - 19);
        }

        if (a.length() > 18) {
            long aLong = Long.parseLong(a.substring(a.length() - 19));
            long bLong = Long.parseLong(b);
            numbers.add(String.valueOf(aLong + bLong));
            answer = a.substring(0, a.length() - 19) +
                    String.join("", numbers.reversed());

        } else if (b.length() > 18) {
            long bLong = Long.parseLong(b.substring(b.length() - 19));
            long aLong = Long.parseLong(a);
            numbers.add(String.valueOf(aLong + bLong));
            answer = b.substring(0, a.length() - 19) +
                    String.join("", numbers.reversed());
        } else if (a.length() <= 18 && b.length() <= 18) {
            long aLong = Long.parseLong(a);
            long bLong = Long.parseLong(b);
            numbers.add(String.valueOf(aLong + bLong));
            answer = String.join("", numbers);
        } else {
            answer = String.join("", numbers);
        }
        return answer;
    }

}
