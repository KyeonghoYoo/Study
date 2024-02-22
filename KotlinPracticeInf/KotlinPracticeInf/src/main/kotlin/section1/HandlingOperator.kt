package section1

fun main() {

    val money1 = JavaMoney(2_000L);
    val money2 = JavaMoney(1_000L);

    if (money1 > money2) { // 인텔리제이에서 '>'를 cmd + 좌클릭을 해보면 JavaMoney 클래스의 `compareTo()`로 이동하는 것을 확인할 수 있음.
        println("Money1이 Money2보다 금액이 큽니다.");
    }

    /*
     * 동일성(identity; 동일한 객체인가), 동등성(equality; 값이 같은가) 비교
     */
    println(money1 == money2) // true, 동등성(equality) 비교
    println(money1 === money2) // false, 동일성(identity) 비교

    /*
     * 연산자 오버로 테스트
     */
    val money3 = Money(1_000L)
    val money4 = Money(2_000L)

    // Money 클래스에서 +를 재정의(오버로딩)하였음.
    val newMoeny: Money = money3 + money4; // Money(amount=3000)
}
