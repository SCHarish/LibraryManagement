package com.harish.library.controller;

import java.util.Optional;
import java.util.Set;

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

import com.harish.library.dto.BookRequestDto;
import com.harish.library.dto.ResponseDto;
import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.InvalidDataException;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Book;
import com.harish.library.service.IBookStoreService;
import com.harish.library.util.BookStoreUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author harishsc
 *
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "Book store API", description = "CRUD API for Book")
public class BookStoreController {
	private final IBookStoreService bookStoreService;

	@Autowired
	public BookStoreController(IBookStoreService bookStoreService) {
		this.bookStoreService = bookStoreService;
	}

	@PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "addBook", nickname = "addBook")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find the Author"),
			@ApiResponse(code = 409, message = "Duplicate book found"),
			@ApiResponse(code = 200, message = "Book added successfully", response = ResponseDto.class) })
	public ResponseEntity<ResponseDto> addBook(
			@ApiParam(name = "RequestDto", value = "Book request DTO", required = true) @RequestBody BookRequestDto RequestDto)
			throws DuplicateBookFoundException, InvalidDataException {
		BookStoreUtil.validateBookRequestDto(RequestDto);
		Book newBook = bookStoreService.addBook(RequestDto);
		var responseDto = new ResponseDto.ResponseDtoBuilder("Book added successfully").payload(newBook).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@GetMapping("/books/{isbn}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "getBook", nickname = "getBook")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find the book"),
			@ApiResponse(code = 200, message = "OK", response = Book.class) })
	public ResponseEntity<Optional<Book>> getBook(
			@ApiParam(value = "isbn", required = true, defaultValue = "") @PathVariable String isbn)
			throws InvalidDataException, BookNotFoundException {
		// Validate ISBN
		BookStoreUtil.isValidISBN(isbn);
		Optional<Book> book = bookStoreService.findBookByISBN(isbn);

		return ResponseEntity.status(HttpStatus.OK).body(book);
	}

	@GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "getAllBooks", nickname = "getAllBooks")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find any books in the library"),
			@ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "Set") })
	public ResponseEntity<Set<Book>> getAllBooks() {
		Set<Book> bookList = bookStoreService.getAllBooks();
		return ResponseEntity.status(HttpStatus.OK).body(bookList);
	}

	@PutMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "updateBook", nickname = "updateBook")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find the book"),
			@ApiResponse(code = 200, message = "Book updated successfully", response = ResponseDto.class) })
	public ResponseEntity<ResponseDto> updateBook(
			@ApiParam(name = "RequestDto", value = "Book request DTO", required = true) @RequestBody BookRequestDto RequestDto)
			throws InvalidDataException {
		BookStoreUtil.validateBookRequestDto(RequestDto);
		Book updatedBook = bookStoreService.updateBook(RequestDto);
		var responseDto = new ResponseDto.ResponseDtoBuilder("Book information updated successfully")
				.payload(updatedBook).build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@DeleteMapping(value = "/books/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "deleteBook", nickname = "deleteBook")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Unable to find the book"),
			@ApiResponse(code = 200, message = "Book deleted successfully", response = ResponseDto.class) })
	public ResponseEntity<ResponseDto> deleteBook(
			@ApiParam(name = "isbn", value = "Book ISBN No.", required = true) @PathVariable String isbn)
			throws InvalidDataException, BookNotFoundException {
		BookStoreUtil.isValidISBN(isbn);
		bookStoreService.deleteBook(isbn);
		var responseDto = new ResponseDto.ResponseDtoBuilder("Book information deleted successfully").build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);

	}
}
