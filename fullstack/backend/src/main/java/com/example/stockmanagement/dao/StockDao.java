package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;

public interface StockDao {
	public Long insertAndSendBackBId(StockMaster stock);

	public Long getQunatityById(Long bId);

	public Boolean modifyStockQuantityByBId(Long bId, int qunatity);

	public List<StockMaster> getAllStocksWithPostiveQuantity();

	boolean addStock(StockTrack stocktrack);

}
