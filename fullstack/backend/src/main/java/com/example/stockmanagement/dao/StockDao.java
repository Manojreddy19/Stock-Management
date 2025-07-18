package com.example.stockmanagement.dao;

import java.util.List;
import java.util.Map;

import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;
import com.example.stockmanagement.exception.StockManagementException;

public interface StockDao {
	public Long insertAndSendBackBId(StockMaster stock) throws StockManagementException;

	public Integer getQunatityById(Long bId) throws StockManagementException;

	public void modifyStockQuantityByBId(Long bId, int qunatity, String modifiedBy) throws StockManagementException;

	public List<StockMaster> getAllStocks() throws StockManagementException;

	public void addStockTrack(StockTrack stocktrack) throws StockManagementException;

	 public Map<Long, String> getProductBatches(String productId) throws StockManagementException;

}