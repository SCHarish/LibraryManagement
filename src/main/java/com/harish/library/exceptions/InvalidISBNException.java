package com.harish.library.exceptions;

public class InvalidISBNException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidISBNException(String message) {
		super(message);
	}
}
