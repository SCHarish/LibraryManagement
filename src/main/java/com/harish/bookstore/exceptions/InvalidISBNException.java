package com.harish.bookstore.exceptions;

public class InvalidISBNException extends Exception {

	private static final long serialVersionUID = 1L;
	private final int status_code;

	public InvalidISBNException(String message, int status_code) {
		super(message);
		this.status_code = status_code;
	}
}
