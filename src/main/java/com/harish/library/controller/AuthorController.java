package com.harish.library.controller;

import java.util.List;
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
import com.harish.library.dto.BookRequestDto;
import com.harish.library.dto.ResponseDto;
import com.harish.library.exceptions.AuthorNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookStoreService;
import com.harish.library.util.BookStoreUtil;

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
@Api(value = "CRUD API for Author", tags = "Author", description = "Manage Authors")
public class AuthorController {
	private final IAuthorStoreService authorStoreService;

	@Autowired
	AuthorController(IAuthorStoreService authorStoreService) {
		this.authorStoreService = authorStoreService;
	}

	@PostMapping(value = "/authors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "addAuthor", nickname = "addAuthor")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "OK", response = ResponseDto.class) })
	public ResponseEntity<ResponseDto> addAuthor(
			@ApiParam(value = "Author DTO", required = true) @RequestBody AuthorRequestDto requestDto) {
		// Validate author request DTO
		BookStoreUtil.validateAuthorRequestDto(requestDto);
		Author author = authorStoreService.addAuthor(requestDto);
		var responseDto = new ResponseDto.ResponseDtoBuilder("Author created successfully").payload(author).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@GetMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "getAllAuthors", nickname = "getAllAuthors")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "OK", response = ResponseDto.class) })
	public ResponseEntity<ResponseDto> getAllAuthors() throws NoResultsFoundException {
		List<Author> authorList = authorStoreService.getAllAuthors();
		var responseDto = new ResponseDto.ResponseDtoBuilder("Author created successfully").payload(authorList).build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping(value = "/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "getAuthor", nickname = "getAuthor")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find the author"),
			@ApiResponse(code = 200, message = "OK", response = Author.class) })
	public ResponseEntity<Optional<Author>> getAuthor(
			@ApiParam(name = "id", value = "Author ID", required = true) @PathVariable Long id)
			throws AuthorNotFoundException {
		Optional<Author> author = authorStoreService.getAuthor(id);
		if (author.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(author);
		} else {
			throw new AuthorNotFoundException("No author found with the given author id : " + id);
		}
	}
}
