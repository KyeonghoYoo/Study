package section4.lec18

data class Fruit(
    val id: Long,
    val name: String,
    /**
     * 출고가
     */
    val factoryPrice: Long,
    /**
     * 현재가
     */
    val currentPrice: Long
) {

    val isSamePrice: Boolean
        get() = factoryPrice == currentPrice
}
