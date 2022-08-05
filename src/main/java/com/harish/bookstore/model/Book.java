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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representation of Book Table
 **/
@Entity
public class Book {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;

	@Id
	@Column(name = "isbn", length = 50, nullable = false, unique = true)
	private String isbn;

	@Column(name = "title", length = 100, nullable = false)
	private String title;
	
	public Book(String isbn, String title) {
		this.isbn = isbn;
		this.title = title;
	}
	
	public Book() {
		
	}
	
//	@Column(name = "author_id", nullable = false)
//	private Long author_id;
//	
//	//@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
////	@JoinTable(name = "tags", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
//			//@JoinColumn(name = "tag_id") })
//	private Set<Tag> tags = new HashSet<Tag>();
}
