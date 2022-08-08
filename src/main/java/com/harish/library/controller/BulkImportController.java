package com.harish.library.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.harish.library.dto.ResponseDto;
import com.harish.library.model.Book;
import com.harish.library.service.IBulkDataImportService;

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
@Api(value = "Bulk import API", description = "Bulk import API")
public class BulkImportController {
	private final IBulkDataImportService bulkDataImportService;

	@Autowired
	BulkImportController(IBulkDataImportService bulkDataImportService) {
		this.bulkDataImportService = bulkDataImportService;
	}

	@PostMapping(value = "/upload/books", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "importBooks", nickname = "importBooks")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 422, message = "Unable to import books from the csv file"),
			@ApiResponse(code = 201, message = "Books imported successfully from the csv file", response = ResponseDto.class) })
	public ResponseEntity<ResponseDto> uploadBooksFromCSV(
			@ApiParam(value = "CSV file to upload", required = true) @RequestParam("csvfile") MultipartFile file)
			throws IOException {
		List<Book> bookList = bulkDataImportService.importBooksFromFile(file);
		if (bookList.size() > 0) {
			var responseDto = new ResponseDto.ResponseDtoBuilder("Books imported successfully from CSV")
					.payload(bookList).build();
			return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
		} else {
			var responseDto = new ResponseDto.ResponseDtoBuilder("Book import failed").build();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseDto);
		}
	}
}