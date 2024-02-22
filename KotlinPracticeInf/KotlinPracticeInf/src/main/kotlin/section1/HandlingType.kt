package section1

fun main() {
    /*
     * 타입 캐스팅
     */
    val number1 = 4;

//    val number2: Long = number1; // type mismatch
    val number2:Long = number1.toLong(); // `to*()` 타입 변환 메서드 사용

    println(number1 + number2);

    /*
    * String interpolation / String indexing
    */
    val name = "유경호";
    println("이름 = ${name}");

    val multilineStr: String = """
        \"\"\"를 사용하면
        여러 줄의 문자열을
        편하게 사용할 수 있음.
        ${name}
    """.trimIndent()
    println(multilineStr);

    val str = "ABC";
    println(str[1]);
    println(str[2]);



}

fun printAgeIfPerson(obj: Any) {
    if (obj is Person) {
//        val person = obj as Person // 생략 가능
//        println(person.getAge());
        println(obj.getAge()); // 바로 obj를 Person 타입으로 사용할 수 있음.
    }
}

fun printAgeIfNullablePerson(obj: Any?) {
    if (obj is Person) {
        val person: Person? = obj as? Person
        println(person?.getAge());
    }
}