package com.harish.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harish.bookstore.model.Book;

@Repository
public interface BookStoreRepository extends CrudRepository<Book, String> {


}