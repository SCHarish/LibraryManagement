package com.harish.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harish.bookstore.model.Book;
import com.harish.bookstore.service.IBookStoreService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "Search Controller")
public class BookSearchController {
	private final IBookStoreService bookStoreService;

    @Autowired
    public BookSearchController(IBookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }
	
	@RequestMapping("/searchBook")
	public ResponseEntity<List<Book>> searchBookByTitle(@RequestParam String title) {
		
				
		//check if the book already exists?
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping("/searchBook")
	public ResponseEntity<List<Book>> searchBookByAuthor(@RequestParam String author) {
		
				
		//check if the book already exists?
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping("/searchBook")
	public ResponseEntity<List<Book>> searchBookByTag(@RequestParam String tag) {
					
		//check if the book already exists?
		return ResponseEntity.ok(null);
	}
}
