package section1

fun main() {

    // ?를 붙여 null이 들어갈 수 있는 변수로 선언
    val str: String? = "ABC"

    /*
     * Safe Call Test
     */
    str?.startsWith("A"); // 메서드 호출하는 . 앞에 ?를 붙이면 Safe Call로 수행함.

    /*
     * Elivs 연산자 Test
     */
    val result = str?.length ?: 0; // str?.length가 null이면 0, 아니면 str?.length의 값 return.
    println(result);
}

fun startsWithA1(str: String?): Boolean {
    if (str == null) {
        throw IllegalArgumentException("str은 null이 될 수 없습니다.");
    }
    return str.startsWith("A");
}

fun startsWithA1WithKotlin(str: String?): Boolean {
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("str은 null이 될 수 없습니다.");
}

// return type인 Bollean에도 ?가 붙어 null이 들어갈 수 있는 Boolean 타입임을 명확하게 함.
fun startsWithA2(str: String?): Boolean? {
    if (str == null) {
        return null;
    }
    return str.startsWith("A");
}

fun startsWithA2WithKotlin(str: String?): Boolean? {
    return str?.startsWith("A");
}

fun startsWithA3(str: String?): Boolean {
    if (str == null) {
        return false;
    }
    return str.startsWith("A");
}

fun startsWithA3WithKotlin(str: String?): Boolean {
    return str?.startsWith("A") ?: false;
}

// 결론적으로 아래와 같이 String에 ?를 붙이지 않으면 str에 null이 들어갈 수 있는 String에 대해 주입되는 것을 원초적으로 방지할 수 있음..
fun startsWithAWithKotlin(str: String): Boolean {
    return str.startsWith("A");
}


fun startsWithA(str: String?): Boolean {
    /*
     * !!로 널 아님을 단언한다고 해서 null이 걸러지는게 아니므로 혹여나 null이 들어오면 NPE이 발생함.
     * 그러므로 확실히 null인 경우에만 사용한다.
     */
    return str!!.startsWith("A");
}