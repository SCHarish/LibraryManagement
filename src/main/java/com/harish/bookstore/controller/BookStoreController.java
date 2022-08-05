package com.harish.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * Controller for the CRUD operations on Book entity
 *
 */

@RestController
@RequestMapping("/api")
@Api(value = "Bookstore Controller", description = "Bookstore CRUD Endpoints.")
public class BookStoreController {
	

}
