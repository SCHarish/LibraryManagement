package com.harish.library.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.harish.library.dto.BookRequestDto;
import com.harish.library.model.Book;

public interface IBookStoreService {
	
	/**
	 * 
	 * @return
	 */
	public Optional<Set<Book>> getAllBooks();
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public Optional<Book> findBookByISBN(String isbn);
	
	/**
	 * 
	 * @param bookDto
	 */
	public Book addBook(BookRequestDto book);
	
	/**
	 * 
	 * @param bookDto
	 */
	public Book updateBook(BookRequestDto bookDto);
	
	/**
	 * 
	 * @param isbn
	 */
	public void deleteBook(String isbn);
	
	/**
	 * 
	 * @param requestDtoList
	 */
	public void addBooks(List<BookRequestDto> requestDtoList);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	public Set<Book> findByTitle(String title);
}
