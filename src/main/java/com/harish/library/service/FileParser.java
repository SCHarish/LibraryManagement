package com.harish.library.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.harish.library.dto.BookRequestDto;

public interface FileParser {
	/**
	 * Parse the CSV file and extracts book request DTO
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public List<BookRequestDto> parse(MultipartFile file) throws IOException;
}
