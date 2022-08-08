package com.harish.library.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Search Controller")
public class BookSearchController {
	private final IBookSearchService bookSearchService;

	@Autowired
	public BookSearchController(IBookSearchService bookSearchService) {
		this.bookSearchService = bookSearchService;
	}

	@GetMapping("/search/books")
	@ApiOperation(value = "searchBooksByAttribute", notes = "get list of books by any of the attribute")
	public ResponseEntity<Set<Book>> searchBooksByAttribute(@RequestParam(required = false) String title,
			@RequestParam(required = false) String tag, @RequestParam(required = false) String isbn,
			@RequestParam(required = false) Long author_id) throws AuthorNotFoundException {
		Set<Book> bookCollection = new HashSet<Book>();

		if (author_id != null && author_id > 0) {
			bookCollection.addAll(bookSearchService.searchBooksByAuthorId(author_id));
		}

		if (title != null && !title.isBlank() && !title.isEmpty()) {
			bookCollection.addAll(bookSearchService.searchBooksByTitle(title));
		}

		if (tag != null && !tag.isBlank() && !tag.isEmpty()) {
			bookCollection.addAll(bookSearchService.searchBooksByTag(tag));
		}

		if (bookCollection.size() > 0) {
			new ResponseEntity<BookRequestDto>(HttpStatus.OK);
			return ResponseEntity.ok(bookCollection);
		} else {
			throw new NoResultsFoundException("No books found matching the given attribute");
		}
	}

	@GetMapping("/title/{title}/books")
	public ResponseEntity<Set<Book>> searchBooksByTitle(@PathVariable String title) {
		Set<Book> bookList = bookSearchService.searchBooksByTitle(title);
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping("/author/{author_id}/books")
	@ApiOperation(value = "searchBooksByAuthorId", notes = "get list of books written by an author")
	public ResponseEntity<Set<Book>> searchBooksByAuthorId(@PathVariable Long author_id) {
		Set<Book> bookList = bookSearchService.searchBooksByAuthorId(author_id);
		if(bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given author id");
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping("/author/books")
	@ApiOperation(value = "searchBooksByAuthorName", notes = "get list of books written by an author")
	public ResponseEntity<Set<Book>> searchBooksByAuthorName(@RequestParam String author_name) {
		Set<Book> bookList = bookSearchService.searchBooksByAuthorName(author_name);
		if(bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given author name");
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping("/tag/{name}/books")
	@ApiOperation(value = "searchBookByTag", notes = "get list of books based on tag")
	public ResponseEntity<Set<Book>> searchBooksByTag(@PathVariable String name) {
		Set<Book> bookList = bookSearchService.searchBooksByTag(name);
		if(bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with given tag name");
		}
		new ResponseEntity<BookRequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}
}
