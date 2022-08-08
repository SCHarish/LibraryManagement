package com.harish.library.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.AuthorNotFoundException;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.InvalidDataException;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Book;
import com.harish.library.service.IBookSearchService;
import com.harish.library.service.impl.BookSearchServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Search Controller")
public class BookSearchController {
	private final IBookSearchService bookSearchService;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookSearchController.class);

	@Autowired
	public BookSearchController(IBookSearchService bookSearchService) {
		this.bookSearchService = bookSearchService;
	}

	@GetMapping("/search/books")
	@ApiOperation(value = "searchBooksByAttribute", response = Iterable.class, notes = "Get list of books by any of the attributes")
	public ResponseEntity<Set<Book>> searchBooksByAttribute(@RequestParam(required = false) String title,
			@RequestParam(required = false) String tag, @RequestParam(required = false) String isbn,
			@RequestParam(required = false) Long author_id) throws AuthorNotFoundException {
		Set<Book> bookCollection = new HashSet<Book>();

		if (author_id != null && author_id > 0) {
			try {
				bookCollection.addAll(bookSearchService.searchBooksByAuthorId(author_id));
			} catch (NoResultsFoundException ex) {
				LOGGER.error("No books found with the given author id "+author_id);
			} catch (AuthorNotFoundException ex) {
				LOGGER.error("No author found with the given author id "+author_id);
			}
		}

		if (title != null && !title.isBlank() && !title.isEmpty()) {
			try {
				bookCollection.addAll(bookSearchService.searchBooksByTitle(title));
			} catch (NoResultsFoundException ex) {
				LOGGER.error("No books found with the given title "+title);
			}
		}

		if (tag != null && !tag.isBlank() && !tag.isEmpty()) {
			try {
				bookCollection.addAll(bookSearchService.searchBooksByTag(tag));
			} catch (NoResultsFoundException ex) {
				LOGGER.error("No books found with the given tag "+tag);
			}
		}

		if (bookCollection.size() > 0) {
			new ResponseEntity<BookRequestDto>(HttpStatus.OK);
			return ResponseEntity.ok(bookCollection);
		} else {
			throw new NoResultsFoundException("No books found matching the given attribute");
		}
	}

	@GetMapping("/title/{title}/books")
	@ApiOperation(value = "searchBooksByTitle", response = Iterable.class, tags = "Get list of books by title")
	public ResponseEntity<Set<Book>> searchBooksByTitle(@PathVariable String title) {
		Set<Book> bookList = bookSearchService.searchBooksByTitle(title);
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping("/author/{author_id}/books")
	@ApiOperation(value = "searchBooksByAuthorId", response = Iterable.class, notes = "Get list of books written by an author")
	public ResponseEntity<Set<Book>> searchBooksByAuthorId(@PathVariable Long author_id) {
		Set<Book> bookList = bookSearchService.searchBooksByAuthorId(author_id);
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given author id");
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping("/author/books")
	@ApiOperation(value = "searchBooksByAuthorName", response = Iterable.class, notes = "Get list of books written by an author")
	public ResponseEntity<Set<Book>> searchBooksByAuthorName(@RequestParam String author_name) {
		Set<Book> bookList = bookSearchService.searchBooksByAuthorName(author_name);
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given author name");
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping("/tag/{name}/books")
	@ApiOperation(value = "searchBooksByTag", response = Iterable.class, notes = "Get list of books based on tag name")
	public ResponseEntity<Set<Book>> searchBooksByTag(@PathVariable String name) {
		Set<Book> bookList = bookSearchService.searchBooksByTag(name);
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with given tag name");
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}
}
