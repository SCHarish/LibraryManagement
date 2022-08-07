package com.harish.library.controller;

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

import com.harish.library.dto.RequestDto;
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
    
    @GetMapping("searchBooks")
    @ApiOperation(value = "searchBookByKeyword", notes="get list of books by keyword")
    public ResponseEntity<List<Book>> searchBooksByKeyword(@PathVariable String title) {
    	return ResponseEntity.ok(null);
    }
	
    @GetMapping("/searchBooks/title/{title}")
	public ResponseEntity<List<Book>> searchBooksByTitle(@PathVariable String title) {
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/searchBooks/author")
	@ApiOperation(value = "searchBookByAuthor", notes="get list of books written by an author")
	public ResponseEntity<Set<Book>> searchBooksByAuthorId(@RequestParam Long id) {
		Set<Book> bookList = bookSearchService.searchBooksByAuthorId(id);
		new ResponseEntity<RequestDto>(HttpStatus.OK);
		return ResponseEntity.ok(bookList);
	}
	
	@GetMapping("/searchBooks/author/{author}")
	@ApiOperation(value = "searchBookByAuthor", notes="get list of books written by an author")
	public ResponseEntity<List<Book>> searchBooksByAuthorName(@PathVariable String author) {			
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/searchBooks/tag/{tag}")
	@ApiOperation(value = "searchBookByTag", notes="get list of books based on tag")
	public ResponseEntity<List<Book>> searchBooksByTag(@PathVariable String tag) {	
		return ResponseEntity.ok(null);
	}
}
