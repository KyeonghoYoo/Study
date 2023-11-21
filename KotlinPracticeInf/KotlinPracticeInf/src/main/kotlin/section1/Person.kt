package section1

class Person(private val name: String, private val age: Int) {

    fun getName(): String {
        return name;
    }

    fun getAge(): Int {
        return age;
    }
}