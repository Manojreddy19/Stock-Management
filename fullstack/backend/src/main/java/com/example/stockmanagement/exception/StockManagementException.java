package com.example.stockmanagement.exception;

import org.springframework.stereotype.Component;

@Component
public class StockManagementException extends Exception {
	private static final long serialVersionUID = 1L;

	public StockManagementException(String message) {
		super(message);
	}
}