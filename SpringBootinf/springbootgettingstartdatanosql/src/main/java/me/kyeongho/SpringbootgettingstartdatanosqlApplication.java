package me.kyeongho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import me.kyeongho.mongo.MongoAccount;
import me.kyeongho.mongo.MongoAccountRepository;

@SpringBootApplication
public class SpringbootgettingstartdatanosqlApplication {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	MongoAccountRepository accountRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootgettingstartdatanosqlApplication.class, args);
	}
	
	// 람다식을 활용하여 아래와 같이 ApplicaitonRunner를 구성할 수도 있다.
	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
//			MongoAccount account = new MongoAccount();
//			account.setUserName("Kyeongho");
//			account.setEmail("ykh6242@gmail.com");
			
//			mongoTemplate.insert(account);
			
			MongoAccount account = new MongoAccount();
			account.setUserName("ykh");
			account.setEmail("ykh@naver.com");
			
			accountRepository.insert(account);
		};
	}

}
