package com.example.stockmanagement.service;

import java.util.List;


import com.example.stockmanagement.domain.Adjustment;

import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.exception.StockManagementException;

public interface StockService  {

	public String stockUp(Adjustment adjustmentHeader) throws StockManagementException;

	public boolean stockDown(Adjustment adjustmentHeader) throws StockManagementException;

	public List<StockMaster> getAllStocks() throws StockManagementException;

}