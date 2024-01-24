package section3.lec14

fun main() {
    handleCar(Avante())
}

fun handleCar(car: HyundaiCar) {
    when (car) {
        is Avante -> TODO()
        is Sonata -> TODO()
        is Grandeur -> TODO()
    }
}
sealed class HyundaiCar(
    val name: String,
    val price: Long
)


class Avante : HyundaiCar("아반떼", 1_000L)
class Sonata : HyundaiCar("아반떼", 2_000L)
class Grandeur : HyundaiCar("아반떼", 3_000L)