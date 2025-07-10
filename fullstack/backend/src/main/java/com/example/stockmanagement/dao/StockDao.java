package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;

public interface StockDao {
	public Long insertAndSendBackBId(StockMaster stock);

	public Long getQunatityById(Long bId) throws Exception;

	public Boolean modifyStockQuantityByBId(Long bId, int qunatity) throws Exception;

	public List<StockMaster> getAllStocksWithPostiveQuantity() throws Exception;

	boolean addStock(StockTrack stocktrack);

}
