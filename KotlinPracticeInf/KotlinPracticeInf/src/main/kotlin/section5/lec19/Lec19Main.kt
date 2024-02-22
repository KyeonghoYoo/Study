package section5.lec19

data class Person(
    val name: String,
    val age: Int
)

class CustomDataPerson(
    val name: String,
    val age: Int
) {
    operator fun component1(): String = this.name
    operator fun component2(): Int = this.age
}

fun getNumberOrNull(number: Int): Int? {
    return if (number <= 0) {
        null
    } else {
        number
    }
}

fun getNumberOrNullUsingTakeIF(number: Int): Int? {
    return number.takeIf { number -> number > 0 }
}

fun main() {
    // 구조 분해와 ComponentN
    val person = Person("유경호", 30)
    val (name, age) = person
    println("이름 : ${name}, 나이 : ${age}")

    val nameComponent = person.component1()
    val ageComponent = person.component1()

    // jump와 label

    val numbers = listOf(1, 2, 3)

    numbers.map { number -> number + 1 }
        .forEach { number -> println(number) } // foreach에서 break, continue를 사용할 수 없음.

    run {
        numbers.forEach { number ->
            if (number == 1) {
                return@forEach // continue와 동작 동일함
            }
            if (number == 2) {
                return@run // break와 동작 동일함.
            }
            println(number)
        }
    }
}