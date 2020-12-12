package me.kyeongho;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/hello")
	public Resource<Hello> hello() {
		Hello hello = new Hello();
		hello.setPrefix("Hey, ");
		hello.setName("Kyeongho");
		
		// 연관 링크 추가하는 방법 (여러 방법 중 하나)
		Resource<Hello> helloResource = new Resource<Hello>(hello);
		helloResource.add(linkTo(methodOn(SampleController.class).hello()).withSelfRel());
		
		return helloResource;
	}
}
