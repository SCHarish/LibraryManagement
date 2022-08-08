package com.harish.library.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.harish.library.controller.BulkImportController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BulkImportControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private BulkImportController bulkImportController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bulkImportController).build();
	}

	@Test
	public void whenFileUploaded_thenVerifyStatus() throws Exception {
		MockMultipartFile file = new MockMultipartFile("csvfile", "sample.csv", MediaType.TEXT_PLAIN_VALUE,
				"Library, Management".getBytes());

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/upload/books").file(file)
				.contentType(MediaType.TEXT_PLAIN_VALUE)).andExpect(status().isCreated());
	}
}
