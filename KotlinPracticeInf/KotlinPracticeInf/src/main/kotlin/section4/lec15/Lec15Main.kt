package section4.lec15

fun main() {
    /*
     ************ LIST ************
     */
    val array = arrayOf(100, 200)

    // indices에 배열의 인덱스들이 담겨서 옴
    for (i in array.indices) {
        println("${i} ${array[i]}}")
    }

    // `withIndex()`를 활용하여 인덱스와 밸류값을 함께 가져올 수 있음.
    for ((idx, value) in array.withIndex()) {
        println("${idx} ${value}")
    }

    array.plus(300) // 코틀린은 배열에 대한 여러 메소드를 제공하기 때문에 이와 같이 쉽게 조작할 수 있음.


    val numbers = listOf(100, 200)
    val emptyList = emptyList<Int>()

    printNumbers(emptyList())

    numbers[0] // Collection 객체지만 배열을 다루듯 다룰 수 있음.

    val mutableNumbers = mutableListOf(100, 200)
    mutableNumbers.add(300)

    /*
     * ****************** SET ************************
     * set의 사용법은 전반적으로 List와 동일함.
     * 기본 구현체는 `LinkedHashSet`
     */
    val immutableSet = setOf(100, 200) // 불가변 Set
    val mutableSet = mutableSetOf(100, 200) // 가변 Set
    val emptySet = emptySet<Int>() // 빈 Set
    // for each
    for (number in immutableSet) {
        println(number)
    }

    // 전통적인 for문
    for ((index, number) in immutableSet.withIndex()) {
        println("$index, $number")
    }

    /*
     * ************ MAP ************
     */
    val oldMap = mutableMapOf<Int, String>()

    oldMap.put(1, "Monday") // 전통적인 Map.put() 활용

    oldMap[1] = "Monday" // 배열 처럼 접근, (e.g. map[key] = value)

    mapOf(1 to "Monday", 2 to "Tuesday") // 불변(immutable) map 생성

    // map의 활용
    for (key in oldMap.keys) {
        println(key)
        println(oldMap[key])
    }

    for ((key, value) in oldMap.entries) {
        println("$key, $value")
    }

    // ? 위치에 따라 컬렉션 객체의 null 여부가 바뀌니 차이를 잘 이해하고 주의하여 설정할 것.
    val list1: List<Int>? = emptyList() // List는 nullable, 내부 elements는 non null
    val list2: List<Int?> = emptyList() // List는 non null, 내부 elements는 nullable
    val list3: List<Int?>? = emptyList() // List는 nullable, 내부 elements도 nullable

}

fun printNumbers(numbers: List<Int>) = println(numbers)