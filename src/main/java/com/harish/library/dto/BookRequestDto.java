
package com.harish.library.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author harishsc
 *
 */
@ApiModel
public class BookRequestDto {
	@NotNull
	@JsonProperty("isbn")
	@ApiModelProperty(value = "ISBN no.", example = "333-2-99-248231-7", required = true)
	String isbn;

	@NotNull
	@JsonProperty("title")
	@ApiModelProperty(value = "Book title", example = "Harry Potter", required = false )
	String title;

	@NotNull
	@JsonProperty("authorId")
	@ApiModelProperty(value = "Author id", dataType="Long", example = "123", required = true)
	Long authorId;

	@NotNull
	@JsonProperty("tags")
	@ApiModelProperty(value = "Tags", dataType = "[Ljava.lang.String;", required = true)
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
		return authorId;
	}

	public void setAuthorId(Long author_id) {
		this.authorId = author_id;
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
		this.authorId = author_id;
		this.tags = tags;
	}

	private BookRequestDto(BookRequestDtoBuilder builder) {
		this.isbn = builder.isbn;
		this.authorId = builder.authorId;
		this.title = builder.title;
		this.tags = builder.tags;
	}

	public static class BookRequestDtoBuilder {
		private String isbn;
		private String title;
		private Long authorId;
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

		public BookRequestDtoBuilder author(Long authorId) {
			this.authorId = authorId;
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
