package section1;

public class JavaMoneyTest {
    public static void main(String[] args) {
        JavaMoney money1 = new JavaMoney(2_000L);
        JavaMoney money2 = new JavaMoney(1_000L);

        if (money1.compareTo(money2) > 0) {
            System.out.println("Money1이 Money2보다 금액이 큽니다.");
        }

        JavaMoney money3 = money1;

        /*
         * 동일성(identity; 동일한 객체인가), 동등성(equality; 값이 같은가) 비교
         */
        System.out.println(money1 == money3); // true, 동일성(identity) 비교
        System.out.println(money1 == money2); // false, 동일성(identity) 비교
        System.out.println(money1.equals(money2)); // true, 동등성(equality) 비교

        /*
         *
         */
        JavaMoney newJavaMoney = money1.plus(money2);
        System.out.println(newJavaMoney);
    }
}
