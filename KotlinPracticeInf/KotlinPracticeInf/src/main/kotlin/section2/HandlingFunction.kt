package section2

fun main() {

    repeat("Hello Wolrd", 3, false)
    /*
     * 아래와 같이 명시적으로 특정 인자에 값을 전달하는 것을 named parameter라고 한다.
     */
    repeat("Hello Wolrd", useNewLine = false)

    /*
     * builder 패턴 사용시 `.파라미터명()`을 통해 값을 주입하여 명시적인 값의 초기화가 가능했는데
     * kotlin에서도 아래와 같이 named parameter를 사용하면 이러한 장점을 활용할 수 있다.
     */
    printNameAndGender(name = "유경호", gender = "남자")

    val lec08Main = Lec08Main()
//    lec08Main.repeat(str = "java 클래스로 짜여진 코드엔 named argument를 사용할 수 없음.")

    printAll("A", "B", "C")

    val array = arrayOf("A", "B", "C")
    // `*`는 spread 연산자임, 코틀린에서 가변인자에 배열을 넣어줄 때 spread 연산자(*)를 앞에 붙여줘야 함.
    // spread 연산자는 배열을 위 println("A", "B", "C")와 같이 `, `를 사용하여 인자를 나열한 것과 같이 해줌.
    printAll(*array)
}


/*
 * public 접근 지시어(접근 제한자)는 생략 가능
 */
public fun maxV1(a: Int, b: Int): Int {
    if (a > b) {
        return a
    }
    return b
}

/*
 * if-else는 하나의 expression이므로 반환 값을 가질 수 있음.
 */
fun maxV2(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

/*
 * 함수가 하나의 결과값인 경우에는 { }(block) 대신 =을 사용할 수 있음.
 */
fun maxV3(a: Int, b: Int): Int =
    if (a > b) {
        a
    } else {
        b
    }

/*
 *
 * = 을 사용하는 경우에는 반환되는 두 인자가 모두 같은 타입이면 반환 타입을 생략할 수 있음
 * if 뒤의 { } 문도 코드가 한 줄인 경우에는 생략 가능함.
 */
fun maxV4(a: Int, b: Int) = if (a > b) a else b

fun repeat(
    str: String,
    num: Int = 3,
    useNewLine: Boolean = true
) {
    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }
}

fun printNameAndGender (name: String, gender: String) {
    println(name)
    println(gender)
}

fun printAll(vararg strings: String) {
    for (str in strings) {
        println(str)
    }
}