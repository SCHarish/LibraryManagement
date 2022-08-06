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

import com.harish.library.dto.RequestDto;
import com.harish.library.dto.RequestDto;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.ISBNValueIsNullException;
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
	public ResponseEntity<Object> addBook(@RequestBody RequestDto RequestDto) throws DuplicateBookFoundException{	
		BookStoreUtil.validateDto(RequestDto);
		bookStoreService.addBook(RequestDto);	
		return ResponseEntity.status(HttpStatus.CREATED).body("");
	}
	
	@GetMapping("/Books/{isbn}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Optional<Book>> getBook(@PathVariable String isbn) throws InvalidISBNException, BookNotFoundException{
		
		if(isbn == null || isbn.isEmpty()) {
			throw new ISBNValueIsNullException("ISBN value cannot be null");
		}
		
		//Validate ISBN
		boolean isValidISBN = BookStoreUtil.isValidISBN(isbn);		
		if(!isValidISBN) {
			throw new InvalidISBNException("Please provide valid ISBN");	
		}
				
		Optional<Book> book = bookStoreService.findByISBN(isbn);
		
	
		if(book.isEmpty()) {
			throw new BookNotFoundException("Unable to find the book");
		}
		
		new ResponseEntity<RequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(book);
	}
	
	@PutMapping(value = "/Books", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBook(@RequestBody RequestDto RequestDto) {
		BookStoreUtil.validateDto(RequestDto);
		bookStoreService.updateBook(RequestDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
	}
	
	@DeleteMapping("/Books/{isbn}")
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
				
		bookStoreService.deleteBook(isbn);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
