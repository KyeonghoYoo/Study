package section3.Lec10

interface Swimable {

    val swimAbility: Int
        get() = 3 // default getter를 구성해주면 하위 클래스에서 override를 해도 되고 안 해도 됨. 없으면 override 필수

    fun swim()

    /*
     * 자바와 달리 `default` 예약어를 함수 앞에 붙이지 않아도 default 메서드를 만들 수 있음.
     */
    fun act() {
        println("파닥 파닥")
    }
}