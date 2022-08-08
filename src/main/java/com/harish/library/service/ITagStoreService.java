package com.harish.library.service;

import java.util.List;
import java.util.Set;

import com.harish.library.model.Tag;

/**
 * 
 * @author harishsc
 *
 */
public interface ITagStoreService {
	/**
	 * 
	 * @param name
	 * @return
	 */
	Set<Tag> getTagsByName(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	List<String> findISBNByTagName(String name);
}
