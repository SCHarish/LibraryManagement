package com.harish.library.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.harish.library.model.Book;

public interface IBulkDataImportService {
	/**
	 * 
	 * @param fileStream
	 * @throws IOException
	 */
	public List<Book> importBooksFromFile(MultipartFile fileStream) throws IOException;
}
