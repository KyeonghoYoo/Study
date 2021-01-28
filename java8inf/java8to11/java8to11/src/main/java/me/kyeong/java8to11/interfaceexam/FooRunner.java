package me.kyeong.java8to11.interfaceexam;

public class FooRunner {
	public static void main(String[] args) {
		Foo foo = new DefaultFoo("kyeongho");
		
		foo.printName();
		
		foo.printNameUpperCase();
	}
}
