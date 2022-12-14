package com.harish.library.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author harishsc
 *
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "Book search API", tags = "Search", description = "Search Books")
public class BookSearchController {
	private final IBookSearchService bookSearchService;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookSearchController.class);

	@Autowired
	public BookSearchController(IBookSearchService bookSearchService) {
		this.bookSearchService = bookSearchService;
	}

	@GetMapping(value = "/search/books", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "searchBooksByAttribute", response = Iterable.class, notes = "Get list of books by any of the attributes")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find any books in the library"),
			@ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "Set") })
	public ResponseEntity<Set<Book>> searchBooksByAttribute(@ApiParam(value = "Book Title")@RequestParam(required = false) String title,
			@ApiParam(value = "Book Tag")@RequestParam(required = false) String tag, @ApiParam(value = "Book ISBN No.")@RequestParam(required = false) String isbn,
			@ApiParam(value = "Author Id")@RequestParam(required = false) Long author_id) throws AuthorNotFoundException {
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
		
		if (isbn != null && !isbn.isBlank() && !isbn.isEmpty()) {
			try {
				bookCollection.add(bookSearchService.searchBookByISBN(isbn));
			} catch (BookNotFoundException ex) {
				LOGGER.error("No books found with the given ISBN No. "+isbn);
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

	@GetMapping(value = "/title/{title}/books", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "searchBooksByTitle", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find any books in the library"),
			@ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "Set") })
	public ResponseEntity<Set<Book>> searchBooksByTitle(@ApiParam(name="title", value ="Book title", example="Harry Potter", required = true)@PathVariable String title) {
		Set<Book> bookList = bookSearchService.searchBooksByTitle(title);
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping(value = "/author/books", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "searchBooksByAuthorId", response = Iterable.class, notes = "Get list of books written by an author")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find any books in the library"),
			@ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "Set") })
	public ResponseEntity<Set<Book>> searchBooksByAuthorId(@ApiParam(name="author_id", value ="Author ID", example="123", required = true)@RequestParam Long author_id) {
		Set<Book> bookList = bookSearchService.searchBooksByAuthorId(author_id);
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given author id : "+author_id);
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping(value = "/author/{author_name}/books", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "searchBooksByAuthorName", response = Iterable.class, notes = "Get list of books written by an author")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find any books in the library"),
			@ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "Set") })
	public ResponseEntity<Set<Book>> searchBooksByAuthorName(@ApiParam(name="author_name", value="Author Name", example="Harish", required = true)@PathVariable String author_name) {
		Set<Book> bookList = bookSearchService.searchBooksByAuthorName(author_name);
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given author name : "+author_name);
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping(value = "/tag/{name}/books", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "searchBooksByTag", response = Iterable.class, notes = "Get list of books based on tag name")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find any books in the library"),
			@ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "Set") })
	public ResponseEntity<Set<Book>> searchBooksByTag(@ApiParam(name="name", value="Tag Name", example="fiction", required = true)@PathVariable String name) {
		Set<Book> bookList = bookSearchService.searchBooksByTag(name);
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with given tag name : "+name);
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}
}
