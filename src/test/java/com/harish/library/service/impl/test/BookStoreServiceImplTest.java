package com.harish.library.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.repository.AuthorRepository;
import com.harish.library.repository.BookStoreRepository;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.impl.BookStoreServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookStoreServiceImplTest {

	private final String isbn = "121-3-66-248511-3";
	private final long author_id = 1L;

	@Mock
	private BookStoreRepository bookStoreRepository;

	@Mock
	private AuthorRepository authorStoreRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private BookStoreServiceImpl bookStoreService;

	@Mock
	private IAuthorStoreService authorStoreService;

	@Test
	public void testAddBook() {
		// Given
		BookRequestDto requestDto = mock(BookRequestDto.class);
		var author = mock(Author.class);
		when(requestDto.getIsbn()).thenReturn(isbn);
		when(requestDto.getAuthorId()).thenReturn(author_id);
		when(requestDto.getTags()).thenReturn(new String[] { "fiction", "literature" });
		when(bookStoreRepository.findById(isbn)).thenReturn(Optional.empty());
		when(authorStoreService.getAuthor(author_id)).thenReturn(Optional.of(author));

		// When
		bookStoreService.addBook(requestDto);

		// Then
		verify(bookStoreRepository).save(Mockito.any(Book.class));
	}

	@Test
	public void testFindBookByISBN() {
		// Given
		Optional<Book> book = Optional.of(new Book());
		when(bookStoreRepository.findById(isbn)).thenReturn(book);

		// When
		Optional<Book> actualBook = bookStoreService.findBookByISBN(isbn);

		// Then
		assertEquals(actualBook, book);
	}

	@Test
	public void testFindBookByISBN_Given_NoBookIsFoundForISBN_Then_ThrowsBookNotFoundException() {
		// Given
		thrown.expect(BookNotFoundException.class);
		thrown.expectMessage("No book found with the given ISBN: " + isbn);
		when(bookStoreRepository.findById(isbn)).thenReturn(Optional.empty());

		// When
		bookStoreService.findBookByISBN(isbn);
	}

	@Test
	public void testGetAllBooks_IfResultIsEmpty_Then_ThrowNoResultsFoundException() {
		// Given
		thrown.expect(NoResultsFoundException.class);
		thrown.expectMessage("No books found in the library");
		Set<Book> books = new HashSet<>();

		// When
		when(bookStoreRepository.findAll()).thenReturn(books);

		// When
		bookStoreService.getAllBooks();
	}

}
