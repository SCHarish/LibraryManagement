package com.harish.library.exceptions;

public class DuplicateBookFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateBookFoundException(String message) {
		super(message);
	}
}
