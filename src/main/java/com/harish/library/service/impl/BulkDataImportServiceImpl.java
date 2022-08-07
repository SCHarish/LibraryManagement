package com.harish.library.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.harish.library.dto.RequestDto;
import com.harish.library.service.FileParser;
import com.harish.library.service.IBookStoreService;
import com.harish.library.service.IBulkDataImportService;
import java.util.List;
@Service
public class BulkDataImportServiceImpl implements IBulkDataImportService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	
	@Autowired
	private final IBookStoreService bookStoreService;
	
	@Autowired
	@Qualifier("csvparser")
	FileParser parser;
	
	BulkDataImportServiceImpl(IBookStoreService bookStoreService){
		this.bookStoreService = bookStoreService;
	}
	
	public void importFromFile(MultipartFile file) throws IOException {
		List<RequestDto> requestDtoList = parser.read(file);
		bookStoreService.addBooks(requestDtoList);
	}
}
