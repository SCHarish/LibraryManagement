package com.harish.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harish.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}