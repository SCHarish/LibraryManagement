package com.harish.library.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harish.library.model.Book;

@Repository
public interface BookStoreRepository extends CrudRepository<Book, String> {
	/**
	 * 
	 * @param title
	 * @return
	 */
	public Set<Book> findByTitle(String title);
}