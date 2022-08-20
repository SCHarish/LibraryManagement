package com.harish.library.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.harish.library.dto.BookRequestDto;
import com.harish.library.dto.ResponseDto;
import com.harish.library.exceptions.AuthorNotFoundException;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.model.BookCopy;
import com.harish.library.repository.AuthorRepository;
import com.harish.library.repository.BookCopyRepository;
//import com.harish.library.model.BookTags;
import com.harish.library.repository.BookStoreRepository;
//import com.harish.library.repository.BookTagRepository;
import com.harish.library.repository.TagRepository;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookStoreService;
import com.harish.library.util.BookStoreUtil;
import com.harish.library.model.Tag;

/**
 * 
 * @author harishsc
 *
 */
@Service
public class BookStoreServiceImpl implements IBookStoreService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	private final BookStoreRepository bookStoreRepository;
	private final IAuthorStoreService authorStoreService;
	private final BookCopyRepository bookCopyRepository;

	@Autowired
	public BookStoreServiceImpl(BookStoreRepository bookRepository, BookCopyRepository bookCopyRepository, IAuthorStoreService authorStoreService) {
		this.authorStoreService = authorStoreService;
		this.bookStoreRepository = bookRepository;
		this.bookCopyRepository = bookCopyRepository;
	}

	@Override
	public Optional<Book> findBookByISBN(String isbn) {
		Optional<Book> book = bookStoreRepository.findById(isbn);
		return Optional.ofNullable(
				book.orElseThrow(() -> new BookNotFoundException("No book found with the given ISBN: " + isbn)));
	}

	@Override
	public Set<Book> getAllBooks() {
		Iterable<Book> bookResult = bookStoreRepository.findAll();
		Set<Book> bookList = StreamSupport.stream(bookResult.spliterator(), false).collect(Collectors.toSet());
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found in the library");
		}
		return bookList;
	}

	@Override
	public Book addBook(BookRequestDto bookDto) throws DuplicateBookFoundException {
		// Check if book is already present
		Optional<Book> bookInfo = bookStoreRepository.findById(bookDto.getIsbn());

		bookInfo.ifPresent(book -> {
			throw new DuplicateBookFoundException("Book with the isbn : " + bookDto.getIsbn() + " is already present");
		});

		LOGGER.info("No duplicate book found");

		// Check Author exist in author table
		Optional<Author> authorInfo = authorStoreService.getAuthor(bookDto.getAuthorId());

		if (authorInfo.isPresent()) {
			List<String> tags = Arrays.asList(bookDto.getTags());
			List<Tag> newTagList = BookStoreUtil.constructTags(tags);

			// converting dto to domain object
			Book newBook = BookStoreUtil.constructBook(bookDto, authorInfo.get(), newTagList);
			
			
			Book addedBook = bookStoreRepository.save(newBook);
			
			BookCopy newBookCopy = BookCopy.builder().book(addedBook).build();
			bookCopyRepository.save(newBookCopy);
			
			return addedBook;
		} else {
			throw new AuthorNotFoundException("No author found with the given author id : " + bookDto.getAuthorId());
		}
	}

	@Override
	public Book updateBook(BookRequestDto bookDto) {
		// Check Author exist in author table
		Optional<Author> authorInfo = authorStoreService.getAuthor(bookDto.getAuthorId());

		if (authorInfo.isPresent()) {
			List<String> updatedTagList = Arrays.asList(bookDto.getTags());
			List<Tag> newTagList = BookStoreUtil.constructTags(updatedTagList);
			deleteBook(bookDto.getIsbn());
			// converting dto to domain object
			Book updateBook = BookStoreUtil.constructBook(bookDto, authorInfo.get(), newTagList);
			return bookStoreRepository.save(updateBook);
		} else {
			throw new AuthorNotFoundException("Author not found exception");
		}
	}

	@Override
	public List<Book> addBooks(List<BookRequestDto> requestDtoList) {
		List<Book> newBooks = new ArrayList<Book>();
		requestDtoList.forEach((requestDto) -> {
			Optional<Author> author = Optional.empty();
			try {
				author = authorStoreService.getAuthor(requestDto.getAuthorId());
			} catch (AuthorNotFoundException ex) {
				LOGGER.error("Author not found exception");
			}
			if (author.isPresent()) {
				Optional<Book> book = Optional.empty();
				try {
					book = findBookByISBN(requestDto.getIsbn());
				} catch (BookNotFoundException ex) {
					LOGGER.error("Book not found exception");
				}
				if (!book.isPresent()) {
					List<String> updatedTagList = Arrays.asList(requestDto.getTags());
					List<Tag> newTagList = BookStoreUtil.constructTags(updatedTagList);

					// converting dto to domain object
					Book newBook = BookStoreUtil.constructBook(requestDto, author.get(), newTagList);
					newBooks.add(newBook);
				} else {
					LOGGER.error("Unable to create Book entity. Because an another Book with the same ISBN no. "
							+ requestDto.getIsbn() + " is already present");
				}
			} else {
				LOGGER.error("Unable to create Book entity. Because, no Author found with the author id : "
						+ requestDto.getAuthorId());
			}
		});
		Iterable<Book> iterable = bookStoreRepository.saveAll(newBooks);
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public Set<Book> findByTitle(String title) {
		return bookStoreRepository.findByTitle(title);
	}

	@Override
	public void deleteBook(String isbn) {
		Optional<Book> book = findBookByISBN(isbn);
		if (book.isPresent()) {
			bookStoreRepository.deleteById(isbn);
		} else {
			throw new BookNotFoundException("No book found with the given isbn : " + isbn);
		}
	}
}
