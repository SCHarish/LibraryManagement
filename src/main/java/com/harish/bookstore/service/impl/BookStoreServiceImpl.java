package com.harish.bookstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.bookstore.exceptions.ISBNValueIsNullException;
import com.harish.bookstore.model.Book;
import com.harish.bookstore.repository.BookStoreRepository;
import com.harish.bookstore.service.IBookStoreService;

@Service
public class BookStoreServiceImpl implements IBookStoreService{
	
	final BookStoreRepository bookStoreRepository;

	@Autowired
	public BookStoreServiceImpl(BookStoreRepository bookRepository) {
		this.bookStoreRepository = bookRepository;
	}
	
	@Override
	public Optional<Book> findByISBN(String isbn) {
		try {
			return bookStoreRepository.findById(isbn);
		} catch(IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}
}
