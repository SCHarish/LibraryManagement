package com.harish.library.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import com.harish.library.dto.RequestDto;
import com.harish.library.model.Book;

public interface FileParser<T> {
  public List<T> read(Class<T> clazz, InputStream stream) throws IOException; 
}
