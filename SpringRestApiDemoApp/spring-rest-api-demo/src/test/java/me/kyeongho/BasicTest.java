package me.kyeongho;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import me.kyeongho.common.NaverApiClientProperties;

@Slf4j
@SpringBootTest
class BasicTest {

	@Autowired
	NaverApiClientProperties properties;
	
	@Test
	void 프로퍼티_받아오기() {
		
		log.info(properties.toString());
		
		assertTrue(StringUtils.hasText(properties.getId()));
		assertTrue(StringUtils.hasText(properties.getSceret()));
		assertTrue(StringUtils.hasText(properties.getMovieurl()));
	}

}
