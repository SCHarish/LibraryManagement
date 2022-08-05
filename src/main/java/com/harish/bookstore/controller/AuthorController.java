package com.harish.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "Author Store Controller", description = "CRUD endpoints for author entity")
public class AuthorController {

}
