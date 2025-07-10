package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;

public interface StockDao {
	public Long insertAndSendBackBId(StockMaster stock) throws Exception;

	public Integer getQunatityById(Long bId) throws Exception;

	public void modifyStockQuantityByBId(Long bId, int qunatity, String modifiedBy) throws Exception;

	public List<StockMaster> getAllStocksWithPostiveQuantity() throws Exception;

	public void addStockTrack(StockTrack stocktrack) throws Exception;

}
