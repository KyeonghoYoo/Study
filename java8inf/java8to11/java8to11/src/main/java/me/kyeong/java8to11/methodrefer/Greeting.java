package me.kyeong.java8to11.methodrefer;

public class Greeting {

	private String name;

	public Greeting() {
		
	}
	
	public Greeting(String name) {
		this.name = name;
	}
	
	public String hello(String name) {
		return "hello" + name;
	}
	
	public static String hi(String name) {
		return "hi" + name;
	}
	
	public String getName() {
		return this.name;
	}
}
