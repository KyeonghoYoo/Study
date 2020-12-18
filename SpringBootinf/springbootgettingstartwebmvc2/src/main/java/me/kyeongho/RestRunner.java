package me.kyeongho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

@Component
public class RestRunner implements ApplicationRunner{

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		StopWatch stopWatch = new StopWatch("RestTemplateTest");
		stopWatch.start("sample1");
		
		// TODO /sample1
		String sample1Result = restTemplate.getForObject("http://localhost:18080/sample1", String.class);
		System.out.println(sample1Result);
		
		stopWatch.stop();
		stopWatch.start("sample2");
		
		// TODO /sample2
		String sample2Result = restTemplate.getForObject("http://localhost:18080/sample2", String.class);
		System.out.println(sample2Result);
		
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
	}
}
