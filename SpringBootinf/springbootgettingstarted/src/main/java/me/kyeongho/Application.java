package me.kyeongho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
    	SpringApplication application = new SpringApplication(Application.class);
    	application.setWebApplicationType(WebApplicationType.NONE);
    	application.run(args);
    }
    
    /* SpringBootApplication은 ComponentScan 후 ConfigurationPropertiesScan을 하기 때문에
     * 개발자가 등록한 Bean이 ConfigurationPropertiesScan에 의해 덮어질 수가 있다.
     */
//    @Bean
//    public HoloMan holoMan() {
//    	HoloMan holoMan = new HoloMan();
//    	holoMan.setName("ykh");
//    	holoMan.setHowLong(60);
//    	return holoMan;
//    }
}
