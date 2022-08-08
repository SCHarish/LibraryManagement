package com.harish.library.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.exceptions.AuthorNotFoundException;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.InvalidDataException;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.model.Tag;
import com.harish.library.service.IAuthorStoreService;
import com.harish.library.service.IBookSearchService;
import com.harish.library.service.IBookStoreService;
import com.harish.library.service.ITagStoreService;

@Service
public class BookSearchServiceImpl implements IBookSearchService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookSearchServiceImpl.class);
	final IBookStoreService bookStoreService;

	final IAuthorStoreService authorStoreService;

	final ITagStoreService tagStoreService;

	@Autowired
	public BookSearchServiceImpl(IBookStoreService bookStoreService, IAuthorStoreService authorStoreService,
			ITagStoreService tagStoreService) {
		this.bookStoreService = bookStoreService;
		this.authorStoreService = authorStoreService;
		this.tagStoreService = tagStoreService;
	}

	@Override
	public Set<Book> searchBooksByTitle(String title) {
		Set<Book> bookList = bookStoreService.findByTitle(title);
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given title : " + title);
		}
		return bookList;
	}

	@Override
	public Book searchBookByISBN(String isbn) {
		Optional<Book> book = bookStoreService.findBookByISBN(isbn);
		if (book.isPresent()) {
			return book.get();
		} else {
			throw new BookNotFoundException("No book found with the given ISBN no. : " + isbn);
		}
	}

	@Override
	public Set<Book> searchBooksByTag(String name) {
		Set<Tag> tagList = tagStoreService.getTagsByName(name);
		Set<Book> bookList = new HashSet<Book>();
		tagList.forEach(tag -> bookList.addAll(tag.getBooks()));
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given tag : " + name);
		}
		return bookList;
	}

	@Override
	public Set<Book> searchBooksByAuthorId(Long author_id) {
		Optional<Author> author = authorStoreService.getAuthor(author_id);
		if (author.isPresent()) {
			Set<Book> bookList = author.get().getBooks();
			if (bookList.size() == 0) {
				throw new NoResultsFoundException("No books found for the given author id - " + author_id);
			}
			return bookList;
		} else {
			throw new AuthorNotFoundException("No author found with the given author id : " + author_id);
		}
	}

	@Override
	public Set<Book> searchBooksByAuthorName(String author_name) {
		List<Author> authorList = authorStoreService.getAuthorsByName(author_name);
		Set<Book> bookList = new HashSet<Book>();
		authorList.forEach(author -> bookList.addAll(author.getBooks()));
		if (bookList.size() == 0) {
			throw new NoResultsFoundException("No books found with the given author name : " + author_name);
		}
		return bookList;
	}
}
