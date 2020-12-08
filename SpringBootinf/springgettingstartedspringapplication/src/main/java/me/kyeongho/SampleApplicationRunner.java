package me.kyeongho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleApplicationRunner implements ApplicationRunner{
	
	@Autowired
	KyeonghoProperties kyeonghoProperties;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("foo: " + args.containsOption("foo"));
		System.out.println("bar: " + args.containsOption("bar"));
		
		System.out.println("name: " + kyeonghoProperties.getName());
		System.out.println("age: " + kyeonghoProperties.getAge());
		System.out.println("sessionTimeout: " + kyeonghoProperties.getSessionTimeout());
	}
}
