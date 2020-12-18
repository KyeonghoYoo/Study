package me.kyeongho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class WebClientRunner implements ApplicationRunner{

	@Autowired
	WebClient.Builder builder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		WebClient webClient = builder
				.baseUrl("http://localhost:18080") // 지역적으로 Customizing 하는 법
				.build();
		
		StopWatch stopWatch = new StopWatch("WebClientTest");
		stopWatch.start("");
		
		// TODO /sample1
		Mono<String> sample1Mono = webClient.get().uri("/sample1")
				.retrieve()
				.bodyToMono(String.class);
		
		sample1Mono.subscribe(s -> {
			System.out.println(s);
			
			if (stopWatch.isRunning()) {
				stopWatch.stop();
			}
			
			System.out.println(stopWatch.prettyPrint());
			stopWatch.start("");
		});
		
		
		// TODO /sample2

		Mono<String> sample2Mono = webClient.get().uri("/sample2")
				.retrieve()
				.bodyToMono(String.class);
		
		sample2Mono.subscribe(s -> {
			System.out.println(s);
			
			if (stopWatch.isRunning()) {
				stopWatch.stop();
			}
			
			System.out.println(stopWatch.prettyPrint());
			stopWatch.start("");
		});
		
	}
}
