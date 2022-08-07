package com.harish.library.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.harish.library.dto.AuthorRequestDto;
import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.InvalidDataException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.model.Tag;

public class BookStoreUtil {
	public static boolean isValidISBN(String isbn) {
		isbn = isbn.replaceAll("-", "");
		int n = isbn.length();
		if (n != 13) {
			return false;
		}
		return true;

	}

	public static boolean validateBookRequestDto(BookRequestDto requestDto) throws InvalidDataException{
		// check if author id is null?
		String isbn = requestDto.getIsbn();
		if(isbn == null || isbn.isBlank() || isbn.isEmpty() || !isValidISBN(isbn))
			throw new InvalidDataException("Please provide valid ISBN");
		return true;
	}

	public static Book constructBook(BookRequestDto requestDto, Author author, List<Tag> tagList) {
		Set<Tag> tagSet = new HashSet<Tag>();
        for (Tag tag : tagList) {
        	tagSet.add(tag);
        }
        
		var book = new Book.BookBuilder(requestDto.getIsbn(), requestDto.getTitle()).Author(author).Tags(tagSet)
				.build();
		return book;
	}

	public static List<Tag> constructTags(List<String> tags) {
		List<Tag> newtagList = new ArrayList();
		// List<BookTags> bookTags = new ArrayList();
		for (String tag : tags) {
			// Tag tagInfo = tagRepository.findTagByName(tag);
			// if(tagInfo == null) {
			var newTag = new Tag();
			newTag.setName(tag);
			newtagList.add(newTag);
//	        	} else {   	
//	        		var bookTag = new BookTags();
//	        		bookTags.add(bookTag);
//	        	}
		}
		return newtagList;
	}
	
	public void validateAuthorRequestDto(AuthorRequestDto requestDto) {
		
	}
}
