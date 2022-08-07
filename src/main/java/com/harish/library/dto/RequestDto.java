package com.harish.library.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harish.library.model.Author;
import com.harish.library.model.Tag;

import io.swagger.annotations.ApiModelProperty;

public class RequestDto {
	@NotNull
	@JsonProperty("isbn")
	@ApiModelProperty(value = "ISBN no. of the book")
	String isbn;

	@NotNull
	@JsonProperty("title")
	@ApiModelProperty(value = "Title of the book")
	String title;

	@NotNull
	@JsonProperty("author_id")
	@ApiModelProperty(value = "Author information")
	Long author_id;
	
	@NotNull
	@JsonProperty("tags")
	@ApiModelProperty(value = "Tag information")
	String[] tags;

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
	
	public Long getAuthorId() {
		return author_id;
	}

	public void setAuthorId(Long author_id) {
		this.author_id = author_id;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	public RequestDto() {
		super();
	}
	
	public RequestDto(String isbn, String title, Long author_id, String[] tags) {
		this.isbn = isbn;
		this.title = title;
		this.author_id = author_id;
		this.tags = tags;
	}
}
