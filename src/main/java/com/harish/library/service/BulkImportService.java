package com.harish.library.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import com.harish.library.dto.BookDto;
import com.harish.library.model.Book;

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
