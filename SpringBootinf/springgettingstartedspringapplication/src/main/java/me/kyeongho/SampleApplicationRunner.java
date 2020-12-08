package me.kyeongho;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleApplicationRunner implements ApplicationRunner{
	
	@Value(value = "${kyeongho.name}")
	private String name;
	
	@Value(value = "${kyeongho.age}")
	private int age;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("foo: " + args.containsOption("foo"));
		System.out.println("bar: " + args.containsOption("bar"));
		
		System.out.println("name: " + name);
		System.out.println("age: " + age);
	}
}
