package com.harish.library.service;

import java.util.List;
import java.util.Set;

import com.harish.library.model.Tag;

public interface ITagStoreService {
	Set<Tag> getTagsByName(String name);
	List<String> findISBNByTagName(String name);
}
