package me.kyeongho;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationWebTestClientTests {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	public SampleService mockSampleService;

	@Test
	public void test() throws Exception {
		when(mockSampleService.getName()).thenReturn("ykh");

		webTestClient.get().uri("/hello").exchange()
				.expectStatus().isOk().expectBody(String.class)
				.isEqualTo("hello ykh");
		assertThat(outputCapture.toString())
				.contains("log")
				.contains("sysout");
	}
}
