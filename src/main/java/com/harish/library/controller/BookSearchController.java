package com.harish.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harish.library.model.Book;
import com.harish.library.service.IBookStoreService;

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
	
	@RequestMapping("/searchBook/title/{title}")
	public ResponseEntity<List<Book>> searchBookByTitle(@PathVariable String title) {
		
				
		//check if the book already exists?
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping("/searchBook/author/{author}")
	public ResponseEntity<List<Book>> searchBookByAuthor(@PathVariable String author) {
		
				
		//check if the book already exists?
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping("/searchBook/tag/{tag}")
	public ResponseEntity<List<Book>> searchBookByTag(@PathVariable String tag) {
					
		//check if the book already exists?
		return ResponseEntity.ok(null);
	}
}
