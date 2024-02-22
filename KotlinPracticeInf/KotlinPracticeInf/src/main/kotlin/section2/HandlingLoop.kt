package section2


fun main() {

    /*
     * 자바의 for...each 대체
     */
    val numbers: List<Long> = listOf(1L, 2L, 3L)
    for (number in numbers) {
        println(number)
    }

    /*
     * 자바의 전통적인 for 문 사용법 대체
     */
    // 일반적으로 i가 1부터 3까지 증가하면서 반복되는 경우
    for (i in 1..3) {
        println(i)
    }
    for (i in 1.rangeTo(3)) {
        println(i)
    }
    // i가 3부터 1까지 감소하면서 반복되는 경우
    for (i in 3 downTo 1) {
        println(i)
    }
    // i가 1부터 5까지 2씩 증가하며 반복함.
    for (i in 1..5 step 2) {
        println(i)
    }
}