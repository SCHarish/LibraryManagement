package com.harish.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harish.library.model.Tag;

import io.swagger.annotations.ApiModelProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
	@NotNull
	@Pattern(regexp="*")
	@JsonProperty("isbn")
	@ApiModelProperty(value="ISBN no. of the book")
	@Getter(AccessLevel.PUBLIC)
	String isbn;
	
	@NotNull
	@JsonProperty("title")
	@ApiModelProperty(value="Title of the book")
	String title;
	
	@NotNull
	@JsonProperty("authorId")
	@ApiModelProperty(value="Author of the book")
	String authorId;
	
	@NotNull
	@JsonProperty("tags")
	@ApiModelProperty(value="Book tags")
	Tag[] tags;
}
