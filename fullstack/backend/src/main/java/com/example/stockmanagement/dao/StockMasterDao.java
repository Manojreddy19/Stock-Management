package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.StockMaster;

public interface StockMasterDao {
	public int InsertAndSendBackBId(StockMaster stock);
	
	public int getQunatityById(long bId);

	public boolean modifyStockQuantityByBId(long bId,int qunatity);

	public List<StockMaster> getAllStocksWithPostiveQuantity();
}