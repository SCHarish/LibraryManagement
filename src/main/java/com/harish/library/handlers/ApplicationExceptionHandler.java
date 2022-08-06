package com.harish.library.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.harish.library.dto.ErrorResponse;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.exceptions.DuplicateBookFoundException;
import com.harish.library.exceptions.InvalidISBNException;
 
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
 
  @ExceptionHandler(BookNotFoundException.class)
  public final ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Record Not Found", details);
    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(InvalidISBNException.class)
  public final ResponseEntity<Object> handleInvalidISBN(InvalidISBNException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Invalid ISBN", details);
    return new ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY);
  }
  
  @ExceptionHandler(DuplicateBookFoundException.class)
  public final ResponseEntity<Object> handleDuplicateBookFoundException(DuplicateBookFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Duplicate book found", details);
    return new ResponseEntity(error, HttpStatus.CONFLICT);
  }
  
  @ExceptionHandler(RuntimeException.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Server Error", details);
    return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	 List<String> details = new ArrayList<>();
	 String message = new JacksonExceptionHandler().getErrorMessage(ex);
	 details.add(ex.getMessage());
	 ErrorResponse error = new ErrorResponse("Malformed JSON", details);
	 return ResponseEntity.badRequest().body(error);
  }
 
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> details = new ArrayList<>();
    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorResponse error = new ErrorResponse("Validation Failed", details);
    return ResponseEntity.badRequest().body(error);
  }
}