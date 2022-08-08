package com.harish.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.dto.AuthorRequestDto;
import com.harish.library.exceptions.NoResultsFoundException;
import com.harish.library.model.Author;
import com.harish.library.repository.AuthorRepository;
import com.harish.library.service.IAuthorStoreService;

/**
 * 
 * @author harishsc
 *
 */
@Service
public class AuthorStoreServiceImpl implements IAuthorStoreService{
	
	private final AuthorRepository authorRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	AuthorStoreServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper){
		this.authorRepository = authorRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public Author addAuthor(AuthorRequestDto authorRequestDto) {
		Author newAuthor = modelMapper.map(authorRequestDto, Author.class);
		return authorRepository.save(newAuthor);
	}
	
	@Override
	public Optional<Author> getAuthor(Long id) {
		Optional<Author> author = authorRepository.findById(id);
		return author;
	}
	
	@Override
	public List<Author> getAllAuthors(){
		List<Author> authorList = authorRepository.findAll();	
		if(authorList.isEmpty()) {
			throw new NoResultsFoundException("No author data available");
		}
		return authorList;
	}
	
	@Override
	public List<Author> getAuthorsByName(String author_name){
		List<Author> authorList = authorRepository.findAuthorsByName(author_name);
		return authorList;
	}
}
