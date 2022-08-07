package com.harish.library.service;

import java.util.List;

import com.harish.library.model.Tag;

public interface ITagStoreService {
	List<Tag> getTagsByName(String name);
}
