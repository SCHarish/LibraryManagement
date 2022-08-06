package com.harish.library.service;

import java.io.IOException;
import java.io.InputStream;

public interface IBulkDataImportService {
	public void importFromFile(InputStream fileStream) throws IOException;
}
