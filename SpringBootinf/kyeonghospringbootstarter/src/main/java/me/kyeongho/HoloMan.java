package me.kyeongho;

public class HoloMan {
    private String name;
    private int howLong;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowLong() {
        return howLong;
    }

    public void setHowLong(int howLong) {
        this.howLong = howLong;
    }

    @Override
    public String toString() {
        return "HoloMan{" +
                "name='" + name + '\'' +
                ", howLong=" + howLong +
                '}';
    }
}
