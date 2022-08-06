package com.harish.library.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.harish.library.model.Book;
import com.harish.library.service.FileParser;

import com.fasterxml.jackson.databind.ObjectReader;

public class CSVParser<Book> implements FileParser{
	private static final CsvMapper mapper = new CsvMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(FileParser.class);
			
	public List<Book> importDataFromFile(String filePath){
		//validate filePath	
		return null;
	}
	
//	
//    public List<Book> read(Class<T> clazz, InputStream stream) {
//        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
//        ObjectReader reader = mapper.readerFor(clazz).with(schema);
//        return reader.<Book>readValues(stream).readAll();
//    }


	@Override
	public List read(Class clazz, InputStream stream) throws IOException {
		// TODO Auto-generated method stub
		CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
         ObjectReader reader = mapper.readerFor(clazz).with(schema);
         List<Object> objList = reader.readValues(stream).readAll();
         return null;
	}

}
