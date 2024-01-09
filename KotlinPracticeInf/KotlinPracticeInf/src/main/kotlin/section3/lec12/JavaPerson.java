package section3.lec12;

public class JavaPerson {

    private static final int MIN_AGE = 1;

    public static JavaPerson bornNewBaby(String name) {
        return new JavaPerson(name, MIN_AGE);
    }

    private String name;

    private int age;

    private JavaPerson(String name, int age) {
        name = this.name;
        age = this.age;
    }
}
