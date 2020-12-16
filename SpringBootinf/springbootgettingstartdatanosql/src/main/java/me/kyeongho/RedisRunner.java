package me.kyeongho;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

//@Component
public class RedisRunner implements ApplicationRunner {

	
	private static final Logger log = LoggerFactory.getLogger(RedisRunner.class);

	
	@Autowired
	StringRedisTemplate redisTemplate;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("RedisRunner 실행");
		
		ValueOperations<String, String> values = redisTemplate.opsForValue();
		
		values.set("kyeongho", "ykh");
		values.set("springboot", "2.0");
		values.set("hello", "world");
		
		Account account = new Account();
		account.setEmail("ykh6242@gmail.com");
		account.setUserName("Kyeongho");
		
		accountRepository.save(account);
		
		Optional<Account> byId = accountRepository.findById(account.getId());
		log.info(byId.get().getUserName());
		log.info(byId.get().getEmail());
	}

}
