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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_copy")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookCopy { 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="bookCopyId")
	private Long bookCopyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Book book;//Owning entity
}
