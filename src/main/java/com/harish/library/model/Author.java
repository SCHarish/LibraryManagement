package com.harish.library.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Author")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "books"})
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@OneToMany(targetEntity=Book.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="author_id", referencedColumnName = "id")
	private Set<Book> books;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//An author can write many books
	@OneToMany(mappedBy = "author")
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	} 
	
	public Author(String name) {
		this.name = name;
	}
	
	public Author() {
		super();
	}
}