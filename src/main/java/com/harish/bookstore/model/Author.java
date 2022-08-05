package com.harish.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long author_id;

	@Column(name = "name", length = 100, nullable = false, unique = true)
	private String name;

//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
//			CascadeType.REMOVE }, mappedBy = "authors")
//	private Set<Book> books = new HashSet<Book>();
//
//	public Author(String name) {
//		this.name = name;
//	}
//
//	public Long getAuthorId() {
//		return author_id;
//	}
//
//	public void setId(Long authorId) {
//		this.author_id = authorId;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Set<Book> getBooks() {
//		return books;
//	}
//
//	public void setBooks(Set<Book> books) {
//		this.books = books;
//	}
//
//	public Author() {
//		super();
//	}
}
