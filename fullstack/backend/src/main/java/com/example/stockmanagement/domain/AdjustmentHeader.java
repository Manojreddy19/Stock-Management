package com.example.stockmanagement.domain;


import java.time.LocalDateTime;

public class AdjustmentHeader {
	private int adjustmentId;
	private char adjustmentType;
	private double amount ;
	private char status;
	private String createdBy;
	private LocalDateTime createdAt;
	private String modifiedBy;
	private String modifiedAt;
	private String remarks;
	
	
	
	public int getAdjustmentId() {
		return adjustmentId;
	}
	public void setAdjustmentId(int adjustmentId) {
		this.adjustmentId = adjustmentId;
	}
	public char getAdjustmentType() {
		return adjustmentType;
	}
	public void setAdjustmentType(char adjustmentType) {
		this.adjustmentType = adjustmentType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
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
	public String getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	
}
