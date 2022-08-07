package com.harish.library.util;

import java.util.ArrayList;
import java.util.List;

import com.harish.library.dto.RequestDto;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.model.Tag;

public class BookStoreUtil {
	public static boolean isValidISBN(String isbn){
		isbn = isbn.replaceAll("-", "");
        int n = isbn.length();
        if (n != 13) {
            return false;
        }
        return true;
 
    }
	
	public static boolean validateDto(RequestDto requestDto) {
		//check if author id is null?
	
		return true;
	}
	
	public static Book constructBook(RequestDto requestDto, Author author, List<Tag> tagList){
		var book = new Book.BookBuilder(requestDto.getIsbn(), requestDto.getTitle())
				.Author(author).Tags(tagList).build();
        return book;
	}
	
	public static List<Tag> constructTags(List<String> tags){
		List<Tag> newtagList = new ArrayList();
        //List<BookTags> bookTags = new ArrayList();
        for(String tag : tags) {
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
        return newtagList;
	}
}
