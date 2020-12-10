package me.kyeongho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringgettingstartedspringapplicationApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringgettingstartedspringapplicationApplication.class);
		app.addListeners(new SampleListner());
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

}