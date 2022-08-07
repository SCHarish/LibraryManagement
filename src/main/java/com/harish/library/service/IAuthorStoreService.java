package com.harish.library.service;

import java.util.List;
import java.util.Optional;

import com.harish.library.dto.AuthorRequestDto;
import com.harish.library.model.Author;

public interface IAuthorStoreService {
	Author addAuthor(AuthorRequestDto authorRequestDto);
	
	Optional<Author> getAuthor(Long id);
	
	List<Author> getAuthorsByName(String author_name);
}
