package com.example.stockmanagement.domain;

import java.util.Date;
import java.util.List;

import com.example.stockmanagement.utilities.AdjustmentType;
import com.example.stockmanagement.utilities.Status;

public class Adjustment {

	private Long adjustmentId;
	private AdjustmentType adjustmentType;
	private Double amount;
	private Status status;
	private String remarks;
	private List<AdjustmentDetail> adjustmentDetails;
	private String createdBy;
	private Date createdAt;
	private String modifiedBy;
	private Date modifiedAt;

	public Adjustment(Long adjustmentId, AdjustmentType adjustmentType, Double amount, Status status, String remarks,
			List<AdjustmentDetail> adjustmentDetails, String createdBy, Date createdAt, String modifiedBy,
			Date modifiedAt) {
		super();
		this.adjustmentId = adjustmentId;
		this.adjustmentType = adjustmentType;
		this.amount = amount;
		this.status = status;
		this.remarks = remarks;
		this.adjustmentDetails = adjustmentDetails;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.modifiedBy = modifiedBy;
		this.modifiedAt = modifiedAt;

	}

	public Long getAdjustmentId() {
		return adjustmentId;
	}

	public void setAdjustmentId(Long adjustmentId) {
		this.adjustmentId = adjustmentId;
	}

	public AdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(AdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<AdjustmentDetail> getAdjustmentDetails() {
		return adjustmentDetails;
	}

	public void setAdjustmentDetails(List<AdjustmentDetail> adjustmentDetails) {
		this.adjustmentDetails = adjustmentDetails;
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
