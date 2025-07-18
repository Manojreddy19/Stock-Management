package com.example.stockmanagement.service;

import java.util.List;
import java.util.Map;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.exception.StockManagementException;

public interface StockService  {

	public void stockUp(Adjustment adjustmentHeader) throws StockManagementException;

	public void stockDown(Adjustment adjustmentHeader) throws StockManagementException;

	public List<StockMaster> getAllStocks() throws StockManagementException;

	Map<Long, String> getBatches(String productId) throws StockManagementException;

}