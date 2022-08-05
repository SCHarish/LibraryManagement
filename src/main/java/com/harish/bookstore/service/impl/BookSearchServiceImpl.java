package com.harish.bookstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.harish.bookstore.exceptions.ISBNValueIsNullException;
import com.harish.bookstore.model.Book;
import com.harish.bookstore.repository.BookStoreRepository;
import com.harish.bookstore.service.IBookSearchService;

public class BookSearchServiceImpl implements IBookSearchService{
	final BookStoreRepository bookStoreRepository;

	@Autowired
	public BookSearchServiceImpl(BookStoreRepository bookRepository) {
		this.bookStoreRepository = bookRepository;
	}
	
	public Optional<List<Book>> searchBookByTitle(String title) {
		try {
			return bookStoreRepository.findById(title);
		} catch(IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}
	
	
	
}
