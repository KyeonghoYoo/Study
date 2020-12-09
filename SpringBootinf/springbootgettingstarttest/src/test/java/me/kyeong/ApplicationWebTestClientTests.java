package me.kyeong;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import me.kyeongho.SampleService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationWebTestClientTests {

	@Autowired
	WebTestClient webTestClient;
	
	@MockBean
	SampleService mockSampleService;

	@Test
	public void test() throws Exception {
		when(mockSampleService.getName()).thenReturn("ykh");
		
		webTestClient.get().uri("/hello").exchange().expectStatus().isOk()
		.expectBody(String.class).isEqualTo("hello ykh");
	}
}
