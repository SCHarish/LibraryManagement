package com.harish.library.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Representation of Book Table
 **/
@Entity
@Table(name = "Book")
public class Book {
	
	@Column(name = "isbn", length = 13, nullable = false, unique = true)
	private String isbn;

	@Column(name = "title", length = 150, nullable = false)
	private String title;
	
	private Author author;

	//private Set<Tag_Temp> tags = new HashSet<Tag_Temp>();

	@Id
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@ManyToOne(targetEntity = Author.class, cascade=CascadeType.ALL)
	public Author getAuthor() {
		return author;
	}

	public Book(String isbn, String title) {
		this.isbn = isbn;
		this.title = title;
	}
	
	public Book() {
		super();
	}
}
