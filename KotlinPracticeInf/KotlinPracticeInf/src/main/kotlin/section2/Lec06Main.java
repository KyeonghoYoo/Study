package section2;

import java.util.Arrays;
import java.util.List;

public class Lec06Main {

    public static void main(String[] args) {

        /*
         * for…each 사용
         */
        List<Long> numbers = Arrays.asList(1L, 2L, 3L);
        for (long number : numbers) {
            System.out.println(number);
        }

        /*
         * 전통적인 for 문
         */
        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
        }
        for (int i = 3; i >= 1; i--) {
            System.out.println(i);
        }
        for (int i = 1; i <= 5; i += 2) {
            System.out.println(i);
        }
    }
}
