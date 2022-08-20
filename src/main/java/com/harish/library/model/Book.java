package com.harish.library.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author harishsc
 *
 */
@Entity
@Table(name = "book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Column(name = "isbn", length = 17, nullable = false, unique = true)
	@Id
	private String isbn;

	@Column(name = "title", length = 150, nullable = false)
	private String title;

	// Many books can be written by one author
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
	//@JoinColumn(name = "author_id")
	private Author author; //Owning side

	// A book can have many tags and vice-versa
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "book_tags", joinColumns = { @JoinColumn(name = "book_isbn") }, inverseJoinColumns = {
			@JoinColumn(name = "tag_name") })
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Tag> tags = new HashSet<Tag>(0);
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<BookCopy> bookCopies = new HashSet<BookCopy>(0); //Inverse side
	
	@Column
	@Min(1)
	private int numberOfCopies = 1;
}
