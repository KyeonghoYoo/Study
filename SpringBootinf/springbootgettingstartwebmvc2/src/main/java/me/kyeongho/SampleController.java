package me.kyeongho;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/hello")
	public EntityModel<Hello> hello() {
		Hello hello = new Hello();
		hello.setPrefix("Hey, ");
		hello.setName("Kyeongho");
		
		// 연관 링크 추가하는 방법 (여러 방법 중 하나)
		EntityModel<Hello> helloEntityModel = EntityModel.of(hello);
		helloEntityModel.add(Link.of("http://localhost:18080/hello"));
		
		return helloEntityModel;
	}
	
	@GetMapping("/sample1")
	public String sample1() throws InterruptedException {
		Thread.sleep(5000l);
		return "sample1";
	}
	
	@GetMapping("/sample2")
	public String sample2() throws InterruptedException {
		Thread.sleep(3000l);
		return "sample2";
	}
}
