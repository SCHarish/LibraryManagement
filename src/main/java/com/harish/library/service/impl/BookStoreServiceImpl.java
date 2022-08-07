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
import org.springframework.stereotype.Service;

import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.AuthorNotFoundException;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.repository.AuthorRepository;
//import com.harish.library.model.BookTags;
import com.harish.library.repository.BookStoreRepository;
//import com.harish.library.repository.BookTagRepository;
import com.harish.library.repository.TagRepository;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookStoreService;
import com.harish.library.util.BookStoreUtil;
import com.harish.library.model.Tag;

@Service
public class BookStoreServiceImpl implements IBookStoreService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	private final BookStoreRepository bookStoreRepository;
	private final IAuthorStoreService authorStoreService;

	@Autowired
	public BookStoreServiceImpl(BookStoreRepository bookRepository, IAuthorStoreService authorStoreService) {
		this.authorStoreService = authorStoreService;
		this.bookStoreRepository = bookRepository;
	}

	@Override
	public Optional<Book> findBookByISBN(String isbn) {
		Optional<Book> book = bookStoreRepository.findById(isbn);
		return Optional.ofNullable(book.orElseThrow(() -> new BookNotFoundException("No book found with the given ISBN: " + isbn)));
	}

	@Override
	public Optional<Set<Book>> getAllBooks() {
		Optional<Iterable<Book>> bookResult = Optional.ofNullable(bookStoreRepository.findAll());
		Iterable<Book> iterable = bookResult.get();
		Optional<Set<Book>> bookList = Optional.of(StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toSet()));
		if (bookList.get().size() == 0) {
			throw new NoResultsFoundException("No books found in the library");
		}
		return bookList;
	}

	@Override
	public Book addBook(BookRequestDto bookDto) throws DuplicateBookFoundException {
		// Check if book is already present
		Optional<Book> bookInfo = bookStoreRepository.findById(bookDto.getIsbn());

		bookInfo.ifPresent(book -> {
			throw new DuplicateBookFoundException("Book with same isbn is already present");
		});

		LOGGER.info("No duplicate book found");

		// Check Author exist in author table
		Optional<Author> authorInfo = authorStoreService.getAuthor(bookDto.getAuthorId());

		if (authorInfo.isPresent()) {
			List<String> tags = Arrays.asList(bookDto.getTags());
			List<Tag> newTagList = BookStoreUtil.constructTags(tags);

			// converting dto to domain object
			Book newBook = BookStoreUtil.constructBook(bookDto, authorInfo.get(), newTagList);
			return bookStoreRepository.save(newBook);
		} else {
			throw new AuthorNotFoundException("Author not found exception");
		}
	}

	@Override
	public Book updateBook(BookRequestDto bookDto) {
		// Check Author exist in author table
		Optional<Author> authorInfo = authorStoreService.getAuthor(bookDto.getAuthorId());

		if (authorInfo.isPresent()) {
			List<String> updatedTagList = Arrays.asList(bookDto.getTags());
			List<Tag> newTagList = BookStoreUtil.constructTags(updatedTagList);

			// converting dto to domain object
			Book updateBook = BookStoreUtil.constructBook(bookDto, authorInfo.get(), newTagList);
			return bookStoreRepository.save(updateBook);
		} else {
			throw new AuthorNotFoundException("Author not found exception");
		}
	}

	@Override
	public void addBooks(List<BookRequestDto> requestDtoList) {
		List<Book> newBooks = new ArrayList<Book>();
		requestDtoList.forEach((requestDto) -> {
			Optional<Author> authorInfo = authorStoreService.getAuthor(requestDto.getAuthorId());
			if (authorInfo.isPresent()) {
				List<String> updatedTagList = Arrays.asList(requestDto.getTags());
				List<Tag> newTagList = BookStoreUtil.constructTags(updatedTagList);

				// converting dto to domain object
				Book newBook = BookStoreUtil.constructBook(requestDto, authorInfo.get(), newTagList);
				newBooks.add(newBook);
			}
		});
		bookStoreRepository.saveAll(newBooks);
	}

	@Override
	public Set<Book> searchBooks(String keyword) {
		Set<Book> bookList = bookStoreRepository.searchBooks(keyword);
		return bookList;
	}

	@Override
	public Set<Book> findByTitle(String title) {
		Set<Book> bookList = bookStoreRepository.findByTitle(title);
		return bookList;
	}

	@Override
	public void deleteBook(String isbn) {
		bookStoreRepository.deleteById(isbn);
	}
}
