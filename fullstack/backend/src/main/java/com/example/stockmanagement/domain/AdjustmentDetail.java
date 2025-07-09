package com.example.stockmanagement.domain;

import java.time.LocalDate;

public class AdjustmentDetail {
	
	private int adjustmentId;
	private String productId;
	private String batch;
	private long batchId;
	private int quantity;
	private int mrp;
	private int amount;
	private LocalDate expirydate;
	private double generatedBatchId;
	
	
	public AdjustmentDetail(int adjustmentId, String productId, String batch, long batchId, int quantity, int mrp,
			int amount, LocalDate expirydate, double generatedBatchId) {
		super();
		this.adjustmentId = adjustmentId;
		this.productId = productId;
		this.batch = batch;
		this.batchId = batchId;
		this.quantity = quantity;
		this.mrp = mrp;
		this.amount = amount;
		this.expirydate = expirydate;
		this.generatedBatchId = generatedBatchId;
	}
	
	public AdjustmentDetail() {
		super();
	}

	public int getAdjustmentId() {
		return adjustmentId;
	}
	public void setAdjustmentId(int adjustmentId) {
		this.adjustmentId = adjustmentId;
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
	public long getBatchId() {
		return batchId;
	}
	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getMrp() {
		return mrp;
	}
	public void setMrp(int mrp) {
		this.mrp = mrp;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(LocalDate expirydate) {
		this.expirydate = expirydate;
	}
	public double getGeneratedBatchId() {
		return generatedBatchId;
	}
	public void setGeneratedBatchId(double generatedBatchId) {
		this.generatedBatchId = generatedBatchId;
	}
	
	
}