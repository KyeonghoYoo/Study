package section3.Lec10;

public class JavaCat extends JavaAnimal {

    public JavaCat(String species) {
        super(species, 4);
    }

    @Override
    void move() {
        System.out.println("고양이가 사뿐 사뿐 걸어갑니다.");
    }
}
