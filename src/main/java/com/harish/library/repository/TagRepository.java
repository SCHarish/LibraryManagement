package com.harish.library.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.harish.library.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

//	@Query(nativeQuery = true, value = "SELECT * FROM TAGS t WHERE t.name IN (:names)")
//	public List<Tag> getTags(List<String> names);

	public Set<Tag> findTagsByName(String name);
	
//	@Query(nativeQuery = true, value = "SELECT ISBN FROM BOOK_TAGS t WHERE t.id IN (:name)")
	@Query(nativeQuery = true, value = "SELECT * FROM BOOK_TAGS")
	public List<String> findISBNByTagName(String name);
}