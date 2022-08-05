package com.harish.bookstore.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import com.harish.bookstore.dto.BookDto;
import com.harish.bookstore.model.Book;

public abstract class BulkImportService {
	
	boolean checkIfFileExists(String filePath)throws FileNotFoundException {
		return true;
	}
	
	abstract public List<Book> importDataFromFile(String filePath);
	
	//convert book to bookdto
	Optional<Object> convertModelToDTO(List<Book> bookList){
		return Optional.empty();
	}
	
}
