package section3.Lec13

fun main() {

}

class House (
    private val address: String
) {

    private val livingRoom: LivingRoom by lazy { this.LivingRoom(10.0) }

    inner class LivingRoom(
        private val area: Double
    ) {
        val address: String
            get() = this@House.address
    }
}