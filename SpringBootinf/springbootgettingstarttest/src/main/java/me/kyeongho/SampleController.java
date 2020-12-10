package me.kyeongho;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	Logger log = LoggerFactory.getLogger(SampleController.class);

	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping("/hello")
	public String hello() {
		log.info("log");
		System.out.println("sysout");
		return "hello " + sampleService.getName();
	}
}
