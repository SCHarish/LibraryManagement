package com.harish.library.service;

import java.util.Optional;

import com.harish.library.dto.RequestDto;

public interface IBookStoreService {
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public Optional<RequestDto> findByISBN(String isbn);
	
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
}
