package com.harish.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harish.library.model.BookCopy;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long>{

}
