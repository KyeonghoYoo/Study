package me.kyeongho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class HolomanRunner implements ApplicationRunner{
	
	@Autowired
	HoloMan holoMan;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(holoMan);
		
	}

}
