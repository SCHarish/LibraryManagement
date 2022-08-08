package com.harish.library.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.InvalidFileException;
import com.harish.library.service.IFileParser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * CSV parser
 * 
 * @author harishsc
 *
 */
@Service(value = "csvparser")
public class CSVParser implements IFileParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVParser.class);

	@Override
	public List<BookRequestDto> parseBookDtoFromFile(MultipartFile file) throws IOException {
		List<BookRequestDto> booksInCSV = new ArrayList<BookRequestDto>();
		// parse CSV file to create a list of `BookRequestDto` objects
		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			// create csv bean reader
			CsvToBean<BookRequestDto> csvToBean = new CsvToBeanBuilder(reader).withSeparator(',')
					.withType(BookRequestDto.class).build();

			// convert `CsvToBean` object to list of books
			booksInCSV = csvToBean.parse();
		} catch (Exception ex) {
			LOGGER.error("Error occurred in file parsing - " + ex.getMessage());
			throw new InvalidFileException("Invalid csv file content :: " + ex.getMessage());
		}
		return booksInCSV;
	}
}
