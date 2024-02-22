package section3.Lec10;

public interface JavaFlyable {

    void fly();

    default void act() {
        System.out.println("파닥 파닥");
    }
}
