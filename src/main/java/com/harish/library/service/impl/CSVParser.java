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
import org.springframework.web.multipart.MultipartFile;

import com.harish.library.dto.RequestDto;
import com.harish.library.service.FileParser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component(value = "csvparser")
public class CSVParser implements FileParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVParser.class);

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<RequestDto> read(MultipartFile file) throws IOException {
		List<RequestDto> booksInCSV = new ArrayList<RequestDto>();
		// parse CSV file to create a list of `RequestDto` objects
		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			// create csv bean reader
			CsvToBean<RequestDto> csvToBean = new CsvToBeanBuilder(reader).withSeparator(',').withType(RequestDto.class)
					.build();

			// convert `CsvToBean` object to list of users
			booksInCSV = csvToBean.parse();
		} catch (Exception ex) {
			LOGGER.error("Error occurred in file parsing");
			throw new IOException();
		}
		return booksInCSV;
	}
}
