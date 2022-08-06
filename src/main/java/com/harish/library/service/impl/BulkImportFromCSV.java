package com.harish.library.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harish.library.model.Book;
import com.harish.library.service.BulkImportService;

public class BulkImportFromCSV extends BulkImportService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BulkImportService.class);
			
	public List<Book> importDataFromFile(String filePath){
		//validate filePath
		return null;
	}
}
