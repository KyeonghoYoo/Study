package etc;

import java.util.Scanner;

public class MuseLiveCodingTest {

    public static String StringChallenge(String str) {
        // code goes here
        int num = Integer.parseInt(str.replaceAll("\"", ""));
        StringBuilder binary = new StringBuilder();

        for (; num > 0; num /= 2) {
            binary.append(num % 2);
        }
        System.out.println(binary);
        int remainLength = 4 - (binary.length() % 4);
        System.out.println("remainLength = " + remainLength);
        if (remainLength > 0 && remainLength < 4) {
            for (; remainLength > 0; remainLength--) {
                binary.append("0");
            }
        }
        System.out.println(binary);

        return String.valueOf(Integer.parseInt(binary.toString(), 2));
    }
    public static void stringMain (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(StringChallenge(s.nextLine()));
    }

    public static String MathChallenge(String str) {
        // code goes here

        String[] splitStr = str.replaceAll("\"", "").split(" = ");
        String experssion = splitStr[0];
        String result = splitStr[1];

        String[] splitExpression = experssion.split(" [+\\-*/] ");
        String o1 = splitExpression[0];
        String o2 = splitExpression[1];

        for (int i = 0; i <= 9; i++) {
            String replacedO1 = o1.replace("x", String.valueOf(i));
            String replacedO2 = o2.replace("x", String.valueOf(i));
            String replacedResult = result.replace("x", String.valueOf(i));

            int temp = 0;

            if (experssion.contains("+")) {
                temp = Integer.parseInt(replacedO1) + Integer.parseInt(replacedO2);
            } else if (experssion.contains("-")) {
                temp = Integer.parseInt(replacedO1) - Integer.parseInt(replacedO2);
            } else if (experssion.contains("*")) {
                temp = Integer.parseInt(replacedO1) * Integer.parseInt(replacedO2);
            } else if (experssion.contains("/")) {
                temp = Integer.parseInt(replacedO1) / Integer.parseInt(replacedO2);
            }

            if (temp == Integer.parseInt(replacedResult)) {
                return String.valueOf(i);
            }
        }

        return str;
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(MathChallenge(s.nextLine()));
    }

}
