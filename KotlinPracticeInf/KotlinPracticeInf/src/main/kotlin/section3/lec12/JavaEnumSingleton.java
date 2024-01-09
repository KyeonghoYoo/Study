package section3.lec12;

public enum JavaEnumSingleton {
    INSTANCE("싱글톤 Instance");

    private String value;
    JavaEnumSingleton(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
