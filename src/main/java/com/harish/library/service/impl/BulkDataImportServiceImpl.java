package com.harish.library.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.harish.library.dto.BookRequestDto;
import com.harish.library.model.Book;
import com.harish.library.service.IFileParser;
import com.harish.library.service.IBookStoreService;
import com.harish.library.service.IBulkDataImportService;
import java.util.List;

/**
 * 
 * @author harishsc
 *
 */
@Service
public class BulkDataImportServiceImpl implements IBulkDataImportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BulkDataImportServiceImpl.class);

	@Autowired
	private final IBookStoreService bookStoreService;

	@Autowired
	@Qualifier("csvparser")
	IFileParser fileParser;

	BulkDataImportServiceImpl(IBookStoreService bookStoreService) {
		this.bookStoreService = bookStoreService;
	}

	public List<Book> importBooksFromFile(MultipartFile file) throws IOException {
		List<BookRequestDto> requestDtoList = fileParser.parseBookDtoFromFile(file);
		return bookStoreService.addBooks(requestDtoList);
	}
}
