package com.harish.library.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.dto.RequestDto;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.ISBNValueIsNullException;
import com.harish.library.model.Book;
//import com.harish.library.model.BookTags;
import com.harish.library.repository.BookStoreRepository;
//import com.harish.library.repository.BookTagRepository;
import com.harish.library.repository.TagRepository;
import com.harish.library.service.IBookStoreService;
import com.harish.library.util.BookStoreUtil;
import com.harish.library.model.Tag;

@Service
public class BookStoreServiceImpl implements IBookStoreService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	private final BookStoreRepository bookStoreRepository;
	private final ModelMapper modelMapper;
	private final TagRepository tagRepository;
	//private final BookTagRepository bookTagRepository;

	@Autowired
	public BookStoreServiceImpl(BookStoreRepository bookRepository, TagRepository tagRepository, ModelMapper modelMapper){
		this.bookStoreRepository = bookRepository;
		this.modelMapper = modelMapper;
		this.tagRepository = tagRepository;
		//this.bookTagRepository = bookTagRepository;
	}	
	
	@Override
	public Optional<Book> findByISBN(String isbn) throws ISBNValueIsNullException{
		try {
			Optional<Book> book = bookStoreRepository.findById(isbn);
			return book;
		} catch(IllegalArgumentException ex) {
			throw new ISBNValueIsNullException("ISBN value is null");
		}
	}
	
	@Override
	public void addBook(RequestDto bookDto) throws DuplicateBookFoundException{
		//Check if book is already present
        Optional<Book> bookInfo = bookStoreRepository.findById(bookDto.getIsbn());
        
        bookInfo.ifPresent(book -> {
            throw new DuplicateBookFoundException("Book with same isbn is already present");
        });
        LOGGER.info("No duplicate book found");
       
        List<String> tags = Arrays.asList(bookDto.getTags());
        List<Tag> newtagList = new ArrayList();
        //List<BookTags> bookTags = new ArrayList();
        for(String tag : tags) {
        	//Tag tagInfo =  tagRepository.findTagByName(tag);
        	//if(tagInfo == null) {
        	   var newTag = new Tag();
        	   newTag.setName(tag);
        	   newtagList.add(newTag);
//        	} else {   	
//        		var bookTag = new BookTags();
//        		bookTags.add(bookTag);
//        	}
        }
        
       // bookTagRepository.saveAll(bookTags);
        
       //converting dto to domain object
        Book newBook = BookStoreUtil.constructBook(bookDto, newtagList);  
        
       // Book book = modelMapper.map(newBook, Book.class);
        bookStoreRepository.save(newBook);    
	}
	
	@Override
	public void updateBook(RequestDto bookDto) {
		//converting dto to domain object
		List<String> updatedTagList = Arrays.asList(bookDto.getTags());
		
		List<Tag> newtagList = new ArrayList();
        //List<BookTags> bookTags = new ArrayList();
        for(String tag : updatedTagList) {
        	//Tag tagInfo =  tagRepository.findTagByName(tag);
        	//if(tagInfo == null) {
        	   var newTag = new Tag();
        	   newTag.setName(tag);
        	   newtagList.add(newTag);
//	        	} else {   	
//	        		var bookTag = new BookTags();
//	        		bookTags.add(bookTag);
//	        	}
        }
	        
        Book updateBook = BookStoreUtil.constructBook(bookDto, newtagList); 
        bookStoreRepository.save(updateBook);
	}
	
	@Override
	public void deleteBook(String isbn) {
		bookStoreRepository.deleteById(isbn);
	}
}
