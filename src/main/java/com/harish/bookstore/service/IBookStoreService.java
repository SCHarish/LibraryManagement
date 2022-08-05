package com.harish.bookstore.service;

import java.util.Optional;

import com.harish.bookstore.model.Book;

public interface IBookStoreService {
	public Optional<Book> findByISBN(String isbn);
}
