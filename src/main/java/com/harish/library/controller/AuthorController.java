package com.harish.library.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harish.library.dto.AuthorRequestDto;
import com.harish.library.dto.RequestDto;
import com.harish.library.dto.ResponseDto;
import com.harish.library.exceptions.AuthorNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookStoreService;
import com.harish.library.util.BookStoreUtil;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Author Store Controller", description = "CRUD endpoints for author entity")
public class AuthorController {
	private final IAuthorStoreService authorStoreService;
	
	@Autowired
	AuthorController(IAuthorStoreService authorStoreService){
		this.authorStoreService = authorStoreService;
	}
	
	@PostMapping(value = "/Authors", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ResponseDto> addAuthor(@RequestBody AuthorRequestDto RequestDto) {
	//	TODO :: validate Dto
		Author author = authorStoreService.addAuthor(RequestDto);
		var responseDto = new ResponseDto.ResponseDtoBuilder("Author created successfully").payload(author).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}
	
	@GetMapping(value = "/Authors")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> getAllAuthors() {	
		return null;
	}
	
	@GetMapping(value = "/Authors/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Optional<Author>> getAuthor(@PathVariable Long id) throws AuthorNotFoundException{	
		Optional<Author> author = authorStoreService.getAuthor(id);
		if(author.isPresent()) {
			new ResponseEntity<RequestDto>(HttpStatus.OK);
			return ResponseEntity.ok(author);
		} else {
			throw new AuthorNotFoundException("No author found with the given author id : "+ id);
		}
	}
}
