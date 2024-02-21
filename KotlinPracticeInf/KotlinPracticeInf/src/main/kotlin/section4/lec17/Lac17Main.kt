package section4.lec17

fun main() {
    val fruits = listOf(
        Fruit("사과", 1000),
        Fruit("사과", 1200),
        Fruit("사과", 1200),
        Fruit("사과", 1500),
        Fruit("바나나", 3000),
        Fruit("바나나", 3200),
        Fruit("바나나", 2500),
        Fruit("수박", 10000)
    )

    /**
     * 이름 없는 함수, 람다
     * 1. 일반적인 함수를 선언하듯 작성하여 선언하는 방법
     *
     * (파라미터타입...) -> 반환타입 형식으로 타입을 명시적으로 정해줄 수도 있음. e.g. (Fruit) -> Boolean
     */
    val isApple: (Fruit) -> Boolean = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    /**
     * 이름 없는 함수, 람다
     * 2. 중괄호와 화살표를 이용한 선언 방법.
     */
    val isApple2 = { fruit: Fruit -> fruit.name == "사과" }

    /*
     * 람다를 호출하는 방법
     */
    // isApple 자체가 함수이기 때문에 소괄호를 통해 파라미터를 넘겨 직접 호출할 수 있음.
    isApple(fruits[0])
    // invoke()를 명시적으로 호출해 함수를 호출할 수 있음.
    isApple.invoke(fruits[0])

    filterFruits(fruits) { it.name == "사과"}
    filterFruits(fruits) { fruit ->
        println("여러 줄의 람다 함수")
        fruit.name == "사과" // 마지막 줄이 return 됨.
    }

}

private fun filterFruits(
    fruits: List<Fruit>,
    filter: (Fruit) -> Boolean
): List<Fruit> {
    val result = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if (filter(fruit)) {
            result.add(fruit)
        }
    }
    return result
}