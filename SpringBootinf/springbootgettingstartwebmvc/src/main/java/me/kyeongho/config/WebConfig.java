package me.kyeongho.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc // 스프링 MVC에 대한 설정을 재정의할 때 사용함.
public class WebConfig implements WebMvcConfigurer {
	
	// 아래와 같은 방법으로 정적 리소스 위치를 추가할 수 있음.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/m/**")
				.addResourceLocations("classpath:/m/")
				.setCachePeriod(20); // 캐싱 전략을 따로 주입해줘야함.
	}

	
}
