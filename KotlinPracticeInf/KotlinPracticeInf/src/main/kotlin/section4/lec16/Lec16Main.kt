package section4.lec16

fun Person.nextYearAge(): Int {
    println("확장함수")
    return this.age + 1
}

fun main() {
    val person = Person("A", "B", 100)
    println(person.nextYearAge())
}