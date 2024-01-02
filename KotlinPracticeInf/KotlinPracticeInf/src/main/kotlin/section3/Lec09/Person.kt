package section3.Lec09

/*
 * - 코틀린은 기본 접근 제한자가 public이라서 자바와 달리 일반적으로 생략해줄 수 있음.
 * - constructor라는 지시어를 통해 생성자를 생성해줄 수 있음. 생성자를 통해 전해 받은 인자를 초기화해주는 코드가 필요함.
 *     - 예시 **********************************************
 *       * class Hello constructor(message: String) {     *
 *       *   var message = message                        *
 *       * }                                              *
 *       **************************************************
 * - 코틀린에서는 클래스의 필드를 프로퍼티라 칭하며 프로퍼티는 다음과 같이 이루어져 있음.
 *   ** 프로퍼티 = 필드 + getter + setter **
 *   즉, 코틀린에서는 필드만 만들면 getter, setter를 자동으로 만들어준다.
 * - constructor 지시어는 생략할 수 있음.
 *     - 예시 **********************************************
 *       * class Hello(message: String) {                 *
 *       *   var message = message                        *
 *       * }                                              *
 *       **************************************************
 * - 코틀린에서는 생성자에서 아래와 같이 바로 프로퍼티를 생성할 수 있음.
 *     - 예시 **********************************************
 *      * class Hello(var message: String) {              *
 *      * }                                               *
 *      ***************************************************
 */

/*
 * Person 클래스의 바디는 비어 있으며, 생성자와 name, age 프로퍼티만 속성으로 갖고 있어 아래와 같은 형태가 가능함.
 */
class Person(
    /*
     * 부 생성자를 사용하는 것보다 아래와 같이 default parameter를 사용하는 것을 kotlin 진영에서는 권장하고 있음.
     */
    var name: String = "유경호",
    var age: Int = 1
) {

    /*
     * init(초기화) 블록은 생성자가 호출되는 시점에 호출됨.
     */
    init {
        println("초기화 블럭")

        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}살이 될 수 없습니다.")
        }
    }

    /*
     * 생성자를 추가로 작성하고 싶은 경우 아래처럼 constructor를 추가해줄 수 있음.
     */
    constructor(name: String): this(name, 1) {
        println("첫 번째 부생성자")
    }

    constructor(): this("유사월") {
        println("두 번째 부생성자")
    }

    //// Person 객체가 성인인지 확인하는 방법 구성
    // 1. function으로 구성
    fun isAdultAsFunction(): Boolean = this.age >= 20
    // 2. 프로퍼티로 구성
    val isAdultAsProperty: Boolean
    get() = this.age >= 20

    //// name을 get할 때 무조건 대문자로 바꾸는 예시
    /*
     * field = field라는 예약어는 아래 secondaryName 자기 자신을 의미함. 만약 secondaryName.uppercase()의 형태로 호출하게 되면
     * secondaryName를 호출한 시점에 get()이 호출됨.
     * 그렇게 되면 무한루프에 빠지게 됨. get을 호출하니 그 안에서 secondaryName를 호출하니 get이 또 호출되는 것임.
     * 이를 막기 위해 코틀린에서는 field라는 에약어를 장치로 둠. 이러한 필드를 보이지 않는 뒤에 있는 필드다하여 backing field라고 칭함.
     */
    val secondaryName = name
        get() = field.uppercase()
    /*
     * 위와 같은 backing field라는 기능을 제공하고 있으나 실무에서는 잘 사용하지 않게 됨. 위 코드는 아래와 같이 대체할 수 있기 때문임.
     */
    fun getUppercaseName(): String = this.name.uppercase()
    var uppercaseName: String
        get() = this.name.uppercase()
        set(value) {
            this.name = value.uppercase()
        }
}

fun main() {
    val person = Person("유경호", 29)
    /*
     * 코틀린에서 프로퍼티에 접근하는 방법은 `객체.프로퍼티명` 형식으로 접근함.
     * .으로 읽기(getter), 쓰기(setter)가 가능함.
     */
    println(person.name)
    person.age = 30
    println(person.age)
}