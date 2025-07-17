package com.example.stockmanagement.domain;

import java.util.Date;

import com.example.stockmanagement.utilities.AdjustmentType;
import com.example.stockmanagement.utilities.Status;

public class AdjustmentCriteria {
	Long adjustmentId;
	Status status;
	AdjustmentType adjustmentType;
	Date createdFrom;
	Date createdTo;
	Long limitFrom;
	Long noOfRows;
	boolean detailsRequired;

	public AdjustmentCriteria() {
	}

	@Override
	public String toString() {
		return "AdjustmentCriteria [adjustmentId=" + adjustmentId + ", status=" + status + ", adjustmentType="
				+ adjustmentType + ", createdFrom=" + createdFrom + ", createdTo=" + createdTo + ", limitFrom="
				+ limitFrom + ", noOfRows=" + noOfRows + ", detailsRequired=" + detailsRequired + "]";
	}

	public AdjustmentCriteria(Long adjustmentId, Status status, AdjustmentType adjustmentType, Date createdFrom,
			Date createdTo, Long limitFrom, Long noOfRows, boolean detailsRequired) {
		this.adjustmentId = adjustmentId;
		this.status = status;
		this.adjustmentType = adjustmentType;
		this.createdFrom = createdFrom;
		this.createdTo = createdTo;
		this.limitFrom = limitFrom;
		this.noOfRows = noOfRows;
		this.detailsRequired = detailsRequired;
	}

	public Long getAdjustmentId() {
		return adjustmentId;
	}

	public void setAdjustmentId(Long adjustmentId) {
		this.adjustmentId = adjustmentId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(AdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public Date getCreatedFrom() {
		return createdFrom;
	}

	public void setCreatedFrom(Date createdFrom) {
		this.createdFrom = createdFrom;
	}

	public Date getCreatedTo() {
		return createdTo;
	}

	public void setCreatedTo(Date createdTo) {
		this.createdTo = createdTo;
	}

	public Long getLimitFrom() {
		return limitFrom;
	}

	public void setLimitFrom(Long limitFrom) {
		this.limitFrom = limitFrom;
	}

	public Long getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(Long noOfRows) {
		this.noOfRows = noOfRows;
	}

	public boolean isDetailsRequired() {
		return detailsRequired;
	}

	public void setDetailsRequired(boolean detailsRequired) {
		this.detailsRequired = detailsRequired;
	}

}
