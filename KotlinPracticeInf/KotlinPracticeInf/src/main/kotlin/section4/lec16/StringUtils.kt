package section4.lec16

fun main() {
    var str = "ABC";
    println(str.lastChar())
}

/*
 * 확장 함수
 */
fun String.lastChar(): Char {
    return this[this.length - 1]
}