package me.kyeong.java8to11.interfaceexam;

public interface Bar extends Foo {
	
	// 상위 인터페이스에서 제공하는 default 메소드를 사용하고 싶지 않으면 재정의하도록 선언하면 된다.
	void printNameUpperCase();
}
