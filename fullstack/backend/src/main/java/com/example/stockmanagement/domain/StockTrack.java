package com.example.stockmanagement.domain;

import java.time.LocalDate;

public class StockTrack {
	private double batchId;
	private char transactionType;
	private int quantity;
	private int openStock;
	private LocalDate createdDate;
	private LocalDate createdBy;
	
	public StockTrack(double batchId, char transactionType, int quantity, int openStock, LocalDate createdDate,
			LocalDate createdBy) {
		super();
		this.batchId = batchId;
		this.transactionType = transactionType;
		this.quantity = quantity;
		this.openStock = openStock;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}
	
	public StockTrack() {
		super();
	}
	
	
	public double getBatchId() {
		return batchId;
	}
	public void setBatchId(double batchId) {
		this.batchId = batchId;
	}
	public char getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(char transactionType) {
		this.transactionType = transactionType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOpenStock() {
		return openStock;
	}
	public void setOpenStock(int openStock) {
		this.openStock = openStock;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(LocalDate createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
}
