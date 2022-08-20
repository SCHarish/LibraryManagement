package com.harish.library.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.harish.library.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Set<Tag> findTagsByName(String name);	
}