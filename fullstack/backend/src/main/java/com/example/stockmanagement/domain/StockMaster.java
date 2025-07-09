package com.example.stockmanagement.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StockMaster {
	private String productId;
	private String batch;
	private double BatchId;
	private int quantity;
	private LocalDate expiryDate;
	private int mrp;
	private String createdBy;
	private LocalDateTime createdAt;
	private String modifiedBy;
	private LocalDateTime modifiedAt;
	
	
	
	public StockMaster(String productId, String batch, double batchId, int quantity, LocalDate expiryDate, int mrp,
			String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt) {
		super();
		this.productId = productId;
		this.batch = batch;
		BatchId = batchId;
		this.quantity = quantity;
		this.expiryDate = expiryDate;
		this.mrp = mrp;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.modifiedBy = modifiedBy;
		this.modifiedAt = modifiedAt;
	}
	
	
	public StockMaster() {
		super();
	}


	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public double getBatchId() {
		return BatchId;
	}
	public void setBatchId(double batchId) {
		BatchId = batchId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getMrp() {
		return mrp;
	}
	public void setMrp(int mrp) {
		this.mrp = mrp;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	
}