package me.kyeongho.sample;

public class MetaData {

	private String name;
	
	private String someString;
	
	private int someNumber;

	public MetaData() {

	}
	
	public MetaData(String name, String someString, int someNumber) {
		this.name = name;
		this.someString = someString;
		this.someNumber = someNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSomeString() {
		return someString;
	}

	public void setSomeString(String someString) {
		this.someString = someString;
	}

	public int getSomeNumber() {
		return someNumber;
	}

	public void setSomeNumber(int someNumber) {
		this.someNumber = someNumber;
	}

	@Override
	public String toString() {
		return "MetaData [name=" + name + ", someString=" + someString + ", someNumber=" + someNumber + "]";
	}
	
	
}
