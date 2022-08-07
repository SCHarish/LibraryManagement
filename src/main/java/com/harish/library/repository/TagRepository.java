package com.harish.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harish.library.model.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {

//	@Query(nativeQuery = true, value = "SELECT * FROM TAGS t WHERE t.name IN (:names)")
//	public List<Tag> getTags(List<String> names);

	public List<Tag> findTagsByName(String name);
}