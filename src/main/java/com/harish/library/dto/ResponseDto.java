package com.harish.library.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseDto {
	String message;
	
	@JsonInclude(Include.NON_NULL)
	Object payload;
	
	// Specific errors in API request processing
	@JsonInclude(Include.NON_NULL)
	private List<String> details;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getPayload() {
		return payload;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	
	private ResponseDto(ResponseDtoBuilder builder) {
		this.message = builder.message;
		this.payload = builder.payload;
		this.details = builder.details;
	}
	
	public static class ResponseDtoBuilder{
		private final String message;
		private Object payload;
		private List<String> details;
		
		public ResponseDtoBuilder(String msg) {
			this.message = msg;
		}
		
		public ResponseDtoBuilder payload(Object payload) {
			this.payload = payload;
			return this;
		}
		
		public ResponseDtoBuilder details(List<String> details) {
			this.details = details;
			return this;
		}
		
		public ResponseDto build() {
			var responseDto = new ResponseDto(this);
			//TODO :: validate book object
			return responseDto;
		}
	}
}
