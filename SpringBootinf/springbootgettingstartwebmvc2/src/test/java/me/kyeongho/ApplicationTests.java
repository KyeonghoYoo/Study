package me.kyeongho;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SampleController.class)
public class ApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception {
		mockMvc.perform(get("/hello"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$._links.self").exists());
	}

}
