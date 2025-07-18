package com.example.stockmanagement.service;

import java.util.List;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
import com.example.stockmanagement.exception.StockManagementException;

public interface AdjustmentService {
	public long addAdjustment(Adjustment adjustment) throws StockManagementException;

	public List<Adjustment> getAdjustmentsByCriteria(AdjustmentCriteria criteria) throws StockManagementException;
	
	public List<String> getProductIds(boolean isStockRequired);
	
	public long getAdjustmentsCount(AdjustmentCriteria adjustmentCriteria);

	public void approveAdjustment(Long adjustmentId, String modifiedBy);
	
	public void withdrawAdjustment(Long adjustmentId, String modifiedBy, String remarks) throws StockManagementException;
}
