package com.example.stockmanagement.service;

import java.util.List;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.utilities.Status;

public interface AdjustmentService {
	public long addAdjustment(Adjustment adjustment) throws StockManagementException;

	public void updateStatus(Long adjustmentId, Status status, String modifiedBy, String remarks) throws StockManagementException;

	public List<Adjustment> getAdjustments() throws StockManagementException;
	public long getAdjustmentsCount(AdjustmentCriteria adjustmentCriteria);
}
