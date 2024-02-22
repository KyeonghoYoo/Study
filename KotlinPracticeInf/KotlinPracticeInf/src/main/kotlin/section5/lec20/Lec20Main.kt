package section5.lec20

import section3.Lec09.Person

fun main() {

}

fun printPerson(person: Person?) {
    person?.let {
        println(it.name)
        println(it.age)
    }
}