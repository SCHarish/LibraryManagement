package com.harish.library.controller.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.harish.library.controller.BookSearchController;
import com.harish.library.model.Book;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookSearchService;
import com.harish.library.service.IBookStoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookSearchControllerTest {
	private MockMvc mockMvc;
	private final String isbn = "121-3-66-248511-3";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private BookSearchController bookSearchController;

	@MockBean
	private IBookSearchService bookSearchService;

	@Mock
	private IAuthorStoreService authorStoreService;
	
	@MockBean
	private IBookStoreService bookStoreService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookSearchController).build();
	}

	@Test
	public void testSearchBooksByTitle() throws Exception {
		// Given
		String title = "Harry Potter";
		String url = "/api/v1/title/" + title + "/books";
		Set<Book> books = new HashSet<>();
		books.add(new Book(isbn, "Harry Potter"));
		when(bookStoreService.findByTitle("Harry Potter")).thenReturn(books);
		when(bookSearchService.searchBooksByTitle(title)).thenReturn(books);
		
		
		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
	}
	
	@Test
	public void testSearchBooksByAuthorId_NoBooksFound_ThrowsNoResultsFoundException() throws Exception {
		String url = "/api/v1/author/" + 1 + "/books";
	}
}
