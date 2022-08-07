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

	public Set<Book> searchBooksByTitle(String title) {
		try {
			return bookStoreService.findByTitle(title);
		} catch (IllegalArgumentException ex) {
			throw new InvalidDataException("ISBN value cannot be null");
		}
	}

	public Set<Book> searchBooksByTag(String name) {
		Set<Tag> tagList = tagStoreService.getTagsByName(name);
		List<String> list = tagStoreService.findISBNByTagName(name);
		Set<Book> bookList = new HashSet<Book>();
		tagList.forEach(tag -> bookList.addAll(tag.getBooks()));
		return bookList;
	}

	@Override
	public Set<Book> searchBooksByAuthorId(Long id) {
		Optional<Author> author = authorStoreService.getAuthor(id);
		if (author.isPresent()) {
			Set<Book> bookList = author.get().getBooks();
			return bookList;
		} else {
			throw new AuthorNotFoundException("Author not found with the author id : " + id);
		}
	}

	@Override
	public Set<Book> searchBooksByAuthorName(String author_name) {
		List<Author> authorList = authorStoreService.getAuthorsByName(author_name);
		Set<Book> bookList = new HashSet<Book>();
		authorList.forEach(author -> bookList.addAll(author.getBooks()));
		return bookList;
	}
}
