package me.kyeongho.sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
@AutoConfigureMockMvc
public class SampleControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void 파일업로드_테스트() throws Exception {
		MockMultipartFile image = new MockMultipartFile("file", "imagefile.jpeg", "image/jpeg", "<<jpeg data>>".getBytes());
		
		this.mockMvc
				.perform(multipart("/form")
						.file(image)
						.param("name", "value")
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8"))
				.andExpect(status().isOk());
	}

	@Test
	public void json_and_image_파일업로드_테스트() throws Exception {
		MockMultipartFile image = new MockMultipartFile("file-data", "filename-1.jpeg", "image/jpeg", "<<jpeg data>>".getBytes());

		String content = objectMapper.writeValueAsString(new MetaData("name", "stringSomething", 100));
		MockMultipartFile json = new MockMultipartFile("meta-data", "jsondata", "application/json", content.getBytes(StandardCharsets.UTF_8));

		this.mockMvc
				.perform(multipart("/form/requestpart")
						.file(json)
						.file(image)
						.contentType("multipart/mixed")
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8"))
				.andExpect(status().isOk());

	}

}
