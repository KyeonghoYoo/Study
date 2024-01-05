package section3.Lec10

interface Flyable {

    /*
     * 추상 메서드
     */
    fun fly()

    /*
     * 자바와 달리 `default` 예약어를 함수 앞에 붙이지 않아도 default 메서드를 만들 수 있음.
     */
    fun act() {
        println("파닥 파닥")
    }
}