package com.example.stockmanagement.service;

import java.util.List;

import com.example.stockmanagement.domain.AdjustmentHeader;
import com.example.stockmanagement.domain.StockMaster;

public interface StockService {

	public boolean stockUp(AdjustmentHeader adjustmentHeader);

	public boolean stockDown(AdjustmentHeader adjustmentHeader);

	public boolean updateStatus(int adjustmentId, char status);

	public List<AdjustmentHeader> getAdjustmentHeaders();

	public List<StockMaster> getAllStocks();

}