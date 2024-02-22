package section3.lec14

fun main() {
    val personDto1 = PersonDto("유경호", 29)
    val personDto2 = PersonDto("유경호", 30)

    println(personDto1) // PersonDto(name=유경호, age=29)
    println(personDto1 == personDto2) // false
}

data class PersonDto(
    val name: String,
    val age: Int
)