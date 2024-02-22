package section3.lec11

/*
 * 코틀린에서는 아래와 같이 파일에 유틸성 함수만 존재하는 경우, 파일이름으로 정적메소드로써 접근할 수 있음.
 * e.g. StringUtilsKt.isDirectoryPath()
 */
fun isDirectoryPath(path: String): Boolean {
    return path.endsWith("/")
}