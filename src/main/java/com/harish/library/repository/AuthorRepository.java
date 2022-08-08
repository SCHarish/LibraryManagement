package com.harish.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harish.library.model.Author;
import com.harish.library.model.Tag;

/**
 * 
 * @author harishsc
 *
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
	public List<Author> findAuthorsByName(String author_name);
}
