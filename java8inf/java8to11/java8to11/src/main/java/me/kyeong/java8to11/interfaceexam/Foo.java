package me.kyeong.java8to11.interfaceexam;

public interface Foo {

	void printName();

	// 특정 기능을 이 인터페이스를 구현하고 있는 구현체들에게 오버라이딩 없이 새로 지원해줄 수 있다.
	/** 
	 * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
	 */
	default void printNameUpperCase() {
		System.out.println(getName().toUpperCase());
	}
	// Object에서 제공하는 default 메소드들은 default 메소드로 재정의 할 수 없다. 컴파일 에러가 난다.
//	default toString() {
//		
//	}
	
	// 선언하는 것은 괜찮다.
	String toString();
	
	String getName();
	
	// 인터페이스를 참조하여 사용할 수 있는 static 메소드
	static void printAnything() {
		System.out.println("Foo");
	}
}
