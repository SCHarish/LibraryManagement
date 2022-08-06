package com.harish.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.harish.library.exceptions.ISBNValueIsNullException;
import com.harish.library.model.Book;
import com.harish.library.repository.BookStoreRepository;
import com.harish.library.service.BulkImportService;
import com.harish.library.service.IBookSearchService;

public class BookSearchServiceImpl implements IBookSearchService{
	private static final Logger LOGGER = LoggerFactory.getLogger(BookSearchServiceImpl.class);
	final BookStoreRepository bookStoreRepository;

	@Autowired
	public BookSearchServiceImpl(BookStoreRepository bookRepository) {
		this.bookStoreRepository = bookRepository;
	}
	
	public Optional<List<Book>> searchBookByTitle(String title) {
		try {
			return null;
			//return bookStoreRepository.findById(title);
		} catch(IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}
	
	public Optional<List<Book>> searchBookByTag(String tag) {
		try {
			return null;
			//return bookStoreRepository.findById(title);
		} catch(IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}
	
	public Optional<List<Book>> searchBookByAuthor(String author) {
		try {
			return null;
			//return bookStoreRepository.findById(title);
		} catch(IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}
	
	
	
}
