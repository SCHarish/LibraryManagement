package com.harish.library.service;

import java.util.Set;

import com.harish.library.model.Book;

public interface IBookSearchService {
	Set<Book> searchBooksByTitle(String title);
	
	Set<Book> searchBooksByTag(String tag);
	
	Set<Book> searchBooksByAuthorId(Long id);
	
	Set<Book> searchBooksByAuthorName(String author_name);
	
	Book searchBookByISBN(String isbn);
}
