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
@Table(name = "tags")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "books" })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
	@Id
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tags")
	private Set<Book> books = new HashSet<Book>(0);
}
