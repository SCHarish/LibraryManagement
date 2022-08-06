package com.harish.library.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.model.Book;
import com.harish.library.repository.BookStoreRepository;
import com.harish.library.service.FileParser;
import com.harish.library.service.IBulkDataImportService;

@Service
public class BulkDataImportServiceImpl implements IBulkDataImportService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	
	@Autowired
	private final BookStoreRepository bookStoreRepository;
	
	BulkDataImportServiceImpl(BookStoreRepository bookStoreRepository){
		this.bookStoreRepository = bookStoreRepository;
	}
	
	public void importFromFile(InputStream fileStream) throws IOException {
		FileParser parser = new CSVParser();
		parser.read(Book.class, fileStream);
	}
}
