package com.example.stockmanagement.exception;

public class StockManagementException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	String message;
	public StockManagementException(String message) {
		super(message);
		this.message=message;
		
	}
	public String getMessage()
	{
		return message;
	}
}