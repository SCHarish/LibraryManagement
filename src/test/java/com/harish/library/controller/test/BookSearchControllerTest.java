package com.harish.library.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	public void testSearchBooksByAttribute_SearchWithTagAndAuthorId() throws Exception {
		// Given
		String url = "/api/v1/search/books?tag=harish&author_id=123";
		Set<Book> books = new HashSet<>();
		books.add(new Book(isbn, "Harry Potter"));
		Set<Book> book_list2 = new HashSet<>();
		book_list2.add(new Book("999-3-55-248511-1", "Harish's Biography"));
		when(bookSearchService.searchBooksByTag("harish")).thenReturn(book_list2);
		when(bookSearchService.searchBooksByAuthorId(123L)).thenReturn(books);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
	}

	@Test
	public void testSearchBooksByAttribute_SearchWithTitleAndTag() throws Exception {
		// Given
		String url = "/api/v1/search/books?title=harry%20potter&tag=fiction";
		Set<Book> books = new HashSet<>();
		books.add(new Book(isbn, "Harry Potter"));
		Set<Book> book_list2 = new HashSet<>();
		book_list2.add(new Book("999-3-55-248511-1", "Harish's Biography"));
		when(bookSearchService.searchBooksByTitle("harry potter")).thenReturn(book_list2);
		when(bookSearchService.searchBooksByTag("fiction")).thenReturn(books);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
	}

	@Test
	public void testSearchBooksByAttribute_SearchWithTag() throws Exception {
		// Given
		String url = "/api/v1/search/books?tag=fiction";
		Set<Book> books = new HashSet<>();
		books.add(new Book(isbn, "Harry Potter"));
		when(bookSearchService.searchBooksByTag("fiction")).thenReturn(books);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
		verify(bookSearchService, times(1)).searchBooksByTag("fiction");
		verify(bookSearchService, times(0)).searchBooksByTitle(Mockito.anyString());
		verify(bookSearchService, times(0)).searchBookByISBN(Mockito.anyString());
		verify(bookSearchService, times(0)).searchBooksByAuthorName(Mockito.anyString());
		verify(bookSearchService, times(0)).searchBooksByAuthorId(Mockito.any());
	}

	@Test
	public void testSearchBooksByAttribute_SearchWithTitleAndAuthorName() throws Exception {
		// Given
		String url = "/api/v1/search/books?title=Believer&tag=Life";
		Set<Book> books = new HashSet<>();
		books.add(new Book(isbn, "Harry Potter"));
		when(bookSearchService.searchBooksByTitle("Believer")).thenReturn(books);
		when(bookSearchService.searchBooksByTag("Life")).thenReturn(books);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
		verify(bookSearchService, times(1)).searchBooksByTitle("Believer");
		verify(bookSearchService, times(0)).searchBookByISBN(Mockito.anyString());
		verify(bookSearchService, times(1)).searchBooksByTag("Life");
		verify(bookSearchService, times(0)).searchBooksByAuthorId(Mockito.any());
	}

	@Test
	public void testSearchBooksByAttribute_SearchWithISBNAndAuthorId() throws Exception {
		// Given
		String url = "/api/v1/search/books?author_id=123&isbn=777-3-55-248511-2";
		Set<Book> books = new HashSet<>();
		books.add(new Book(isbn, "Harry Potter"));
		when(bookSearchService.searchBooksByAuthorId(123L)).thenReturn(books);
		when(bookSearchService.searchBookByISBN("777-3-55-248511-2"))
				.thenReturn(new Book("777-3-55-248511-2", "Harish's Biography"));

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
		verify(bookSearchService, times(0)).searchBooksByTitle(Mockito.anyString());
		verify(bookSearchService, times(1)).searchBookByISBN("777-3-55-248511-2");
		verify(bookSearchService, times(0)).searchBooksByTag(Mockito.anyString());
		verify(bookSearchService, times(1)).searchBooksByAuthorId(123L);
	}
}
