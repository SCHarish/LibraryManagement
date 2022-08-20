package com.harish.library.controller.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harish.library.controller.BookStoreController;
import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.AuthorNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.InvalidDataException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.model.Tag;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookStoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreControllerTest {
	private final String isbn = "121-3-66-248511-3";
	private final Long author_id = 1L;
	private MockMvc mockMvc;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private BookStoreController bookStoreController;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IBookStoreService bookStoreService;

	@MockBean
	private IAuthorStoreService authorStoreService;

	@Before
	public void setup() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(this.bookStoreController).build();
	}

	@Test
	public void testAddBook() throws Exception {
		// When
		BookRequestDto bookRequestDto = new BookRequestDto.BookRequestDtoBuilder().isbn(isbn).author(author_id)
				.title("Harry potter").tags(new String[] { "fiction" }).build();
		var book = mock(Book.class);
		when(bookStoreService.addBook(bookRequestDto)).thenReturn(book);

		// Act and Assert
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/books").content(objectMapper.writeValueAsBytes(bookRequestDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testFindBookByISBN() throws Exception {
		// When
		String url = "/api/v1/books/" + isbn;
		var author = new Author();
		author.setName("harish");
		var tags = new HashSet<Tag>();
		var book = Book.builder().isbn(isbn).title("Harry Potter").author(author).tags(tags).build();
		when(bookStoreService.findBookByISBN(isbn)).thenReturn(Optional.of(book));

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(isbn))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Harry Potter"));
	}

	@Test
	public void testAddBookInvalidISBN_ThrowsInvalidDataException() throws Exception {
		// When
		BookRequestDto bookRequestDto = new BookRequestDto.BookRequestDtoBuilder().isbn("28283").author(author_id)
				.title("Harry potter").tags(new String[] { "fiction" }).build();

		when(bookStoreService.addBook(Mockito.any(BookRequestDto.class))).thenThrow(InvalidDataException.class);

		// Act and Assert
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/api/v1/books").content(objectMapper.writeValueAsBytes(bookRequestDto))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		} catch (Exception ex) {
			assertTrue(ex.getMessage().contains("InvalidDataException"));
		}
	}

	@Test
	public void testAddBookDuplicateBook_ThrowsDuplicateBookNotFoundException() throws Exception {
		// When
		BookRequestDto bookRequestDto = new BookRequestDto.BookRequestDtoBuilder().isbn(isbn).author(author_id)
				.title("Harry potter").tags(new String[] { "fiction" }).build();

		when(bookStoreService.addBook(Mockito.any(BookRequestDto.class))).thenThrow(DuplicateBookFoundException.class);

		// Act and Assert
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/api/v1/books").content(objectMapper.writeValueAsBytes(bookRequestDto))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		} catch (Exception ex) {
			assertTrue(ex.getMessage().contains("DuplicateBookFoundException"));
		}
	}
}
