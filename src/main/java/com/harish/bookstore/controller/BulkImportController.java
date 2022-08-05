package com.harish.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "Bulk import Controller", description = "Used to bulk import book entity")
public class BulkImportController {
	
}
