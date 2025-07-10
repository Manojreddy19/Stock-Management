package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentDetail;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.utilities.Status;

public interface AdjustmentDao {

	public void addAdjustmentDetails(List<AdjustmentDetail> adjustmentsToBeAdded) throws StockManagementException;

	public void updateGeneratedBatchId(Long adjustmentId, Long batchId, Long generatedBid)
			throws StockManagementException;

	void addAdjustment(Adjustment adjustmentHeader) throws StockManagementException;

	public void updateAdjustment(Long adjustmentId, Status status, String modifiedBy) throws StockManagementException;

	public List<Adjustment> getAdjustments() throws StockManagementException;
	
	public Adjustment getAdjustmentById(long adjustmentId);

	public List<AdjustmentDetail> getAdjustmentDetails(long adjustmentId) throws StockManagementException;
}
