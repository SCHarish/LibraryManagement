package com.harish.library.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.dto.BookDto;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.ISBNValueIsNullException;
import com.harish.library.model.Book;
import com.harish.library.repository.BookStoreRepository;
import com.harish.library.service.IBookStoreService;

@Service
public class BookStoreServiceImpl implements IBookStoreService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	private final BookStoreRepository bookStoreRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public BookStoreServiceImpl(BookStoreRepository bookRepository, ModelMapper modelMapper){
		this.bookStoreRepository = bookRepository;
		this.modelMapper = modelMapper;
	}	
	
	@Override
	public Optional<BookDto> findByISBN(String isbn) throws ISBNValueIsNullException{
		try {
			Optional<Book> book = bookStoreRepository.findById(isbn);
			return Optional.of(modelMapper.map(book, BookDto.class)); 
		} catch(IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}
	
	@Override
	public void addBook(BookDto bookDto) throws DuplicateBookFoundException{
		//Check if book is already present
        Optional<Book> bookById = bookStoreRepository.findById(bookDto.getIsbn());
        
        bookById.ifPresent(book -> {
            throw new DuplicateBookFoundException("Book with same isbn is alread present");
        });
        
        if (!bookById.isPresent()) {
            LOGGER.info("No duplicate book found");
            Book book = modelMapper.map(bookDto, Book.class);
            bookStoreRepository.save(book);
        }
	}
	
	@Override
	public void updateBook(BookDto book) {
		
	}
	
	@Override
	public void deleteBook(String isbn) {
		
	}	
}
