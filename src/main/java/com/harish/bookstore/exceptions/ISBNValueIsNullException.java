package com.harish.bookstore.exceptions;

public class ISBNValueIsNullException extends RuntimeException{
	public ISBNValueIsNullException(String message){
		super(message);
	}
}
