package com.harish.library.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.harish.library.dto.RequestDto;
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
	public Optional<Book> findByISBN(String isbn);
	
	/**
	 * 
	 * @param bookDto
	 */
	public void addBook(RequestDto book);
	
	/**
	 * 
	 * @param bookDto
	 */
	public void updateBook(RequestDto bookDto);
	
	/**
	 * 
	 * @param isbn
	 */
	public void deleteBook(String isbn);
	
	/**
	 * 
	 * @param requestDtoList
	 */
	public void addBooks(List<RequestDto> requestDtoList);

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public Set<Book> searchBooks(String keyword);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	public Set<Book> findByTitle(String title);
}
