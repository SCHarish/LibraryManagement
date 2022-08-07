package com.harish.library.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.harish.library.dto.ResponseDto;
import com.harish.library.service.IBulkDataImportService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Bulk import Controller", description = "Used to bulk import books")
public class BulkImportController {
	private final IBulkDataImportService bulkDataImportService;
	
	@Autowired
	BulkImportController(IBulkDataImportService bulkDataImportService){
		this.bulkDataImportService = bulkDataImportService;
	}
	
	@PostMapping(value = "/upload/books")
    public ResponseEntity<Object> uploadBooksFromCSV(@RequestParam("sampleCSV") MultipartFile file) throws IOException {
		bulkDataImportService.importFromFile(file);
		var responseDto = new ResponseDto.ResponseDtoBuilder("Books imported successfully from CSV").build();
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}