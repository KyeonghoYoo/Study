package section3.lec12

class Person private constructor(
    private var name: String,
    private var age: Int
) {
    /*
     * 코틀린에서는 static이라는 키워드가 존재하지 않음.
     * 이를 대체하는 수단으로 동행 객체(companion object)가 잇음.
     */
    companion object Factory : Log {
        private const val MIN_AGE = 1;

        @JvmStatic // java에서 클래스이름으로 접근할 수 있도록 해줌.
        fun bornNewBaby(name: String) = Person(name, MIN_AGE)
        override fun log() {
            println("나는 Person 클래스의 동행 객체에요.")
        }
    }
}
