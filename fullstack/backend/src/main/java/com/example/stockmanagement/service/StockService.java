package com.example.stockmanagement.service;

import java.util.List;


import com.example.stockmanagement.domain.Adjustment;

import com.example.stockmanagement.domain.StockMaster;

public interface StockService  {

	public String stockUp(Adjustment adjustmentHeader) throws Exception;

	public boolean stockDown(Adjustment adjustmentHeader) throws Exception;

	public List<StockMaster> getAllStocks() throws Exception;

}