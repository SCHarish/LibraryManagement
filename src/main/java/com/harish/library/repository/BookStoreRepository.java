package com.harish.library.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harish.library.model.Book;

@Repository
public interface BookStoreRepository extends CrudRepository<Book, String> {

//	@Query("SELECT b FROM Book b WHERE b.title LIKE %?1%" + " OR b.isbn LIKE %?1%")
//	public Set<Book> searchBooks(String keyword);
	
	public Set<Book> findByTitle(String title);
}