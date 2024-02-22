package section4.lec18

fun main() {
    val fruitsInList: List<List<Fruit>> = listOf(
        listOf(
            Fruit(1L, "사과", 1_000L, 1_200L),
            Fruit(2L, "사과", 1_000L, 1_200L),
            Fruit(3L, "사과", 1_000L, 1_200L),
            Fruit(4L, "사과", 1_200L, 1_500L),
        ),
        listOf(
            Fruit(5L, "바나나", 2_200L, 3_000L),
            Fruit(6L, "바나나", 2_500L, 3_200L),
            Fruit(7L, "바나나", 1_500L, 2_500L)
        ),
        listOf(
            Fruit(8L, "수박", 8_000L, 10_000L),
        )
    )

    // 출고가와 현재가가 동일한 과일을 골라주세요!
    fruitsInList.flatMap { list ->
        list.filter { fruit -> fruit.factoryPrice == fruit.currentPrice }
    }

    // 위의 코드를 하나릐 람다만 사용하도록 리팩토링 할 수도 있음.
    fruitsInList.flatMap(List<Fruit>::samePriceFilter)

    // List<List<Fruit>>를 List<Fruit>로 그냥 바꾸어주세요!
    fruitsInList.flatten()
}

private fun filterFruits(
    fruit: List<Fruit>,
    filter: (Fruit) -> Boolean
): List<Fruit> {
    return fruit.filter(filter)
}

/**
 * List<Fruit>에 확장함수를 추가할 수가 있음. 확장 프로퍼티와 custom getter를 활용함.
 */
val List<Fruit>.samePriceFilter: List<Fruit>
    get() = this.filter(Fruit::isSamePrice)