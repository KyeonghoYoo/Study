package section1

import java.util.Collections
import java.util.Random

fun main() {

    // - var은 variable의 약자로 가변 변수 앞에는 반드시 선언해 줘야 하는 약속어이다.
    // - 코틀린은 변수에 타입을 선언해주지 않아도 값에 따라 타입을 컴파일러가 추론하여 정해줌. 명시적으로 선언해줄 수도 있음. 예시: var number: Long = 5L;
    //    - 값을 정하지 않고 생성한 경우에는 타입을 추론할 수 없기 때문에 컴파일 에러가 발생함.
    var number1 = 10L
    number1 = 5L;

    // val은 자바로 치면 final과 같은 역할을 함. number2가 선언된 이후로는 변수의 값을 바꿔줄 수 없음.
    val number2 = 10L

    // elements를 수정하는데는 문제 없음.
    val numbers = Collections.emptyList<Long>();
    numbers.add(10L);

    // 코틀린에서는 클래스를 객체화할 때 new를 붙이지 않음.
    val math = Random();
}