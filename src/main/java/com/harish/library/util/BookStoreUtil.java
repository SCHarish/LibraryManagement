package com.harish.library.util;

import com.harish.library.dto.RequestDto;

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
}
