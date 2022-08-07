package com.harish.library.dto;

public class ResponseDto {
	String message;
	
	Object payload;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	private ResponseDto(ResponseDtoBuilder builder) {
		this.message = builder.message;
		this.payload = builder.payload;
	}
	
	public static class ResponseDtoBuilder{
		private final String message;
		private Object payload;
		
		public ResponseDtoBuilder(String msg) {
			this.message = msg;
		}
		
		public ResponseDtoBuilder payload(Object payload) {
			this.payload = payload;
			return this;
		}
		
		public ResponseDto build() {
			var responseDto = new ResponseDto(this);
			//TODO :: validate book object
			return responseDto;
		}
	}
}
