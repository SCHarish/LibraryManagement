package com.harish.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harish.library.model.Author;
import com.harish.library.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
