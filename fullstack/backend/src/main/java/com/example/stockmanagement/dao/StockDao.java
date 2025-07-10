package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;

public interface StockDao {
	public Long insertAndSendBackBId(StockMaster stock) throws Exception;

	public int getQunatityById(Long bId) throws Exception;

	public Boolean modifyStockQuantityByBId(Long bId, int qunatity,String modifiedBy) throws Exception;

	public List<StockMaster> getAllStocksWithPostiveQuantity() throws Exception;

	boolean addStock(StockTrack stocktrack);

}
