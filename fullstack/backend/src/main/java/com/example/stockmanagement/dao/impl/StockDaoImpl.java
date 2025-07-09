package com.example.stockmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.stockmanagement.dao.StockDao;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;

@Repository
public class StockDaoImpl implements StockDao {

	@Override
	public Long insertAndSendBackBId(StockMaster stock) {
		
		return null;
	}

	@Override
	public Long getQunatityById(Long bId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean modifyStockQuantityByBId(Long bId, int qunatity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockMaster> getAllStocksWithPostiveQuantity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addStock(StockTrack stocktrack) {
		// TODO Auto-generated method stub
		return false;
	}

}
