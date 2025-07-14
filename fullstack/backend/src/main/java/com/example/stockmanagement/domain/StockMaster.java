package com.example.stockmanagement.domain;

import java.util.Date;

public class StockMaster {
	private String productId;
	private String batch;
	private Long BatchId;
	private Integer quantity;
	private Date expiryDate;
	private Double mrp;
	private String createdBy;
	private Date createdAt;
	private String modifiedBy;
	private Date modifiedAt;

	public StockMaster() {
		super();
	}

	public StockMaster(String productId, String batch, Long batchId, Integer quantity, Date expiryDate, Double mrp,
			String createdBy, Date createdAt, String modifiedBy, Date modifiedAt) {
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
		return BatchId;
	}

	public void setBatchId(Long batchId) {
		BatchId = batchId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
