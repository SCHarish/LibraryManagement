package com.harish.library.handlers;

import org.springframework.http.converter.HttpMessageNotReadableException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * 
 * @author harishsc
 *
 */
public class JacksonExceptionHandler {

	public String getErrorMessage(HttpMessageNotReadableException e) {
		String message = null;
		Throwable cause = e.getRootCause();

		if (cause instanceof UnrecognizedPropertyException) {
			message = "Unrecognized property";
		} else if (cause instanceof InvalidFormatException) {
			message = "Invalid JSON format";
		} else if (cause instanceof MismatchedInputException) {
			message = "Malformed json";
		} else if (cause instanceof JsonParseException) {
			message = "Malformed json";
		} else {
			message = "Invalid payload";
		}
		return message;
	}
}
