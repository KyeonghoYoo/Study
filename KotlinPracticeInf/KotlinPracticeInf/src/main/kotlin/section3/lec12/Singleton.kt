package section3.lec12

fun main() {

    // `object Singleton`은 그 자체가 객체이므로 바로 객체처럼 사용하면 되고, 필드에 object 이름으로 접근할 수 있음.
    println(Singleton.a)
    Singleton.a += 10
    println(Singleton.a)
}

/*
 * 하나의 객체만 생성하는 코틀린의 방법
 */
object Singleton {
    var a: Int = 0;
}