package com.harish.library.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.repository.BookStoreRepository;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookStoreService;
import com.harish.library.service.ITagStoreService;
import com.harish.library.service.impl.BookSearchServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookSearchServiceImplTest {
	private final String isbn = "121-3-66-248511-3";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private IBookStoreService bookStoreService;

	@Mock
	private IAuthorStoreService authorStoreService;

	@Mock
	private ITagStoreService tagStoreService;

	@InjectMocks
	private BookSearchServiceImpl bookSearchService;

	@Test
	public void testSearchBooksByTitle_IfNoResults_Then_ThrowsNoResultsFoundException() throws Exception {
		thrown.expect(NoResultsFoundException.class);
		thrown.expectMessage("No books found with the given title");
		// Given
		Set<Book> books = new HashSet<>();
		when(bookStoreService.findByTitle("Harry Potter")).thenReturn(books);

		// When
		bookSearchService.searchBooksByTitle("Harry Potter");
	}

	@Test
	public void testSearchBooksByAuthorName() throws Exception {
		// Given
		Set<Book> books = new HashSet<>();
		books.add(new Book(isbn, "Harry Potter"));
		var author = mock(Author.class);
		when(author.getBooks()).thenReturn(books);
		List<Author> authorList = new ArrayList<>();
		authorList.add(author);
		when(authorStoreService.getAuthorsByName("Harish")).thenReturn(authorList);

		// When
		Set<Book> bookList = bookSearchService.searchBooksByAuthorName("Harish");

		// Then
		assertEquals(bookList, books);
	}

	@Test
	public void testSearchBooksByAuthorId() throws Exception {
		// Given
		thrown.expect(NoResultsFoundException.class);
		thrown.expectMessage("No books found for the given author id - 123");
		long author_id = 123L;
		Set<Book> books = new HashSet<>();
		var author = mock(Author.class);
		when(author.getBooks()).thenReturn(books);
		when(authorStoreService.getAuthor(author_id)).thenReturn(Optional.of(author));

		// When
		bookSearchService.searchBooksByAuthorId(author_id);
	}

}
