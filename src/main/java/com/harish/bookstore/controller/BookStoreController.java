package com.harish.bookstore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harish.bookstore.exceptions.InvalidISBNException;
import com.harish.bookstore.model.Book;
import com.harish.bookstore.service.IBookStoreService;
import com.harish.bookstore.util.BookStoreUtil;

import io.swagger.annotations.Api;

/**
 * Controller for the CRUD operations on Book entity
 *
 */
@RestController
@RequestMapping("/api")
@Api(value = "Book Store Controller", description = "CRUD endpoints for book entity")
public class BookStoreController {
	private final IBookStoreService bookStoreService;

    @Autowired
    public BookStoreController(IBookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }
	
	@RequestMapping("/addBook")
	public String addBook(Book book) {
		
		//check if the book already exists?
		return "New book added to the store";
	}
	
	@RequestMapping("/getBook/{isbn}")
	public ResponseEntity<String> getBookByISBN(@PathVariable String isbn) throws InvalidISBNException{
		
		if(isbn == null || isbn.isEmpty()) {
			//set status code as 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
		}
		
		//Validate ISBN
		boolean isValidISBN = BookStoreUtil.isValidISBN(isbn);		
		if(!isValidISBN) {
			//set status code as 422
			//throw new InvalidISBNException("Please provide valid ISBN", 422);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Book not found");		
		}
				
		Optional<Book> book = bookStoreService.findByISBN(isbn);
		
	
		if(book.isEmpty()) {
			//set status code as 404
			//throw book not found exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Book found");
	}
	
	@RequestMapping("/updateBook")
	public String updateBook(Book book) {
		
				
		//check if the book already exists?
		return "New book added to the store";
	}
}
