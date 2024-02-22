package section4.lec15;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lec15Main {

    public static void main(String[] args) {
        int[] array = { 100, 200 };

        for (int i = 0; i < array.length; i++) {
            System.out.printf("%s %s%n", i, array[i]);
        }

        final List<Integer> numbers = Arrays.asList(100, 200);

        // JDK 8까지
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Monday");
        map.put(2, "Tuesday");

        // JDK 9부터
        Map.of(1, "Monday", 2, "Tuesday");

        for (int key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.printf("%d, %s\n", entry.getKey(), entry.getValue());
        }
    }
}
