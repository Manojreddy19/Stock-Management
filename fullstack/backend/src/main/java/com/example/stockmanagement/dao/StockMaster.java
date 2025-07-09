package com.example.stockmanagement.dao;

import java.util.List;

public interface StockMaster {
	
	public int InsertAndSendBackBId(StockMaster stock);
	
	public int getQunatityById(long bId);
	
	public boolean modifyStockQuantityByBId(long bId,int qunatity);
	
	public List<StockMaster> getAllStocksWithPostiveQuantity();

}
