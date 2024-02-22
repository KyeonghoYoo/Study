package section3.Lec10

class Penguin(
    species: String,
) : Animal(species, 2), Swimable, Flyable {

    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 파닥 파닥 움직입니다.")
    }

    override fun fly() {
        TODO("Not yet implemented")
    }

    override fun swim() {
        TODO("Not yet implemented")
    }

    override fun act() {
        // 중복되는 인터페이스를 특정할 때 `super<타입>.함수`를 사용한다.
        super<Swimable>.act()
        super<Flyable>.act()
    }

    // Swimable 인터페이스에 존재하는 추상 프로퍼티를 override함. 추상 프로퍼티는 field가 없기 때문에 default getter가 구현되어 있지 않다면 override 반드시 해주어야 함.
    override val swimAbility: Int
        get() = TODO("Not yet implemented")

    override val legCount: Int
        get() = super.legCount + this.wingCount
}