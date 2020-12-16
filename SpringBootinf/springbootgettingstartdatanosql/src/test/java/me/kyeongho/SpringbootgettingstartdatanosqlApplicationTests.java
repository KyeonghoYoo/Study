package me.kyeongho;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import me.kyeongho.mongo.MongoAccount;
import me.kyeongho.mongo.MongoAccountRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SpringbootgettingstartdatanosqlApplicationTests {

	@Autowired
	MongoAccountRepository accountRepository;
	
	@Test
	public void test() {
		MongoAccount account = new MongoAccount();
		account.setUserName("kyeongho");
		account.setEmail("ykh6242@gmail.com");
		
		accountRepository.save(account);
		
		Optional<MongoAccount> byId = accountRepository.findById(account.getId());
		assertThat(byId).isNotEmpty();
		
		Optional<MongoAccount> ByEmail = accountRepository.findByEmail(account.getEmail());
		assertThat(ByEmail).isNotEmpty();
		assertThat(ByEmail.get().getUserName()).isEqualTo("kyeongho");
		
	}

}
