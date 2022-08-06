package com.harish.library.controller;

import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Bulk import Controller", description = "Used to bulk import book entity")
public class BulkImportController {
	@PostMapping(value = "/upload", consumes = "text/csv")
    public ResponseEntity uploadCSV(@RequestBody InputStream body) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body("New books imported successfully");
    }
}