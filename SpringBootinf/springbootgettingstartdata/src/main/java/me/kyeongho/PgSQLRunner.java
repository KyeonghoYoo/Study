package me.kyeongho;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PgSQLRunner implements ApplicationRunner {
	
	
	private static final Logger log = LoggerFactory.getLogger(PgSQLRunner.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		try (Connection connection = dataSource.getConnection()) {
			log.info(connection.getMetaData().getURL());
			log.info(connection.getMetaData().getUserName());
			log.info(connection.getMetaData().getDriverName());
			Statement statement = connection.createStatement();
			
			String sql = "CREATE TABLE USERS(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
			log.info("PgSQL Update문 실행");
			statement.executeUpdate(sql);
		}
		log.info("PgSQL INSERT문 실행");
		jdbcTemplate.execute("INSERT INTO USERS VALUES (1, 'Kyeongho')");
		
	}

}
