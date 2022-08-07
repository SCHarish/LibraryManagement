package com.harish.library.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class AuthorRequestDto {
	@NotNull
	@JsonProperty("name")
	@ApiModelProperty(value = "Author of the book")
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
