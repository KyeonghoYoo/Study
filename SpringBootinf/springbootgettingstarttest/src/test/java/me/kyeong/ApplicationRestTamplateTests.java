package me.kyeong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationRestTamplateTests {
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@MockBean
	SampleService mockSampleService;
	
	@Test
	public void test() throws Exception {
		when(mockSampleService.getName()).thenReturn("ykh");
		
		String result = testRestTemplate.getForObject("/hello", String.class);
		assertThat(result).isEqualTo("hello ykh");
	}
}
