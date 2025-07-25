package com.example.stockmanagement.domain;

import java.util.Date;

import com.example.stockmanagement.utilities.AdjustmentType;

public class StockTrack {
	private Long batchId;
	private AdjustmentType transactionType;
	private Integer quantity;
	private Integer openStock;
	private Date createdDate;
	private String createdBy;

	public StockTrack(Long batchId, AdjustmentType transactionType, Integer quantity, Integer openStock,
			Date createdDate, String createdBy) {
		super();
		this.batchId = batchId;
		this.transactionType = transactionType;
		this.quantity = quantity;
		this.openStock = openStock;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public AdjustmentType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(AdjustmentType transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOpenStock() {
		return openStock;
	}

	public void setOpenStock(Integer openStock) {
		this.openStock = openStock;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
