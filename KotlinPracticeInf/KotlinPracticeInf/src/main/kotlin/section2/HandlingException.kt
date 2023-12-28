package section2

import java.lang.NumberFormatException

fun parseOntOrThrow(str: String): Int {
    try {
        return str.toInt()
    } catch (ex: NumberFormatException) {
        throw IllegalArgumentException("주어진 ${str}은 숫자가 아닙니다.")
    }
}

fun parseOntOrThrowV2(str: String): Int? {
    return try {
        str.toInt()
    } catch (ex: NumberFormatException) {
        null
    }
}