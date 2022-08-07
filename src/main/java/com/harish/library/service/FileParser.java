package com.harish.library.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.harish.library.dto.RequestDto;

public interface FileParser {
  public List<RequestDto> read(MultipartFile file) throws IOException; 
}
