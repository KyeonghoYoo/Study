package section3.lec14;

public enum JavaCountry {
    KOREA("KOR"),
    AMERICA("USA")
    ;

    private final String code;

    JavaCountry(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
