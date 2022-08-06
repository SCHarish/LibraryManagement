package com.harish.library.util;

import java.util.List;

import com.harish.library.dto.RequestDto;
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
	
	public static Book constructBook(RequestDto requestDto, List<Tag> tagList){
		var book = new Book.BookBuilder(requestDto.getIsbn(), requestDto.getTitle())
				.Author(requestDto.getAuthor()).Tags(tagList).build();
        return book;
	}
}
