package com.harish.library.service;

import java.util.Optional;

import com.harish.library.dto.BookDto;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.model.Book;

public interface IBookStoreService {
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public Optional<BookDto> findByISBN(String isbn);
	
	/**
	 * 
	 * @param book
	 */
	public void addBook(BookDto book);
	
	/**
	 * 
	 * @param book
	 */
	public void updateBook(BookDto book);
	
	/**
	 * 
	 * @param isbn
	 */
	public void deleteBook(String isbn);
}
