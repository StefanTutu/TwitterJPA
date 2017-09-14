package com.cgm.exception.response;

public class ExceptionResponse {
	private String errorMessage;

	public ExceptionResponse(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
