package me.kyeong.java8to11.interfaceexam;

public class DefaultFoo implements Foo {
	
	String name;
	
	public DefaultFoo(String name) {
		this.name = name;
	}

	@Override
	public void printName() {
		System.out.println(name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	// default 메소드 재정의 가능, 
	@Override
	public void printNameUpperCase() {
		Foo.super.printNameUpperCase();
	}
	

}
