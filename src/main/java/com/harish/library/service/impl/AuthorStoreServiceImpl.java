package com.harish.library.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.dto.AuthorRequestDto;
import com.harish.library.model.Author;
import com.harish.library.repository.AuthorRepository;
import com.harish.library.service.IAuthorStoreService;

@Service
public class AuthorStoreServiceImpl implements IAuthorStoreService{
	
	private final AuthorRepository authorRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	AuthorStoreServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper){
		this.authorRepository = authorRepository;
		this.modelMapper = modelMapper;
	}
	
	public Author addAuthor(AuthorRequestDto authorRequestDto) {
		Author newAuthor = modelMapper.map(authorRequestDto, Author.class);
		return authorRepository.save(newAuthor);
	}
	
	public Optional<Author> getAuthor(Long id) {
		Optional<Author> author = authorRepository.findById(id);
		return author;
	}
}
