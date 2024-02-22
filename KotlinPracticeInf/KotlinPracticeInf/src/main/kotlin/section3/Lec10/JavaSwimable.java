package section3.Lec10;

public interface JavaSwimable {


    void swim();

    default void act() {
        System.out.println("어푸 어푸");
    }
}
