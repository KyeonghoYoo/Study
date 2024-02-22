package section3.Lec13;

public class JavaHouse {

    private String address;
    private LivingRoom livingRoom;

    public JavaHouse(String address) {
        this.address = address;
        livingRoom = new LivingRoom(10);
    }

    public LivingRoom getLivingRoom() {
        return livingRoom;
     }

    public class LivingRoom {

        private double area;

        public LivingRoom(double area) {
            this.area = area;
        }

        public double getArea() {
            return area;
        }

        public String getAddress() {
            return JavaHouse.this.address;
        }
    }
}
