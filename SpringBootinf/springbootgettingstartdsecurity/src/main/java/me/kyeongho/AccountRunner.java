package me.kyeongho;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import me.kyeongho.account.Account;
import me.kyeongho.account.AccountService;

@Component
public class AccountRunner implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(AccountRunner.class);

	@Autowired
	AccountService accountService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Account kyeongho = accountService.createAccount("Kyeongho", "1234");

		log.info("userName: " + kyeongho.getUsername() + ", password: " + kyeongho.getPassword());
	}

}
