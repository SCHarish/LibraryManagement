package com.harish.library.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name = "book")
public class Book {
	
	@Column(name = "isbn", length = 13, nullable = false, unique = true)
	private String isbn;

	@Column(name = "title", length = 150, nullable = false)
	private String title;
	
	private Author author;
	
//	@ElementCollection(targetClass=Tag.class)
	private Set<Tag> tags = new HashSet<Tag>();

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
	
	// Many books can be written by one author
	@ManyToOne(targetEntity = Author.class, cascade=CascadeType.ALL) 
	public Author getAuthor() {
		return author;
	}
	
	// A book can have many tags and vice-versa
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "book_tags", 
		joinColumns = @JoinColumn(name= "book_isbn"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> funnyTag) {
		this.tags = funnyTag;
	}
	
	public Book() {
		super();
	}

	public Book(String isbn, String title) {
		this.isbn = isbn;
		this.title = title;
	}

	public Book(String isbn, String title, Author author, Set<Tag> tags) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.tags = tags;
	}
	
	private Book(BookBuilder builder) {
		this.isbn = builder.isbn;
		this.title = builder.title;
		this.author = builder.author;
		this.tags = builder.tags;
	}
	
	public static class BookBuilder{
		private final String isbn;
		private final String title;
		private Author author;
		private Set<Tag> tags;
		
		public BookBuilder(String isbn, String title) {
			this.isbn = isbn;
			this.title = title;
		}
		
		public BookBuilder Author(Author author) {
			this.author = author;
			return this;
		}
		
		public BookBuilder Tags(List<Tag> tagList) {
			Set<Tag> hSet = new HashSet<Tag>();
	        for (Tag tag : tagList) {
	            hSet.add(tag);
	        }
			this.tags = hSet;
			return this;
		}
		
		public Book build() {
			var book = new Book(this);
			//TODO :: validate book object
			return book;
		}
	}
}
