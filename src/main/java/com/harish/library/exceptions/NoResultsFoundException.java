package com.harish.library.exceptions;

public class NoResultsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoResultsFoundException(String message) {
		super(message);
	}

}
