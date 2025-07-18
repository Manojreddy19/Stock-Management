package com.example.stockmanagement.dao;

import java.util.List;
import java.util.Map;

import com.example.stockmanagement.domain.StockDetail;
import com.example.stockmanagement.domain.StockTrack;
import com.example.stockmanagement.exception.StockManagementException;

public interface StockDao {
	public Long insertAndSendBackBId(StockDetail stock) throws StockManagementException;

	public Integer getQunatityById(Long bId) throws StockManagementException;

	public void modifyStockQuantityByBId(Long bId, int qunatity, String modifiedBy) throws StockManagementException;

	public List<StockDetail> getAllStocks() throws StockManagementException;

	public void addStockTrack(StockTrack stocktrack) throws StockManagementException;
	
	public StockDetail getStockDetail(String productId, Long batchId) throws StockManagementException;
	
	public Map<Long, String> getProductBatches(String productId) throws StockManagementException;
}