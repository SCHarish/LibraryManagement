package com.harish.library.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class AuthorRequestDto {
	@NotNull
	@JsonProperty("name")
	@ApiModelProperty(value = "Author of the book")
	String name;
	
	@JsonProperty("country")
	@ApiModelProperty(value = "Author country")
	String country;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
