package com.harish.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.library.model.Tag;
import com.harish.library.repository.TagRepository;
import com.harish.library.service.ITagStoreService;
@Service
public class TagStoreServiceImpl implements ITagStoreService{
	
	@Autowired
	private TagRepository tagRepository;
	
	public List<Tag> getTagsByName(String name){
		return tagRepository.findTagsByName(name);
	}
}
