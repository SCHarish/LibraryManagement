package com.harish.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harish.library.model.Book;

@Repository
public interface BookStoreRepository extends CrudRepository<Book, String> {


}