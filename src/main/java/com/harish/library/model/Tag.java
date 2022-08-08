package com.harish.library.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author harishsc
 *
 */
@Entity
@Table(name = "tags")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "books" })
public class Tag {
	@Id
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tags")
	private Set<Book> books = new HashSet<Book>();

	public Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBook(Book book) {
		this.books.add(book);
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
		books.stream().forEach(book -> book.setTag(this));
	}

	public Tag() {
		super();
	}

	public Tag(Long id, String name, Set<Book> books) {
		super();
		this.name = name;
		this.books = books;
	}

}
