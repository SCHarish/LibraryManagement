package com.harish.library.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BookRequestDto {
	@NotNull
	@JsonProperty("isbn")
	@ApiModelProperty(value = "ISBN no.")
	String isbn;

	@NotNull
	@JsonProperty("title")
	@ApiModelProperty(value = "Book title")
	String title;

	@NotNull
	@JsonProperty("author_id")
	@ApiModelProperty(value = "Author id")
	Long author_id;

	@NotNull
	@JsonProperty("tags")
	@ApiModelProperty(value = "Tags")
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

	public BookRequestDto() {
		super();
	}

	public BookRequestDto(String isbn, String title, Long author_id, String[] tags) {
		this.isbn = isbn;
		this.title = title;
		this.author_id = author_id;
		this.tags = tags;
	}

	private BookRequestDto(BookRequestDtoBuilder builder) {
		this.isbn = builder.isbn;
		this.author_id = builder.author_id;
		this.title = builder.title;
		this.tags = builder.tags;
	}

	public static class BookRequestDtoBuilder {
		private String isbn;
		private String title;
		private Long author_id;
		private String[] tags;

		public BookRequestDtoBuilder() {
			super();
		}

		public BookRequestDtoBuilder isbn(String isbn) {
			this.isbn = isbn;
			return this;
		}

		public BookRequestDtoBuilder title(String title) {
			this.title = title;
			return this;
		}

		public BookRequestDtoBuilder author(Long author_id) {
			this.author_id = author_id;
			return this;
		}

		public BookRequestDtoBuilder tags(String[] tags) {
			this.tags = tags;
			return this;
		}

		public BookRequestDto build() {
			var bookRequestDto = new BookRequestDto(this);
			// TODO :: validate book object
			return bookRequestDto;
		}
	}
}
