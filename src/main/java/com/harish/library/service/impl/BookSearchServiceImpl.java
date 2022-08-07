package com.harish.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.exceptions.ISBNValueIsNullException;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.repository.BookStoreRepository;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookSearchService;
import com.harish.library.service.IBookStoreService;

@Service
public class BookSearchServiceImpl implements IBookSearchService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookSearchServiceImpl.class);
	final IBookStoreService bookStoreService;
	
	final IAuthorStoreService authorStoreService;

	@Autowired
	public BookSearchServiceImpl(IBookStoreService bookStoreService, IAuthorStoreService authorStoreService) {
		this.bookStoreService = bookStoreService;
		this.authorStoreService = authorStoreService;
	}

	public Optional<List<Book>> searchBookByTitle(String title) {
		try {
			return null;
			// return bookStoreRepository.findById(title);
		} catch (IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}

	public Optional<List<Book>> searchBookByTag(String tag) {
		try {
			return null;
			// return bookStoreRepository.findById(title);
		} catch (IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}

	public Optional<List<Book>> searchBookByAuthor(String author) {
		try {
			return null;
			// return bookStoreRepository.findById(title);
		} catch (IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}

	@Override
	public Set<Book> searchBooks(String keyword) {
		if (keyword != null && !keyword.isEmpty() && !keyword.isBlank()) {
			//return bookStoreService.search(keyword);
			return null;
		} else {
			throw new NoResultsFoundException("Invalid keyword");
		}
	}
	
	@Override
	public Set<Book> searchBooksByAuthorId(Long id){
		Optional<Author> author = authorStoreService.getAuthor(id);
		if(author.isPresent()) {
			Set<Book> bookList = author.get().getBooks();
			return bookList;
		} else {
			
		}
		return null;
	}

}
