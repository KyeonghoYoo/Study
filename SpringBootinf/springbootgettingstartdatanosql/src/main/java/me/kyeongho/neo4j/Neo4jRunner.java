package me.kyeongho.neo4j;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner{
	
	private static final Logger log = LoggerFactory.getLogger(Neo4jRunner.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	Neo4jAccountRepository accountRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Neo4jAccount account = new Neo4jAccount();
		account.setUserName("kyeongho");
		account.setEamil("ykh6242@gmail.com");
		
		Role role = new Role();
		role.setName("admin");
		
		account.getRoles().add(role);
		
		/*
		Session session = sessionFactory.openSession();
		session.save(account);

		// 닫아주지 않으면 어플리케이션이 실제로 종료되지 않는다.
		sessionFactory.close();
		*/
		
		accountRepository.save(account);
		
		log.info("Finished");
	}

}
