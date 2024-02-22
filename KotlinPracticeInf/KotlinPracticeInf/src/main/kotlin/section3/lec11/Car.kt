package section3.lec11

class Car (
  internal val name: String, // getter setter 한 번에 가시성 부여
    _price: Int
) {
    var price = _price
        private set // Setter나 Getter에 따로 추가 가시성을 부여할 수 있음.
}