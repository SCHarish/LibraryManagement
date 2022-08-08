package com.harish.library.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.model.Tag;
import com.harish.library.repository.TagRepository;
import com.harish.library.service.ITagStoreService;

/**
 * 
 * @author harishsc
 *
 */
@Service
public class TagStoreServiceImpl implements ITagStoreService{
	
	@Autowired
	private TagRepository tagRepository;
	
	public Set<Tag> getTagsByName(String name){
		return tagRepository.findTagsByName(name);
	}
	
	public List<String> findISBNByTagName(String name){
		return tagRepository.findISBNByTagName(name);
	}
}
