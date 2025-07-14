package com.example.stockmanagement.domain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AdjustmentDetail {

	private Long adjustmentId;

	@NotNull(message = "Product Id is required")
	private String productId;

	@NotNull(message = "Batch is required")
	private String batch;

	@NotNull(message = "Batch Id is required")
	private Long batchId;

	@NotNull(message = "Quantity is required")
	@Min(value = 1, message = "Quantity must be positive")
	private Integer quantity;

	@NotNull(message = "MRP is required")
	@Min(value = 1, message = "MRP must be Positive")
	private Double mrp;

	@NotNull(message = "Amount is required")
	@Min(value = 1, message = "Amount must be Positive")
	private Double amount;

	@NotNull(message = "Expiry Date is required")
	private Date expiryDate;
	private Long generatedBatchId;

	@Override
	public String toString() {
		return "AdjustmentDetail [adjustmentId=" + adjustmentId + ", productId=" + productId + ", batch=" + batch
				+ ", batchId=" + batchId + ", quantity=" + quantity + ", mrp=" + mrp + ", amount=" + amount
				+ ", expiryDate=" + expiryDate + ", generatedBatchId=" + generatedBatchId + "]";
	}

	public AdjustmentDetail() {
		super();
	}

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
		this.expiryDate = expirydate;
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expirydate) {
		this.expiryDate = expirydate;
	}

	public Long getGeneratedBatchId() {
		return generatedBatchId;
	}

	public void setGeneratedBatchId(Long generatedBatchId) {
		this.generatedBatchId = generatedBatchId;
	}

}
