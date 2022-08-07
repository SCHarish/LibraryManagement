package com.harish.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM TAGS t WHERE t.name IN (:names)")
	public List<Tag> getTags(List<String> names);
	
	public Tag findTagByName(String name);
	
	public List<Tag> findTagsByName(String name);
}