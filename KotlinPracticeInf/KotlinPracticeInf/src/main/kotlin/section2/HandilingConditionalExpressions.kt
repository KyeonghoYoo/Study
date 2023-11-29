package section2

/**
 * 5강. 코틀린에서 제어문 다루는 방법 자바 코드
 *
 * @see Lec05Main
 */


/**
 * 자바 코드와 다른 점
 * - void 대신 Unit을 사용하며 이 점이 생략됨.
 * - 함수 만들 때 fun을 사용함
 * - 예외를 던질 때 new 연산자를 붙여 인스턴스를 생성하지 않음.
 */
fun validateScoreIsNotNegative(score: Int) {
    if (score < 0) {
        throw IllegalArgumentException("${score}는 0보다 작을 수 없습니다.")
    }

}

fun validateScoreIsNotIn(score: Int) {
    if (score !in 0..100) {
        throw IllegalArgumentException("${score}는 0보다 작거나 100보다 클 수 없습니다.")
    }
}

fun getPassOrFail(score: Int): String {
    if (score >= 50) {
        return "P"
    } else {
        return "F"
    }
    /*
     * 위 코드가 아래와 같이 표현이 가능함. 코틀린에서 if문은 값이 반환되는 Expression이기 때문임.
     * 자바에서 expression인 3항 연산자 (like. a ? b : c)는 코틀린에서 if-else가 대체할 수 있기 때문에 삭제됨.
     */
    return if (score >= 50) {
        "P"
    } else {
        "F"
    }
}

fun getGreade(score: Int): String {
    return if (score >= 90) {
        "A"
    } else if (score >= 80) {
        "B"
    } else if (score >= 70) {
        "C"
    } else {
        "D"
    }
}

/**
 * 자바의 switch..case 문을 대체하는 when..else 문
 *
 * @see Lec05Main.getGradeWithSwitch
 */
fun getGradeWithSwitch(score: Int): String {
    return when (score / 10) {
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "D"
    }

    return when (score) {
        in 90..99 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "D"
    }
}

fun startsWithA(obj: Any): Boolean {
    /*
     * when..else문과 is 연산자를 사용하면 아래와 같이 스마트 캐스트도 활용 가능함.
     * when..else문에 조건부에는 어떠한 Expression도 올 수 있기 때문임.
     */
    return when(obj) {
        is String -> obj.startsWith("A")
        if (obj is Int) true else false -> (obj as Int) == 1
        else -> false
    }
}

fun judgheNumber(number: Int) {
    when (number) {
        1, 0, -1 -> println("어디서 많이 본 숫자입니다.")
        else -> println("1, 0, -1이 아닙니다.")
    }

    if (number in listOf(1, 0, -1)) {
        println("어디서 많이 본 숫자입니다.")
    } else {
        println("1, 0, -1이 아닙니다.")
    }
}


fun judgheNumber2(number: Int) {
    if (number == 0) {
        println("어디서 많이 본 숫자입니다.")
        return
    }
    if (number % 2 == 0) {
        println("주어진 숫자는 짝수입니다.")
        return
    }
    println("주어진 숫자는 홀수입니다.")

    /*
     * when 버전
     */
    when {
        number == 0 -> println("주어진 숫자는 0입니다")
        number % 2 == 0 -> println("주어진 숫자는 짝수입니다")
        else -> println("주어진 숫자는 홀수입니다.")
    }
}
