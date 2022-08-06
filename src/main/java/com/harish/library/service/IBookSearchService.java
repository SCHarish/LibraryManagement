package com.harish.library.service;

import java.util.List;
import java.util.Optional;

import com.harish.library.model.Book;

public interface IBookSearchService {
	Optional<List<Book>> searchBookByTitle(String title);
	
	Optional<List<Book>> searchBookByTag(String tag);
	
	Optional<List<Book>> searchBookByAuthor(String author);
}
