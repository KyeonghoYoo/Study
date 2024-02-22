package section3.Lec10;

import org.jetbrains.annotations.NotNull;

public class JavaPenguin extends JavaAnimal implements JavaSwimable, JavaFlyable {

    private final int wingCount;

    public JavaPenguin(@NotNull String spcies) {
        super(spcies, 2);
        this.wingCount = 2;
    }

    @Override
    public void move() {
        System.out.println("펭귄이 파닥 파닥 움직입니다.");
    }

    @Override
    public void fly() {
        JavaFlyable.super.act();
    }

    @Override
    public void swim() {
        JavaSwimable.super.act();
    }

    /*
     * 같은 이름의 메서드를 가진 인터페이스를 override하는 일반적인 방식
     */
    @Override
    public void act() {
        JavaSwimable.super.act();
        JavaFlyable.super.act();
    }

    public int getWingCount() {
        return wingCount;
    }

    @Override
    public int getLegCount() {
        return super.legCount + this.wingCount;
    }
}
