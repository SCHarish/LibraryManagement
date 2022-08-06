package com.harish.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harish.library.model.Book;
import com.harish.library.service.IBookStoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Search Controller")
public class BookSearchController {
	private final IBookStoreService bookStoreService;

    @Autowired
    public BookSearchController(IBookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }
    
    @GetMapping("searchBook")
    @ApiOperation(value = "searchBookByKeyword", notes="get list of books by keyword")
    public ResponseEntity<List<Book>> searchBookByKeyword(@PathVariable String title) {
    	return ResponseEntity.ok(null);
    }
	
    @GetMapping("/searchBook/title/{title}")
	public ResponseEntity<List<Book>> searchBookByTitle(@PathVariable String title) {
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/searchBook/author/{author}")
	@ApiOperation(value = "searchBookByAuthor", notes="get list of books written by an author")
	public ResponseEntity<List<Book>> searchBookByAuthor(@PathVariable String author) {			
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/searchBook/tag/{tag}")
	@ApiOperation(value = "searchBookByTag", notes="get list of books based on tag")
	public ResponseEntity<List<Book>> searchBookByTag(@PathVariable String tag) {
				
		return ResponseEntity.ok(null);
	}
}
