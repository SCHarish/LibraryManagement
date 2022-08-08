package com.harish.library.service;

import java.util.Set;

import com.harish.library.model.Book;

/**
 * 
 * @author harishsc
 *
 */
public interface IBookSearchService {
	/**
	 * Searches books with the provided title
	 * @param title
	 * @return
	 */
	Set<Book> searchBooksByTitle(String title);
	
	/**
	 * Searches books with the provied tag name
	 * @param tag
	 * @return
	 */
	Set<Book> searchBooksByTag(String tag);
	
	/**
	 * Searches books with the given author id
	 * @param id
	 * @return
	 */
	Set<Book> searchBooksByAuthorId(Long id);
	
	/**
	 * Searches books with the given author name
	 * @param author_name
	 * @return
	 */
	Set<Book> searchBooksByAuthorName(String author_name);
	
	/**
	 * Searches books with the given ISBN No.
	 * @param isbn
	 * @return
	 */
	Book searchBookByISBN(String isbn);
}
