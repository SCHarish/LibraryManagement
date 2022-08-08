package com.harish.library.service;

import java.util.List;
import java.util.Optional;

import com.harish.library.dto.AuthorRequestDto;
import com.harish.library.model.Author;

/**
 * 
 * @author harishsc
 *
 */
public interface IAuthorStoreService {
	/**
	 * 
	 * @param authorRequestDto
	 * @return
	 */
	Author addAuthor(AuthorRequestDto authorRequestDto);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Author> getAuthor(Long id);
	
	/**
	 * 
	 * @param author_name
	 * @return
	 */
	List<Author> getAuthorsByName(String author_name);
	
	/**
	 * 
	 * @return
	 */
	List<Author> getAllAuthors();
}
