package section3.lec11;

public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("유틸성 클래스입니다.");
    }

    public static boolean isDirectoryPath(String path) {
        return path.endsWith("/");
    }
}
