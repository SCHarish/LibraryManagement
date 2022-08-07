package com.harish.library.service;

import java.util.List;
import java.util.Optional;

import com.harish.library.dto.RequestDto;
import com.harish.library.model.Book;

public interface IBookStoreService {
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
}
