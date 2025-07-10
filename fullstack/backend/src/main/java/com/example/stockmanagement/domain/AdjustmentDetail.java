package com.example.stockmanagement.domain;

import java.util.Date;

public class AdjustmentDetail {
	
	private Long adjustmentId;
	private String productId;
	private String batch;
	private Long batchId;
	private Integer quantity;
	private Double mrp;
	private Double amount;
	private Date expirydate;
	private Long generatedBatchId;
	public AdjustmentDetail(Long adjustmentId, String productId, String batch, Long batchId, Integer quantity,
			Double mrp, Double amount, Date expirydate, Long generatedBatchId) {
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
	public Long getAdjustmentId() {
		return adjustmentId;
	}
	public void setAdjustmentId(Long adjustmentId) {
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
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	public Long getGeneratedBatchId() {
		return generatedBatchId;
	}
	public void setGeneratedBatchId(Long generatedBatchId) {
		this.generatedBatchId = generatedBatchId;
	}
	
	
	
	
	
	
}

