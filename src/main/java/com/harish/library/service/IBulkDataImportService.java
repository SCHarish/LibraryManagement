package com.harish.library.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface IBulkDataImportService {
	public void importFromFile(MultipartFile fileStream) throws IOException;
}
