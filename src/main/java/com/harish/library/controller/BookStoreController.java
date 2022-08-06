package com.harish.library.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harish.library.dto.BookDto;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.InvalidISBNException;
import com.harish.library.model.Book;
import com.harish.library.service.IBookStoreService;
import com.harish.library.util.BookStoreUtil;

import io.swagger.annotations.Api;

/**
 * Controller for the CRUD operations on Book entity
 *
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "Book Store Controller", description = "CRUD endpoints for book entity")
public class BookStoreController {
	private final IBookStoreService bookStoreService;

    @Autowired
    public BookStoreController(IBookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }
	
	@PostMapping(value = "/Books", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> addBook(@RequestBody BookDto bookDto) throws DuplicateBookFoundException{
		//sanitize Dto
		BookStoreUtil.sanitizeDto(bookDto);
		
		bookStoreService.addBook(bookDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("");
	}
	
	@GetMapping("/Books/{isbn}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Optional<BookDto>> getBook(@PathVariable String isbn) throws InvalidISBNException, BookNotFoundException{
		
		if(isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException("Invalid request");
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
		}
		
		//Validate ISBN
		boolean isValidISBN = BookStoreUtil.isValidISBN(isbn);		
		if(!isValidISBN) {
			throw new InvalidISBNException("Please provide valid ISBN");	
		}
				
		Optional<BookDto> book = bookStoreService.findByISBN(isbn);
		
	
		if(book.isEmpty()) {
			throw new BookNotFoundException("Unable to find the book");
		}
		
		new ResponseEntity<BookDto>(HttpStatus.OK);
		return ResponseEntity.ok(book);
	}
	
	@PutMapping("/Books/")
	public ResponseEntity<Object> updateBook(@RequestBody Book book) {
		
				
		//check if the book already exists?
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
	}
	
	@DeleteMapping("/Books/")
	public ResponseEntity<Object> deleteBook(@PathVariable String isbn) throws InvalidISBNException {
		if(isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException("Invalid request");
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
		}
		
		//Validate ISBN
		boolean isValidISBN = BookStoreUtil.isValidISBN(isbn);		
		if(!isValidISBN) {
			throw new InvalidISBNException("Please provide valid ISBN");	
		}
				
		//check if the book already exists?
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
