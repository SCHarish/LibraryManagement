package com.harish.library.controller.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.harish.library.controller.BulkImportController;
import com.harish.library.model.Book;
import com.harish.library.service.IBookStoreService;
import com.harish.library.service.IBulkDataImportService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BulkImportControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private BulkImportController bulkImportController;
	
	@MockBean
	private IBulkDataImportService bulkDataImportService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bulkImportController).build();
	}

	@Test
	public void testUploadBooksFromCSV_Success_ThenVerifyStatus() throws Exception {
		MockMultipartFile file = new MockMultipartFile("csvfile", "sample.csv", MediaType.TEXT_PLAIN_VALUE,
				"Library, Management".getBytes());
		List<Book> bookList = new ArrayList();
		bookList.add(Book.builder().isbn("121-3-66-248511-3").title("Harry Potter").build());
		when(bulkDataImportService.importBooksFromFile(file)).thenReturn(bookList);
		
		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/upload/books").file(file)
				.contentType(MediaType.TEXT_PLAIN_VALUE)).andExpect(status().isCreated());
	}
	
	@Test
	public void testUploadBooksFromCSV_Failure_ThenVerifyStatus() throws Exception {
		MockMultipartFile file = new MockMultipartFile("csvfile", "sample.csv", MediaType.TEXT_PLAIN_VALUE,
				"Library, Management".getBytes());

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/upload/books").file(file)
				.contentType(MediaType.TEXT_PLAIN_VALUE)).andExpect(status().is4xxClientError());
	}
}
