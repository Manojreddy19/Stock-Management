package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
import com.example.stockmanagement.domain.AdjustmentDetail;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.utilities.Status;

public interface AdjustmentDao {
	
	public long getAdjustmentCount(AdjustmentCriteria adjustmentCriteria);

	void addAdjustmentDetails(long id, List<AdjustmentDetail> adjustment) throws StockManagementException;

	public void updateGeneratedBatchId(Long adjustmentId, Long batchId, Long generatedBid)
			throws StockManagementException;

	long addAdjustment(Adjustment adjustmentHeader) throws StockManagementException;

	public void updateAdjustment(Long adjustmentId, Status status, String modifiedBy, String remarks) throws StockManagementException;

	public List<Adjustment> getAdjustments() throws StockManagementException;

	public Adjustment getAdjustmentById(long adjustmentId) throws StockManagementException;

	public List<AdjustmentDetail> getAdjustmentDetails(long adjustmentId) throws StockManagementException;

	List<Adjustment> getAdjustmentsByCriteria(AdjustmentCriteria criteria) throws StockManagementException;
}
