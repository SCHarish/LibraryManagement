package com.harish.library.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harish.library.model.Author;
import com.harish.library.model.Tag;

import io.swagger.annotations.ApiModelProperty;

public class RequestDto {
	@NotNull
	@Pattern(regexp = "*")
	@JsonProperty("isbn")
	@ApiModelProperty(value = "ISBN no. of the book")
	String isbn;

	@NotNull
	@JsonProperty("title")
	@ApiModelProperty(value = "Title of the book")
	String title;

	@NotNull
	@JsonProperty("author")
	@ApiModelProperty(value = "Author information")
	Author author;
	
	@NotNull
	@JsonProperty("tag")
	@ApiModelProperty(value = "Tag information")
	Tag[] tag;
	

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
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Tag[] getTag() {
		return tag;
	}

	public void setTag(Tag[] tag) {
		this.tag = tag;
	}
	
	public RequestDto() {
		super();
	}
}
