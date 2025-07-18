package com.example.stockmanagement.domain;

public class Response {
	private String statuscode;
	private String message;

	public Response() {
		super();
	}

	public Response(String statuscode, String message) {
		super();
		this.statuscode = statuscode;
		this.message = message;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
