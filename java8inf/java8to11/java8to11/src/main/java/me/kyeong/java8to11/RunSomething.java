package me.kyeong.java8to11;

@FunctionalInterface
public interface RunSomething {
	
	int doIt(int number);
	
	static void printName() {
		System.out.println("Kyeongho");
	}
	
	default void printAge() {
		System.out.println("40");
	}
	
}
