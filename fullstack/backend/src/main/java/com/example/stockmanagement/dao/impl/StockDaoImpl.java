package com.example.stockmanagement.dao.impl;

import java.util.List;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.stockmanagement.dao.StockDao;
import com.example.stockmanagement.dao.queries.StockQueries;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.utilities.StockMapper;
import com.example.stockmanagement.utilities.StockParamterMapper;

public class StockDaoImpl extends StockQueries implements StockDao  {
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	Session session; 
	@Autowired
	StockParamterMapper sotckParameterMapper;

	@Override
	public Long insertAndSendBackBId(StockMaster stock) throws StockManagementException{

		String sql = INSERT_STOCK;
		MapSqlParameterSource params = sotckParameterMapper.mapStockToParameters(stock);
		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		if (rowsAffected > 0) {
			return stock.getBatchId();
		}
		throw new StockManagementException("Failed to insert stock record for ProductId: " + stock.getProductId());
	}



	public int getQunatityById(Long bId) throws StockManagementException{
		String sql = GET_QUANTITY_BY_ID;
		MapSqlParameterSource param  = sotckParameterMapper.mapStockIdParameter( bId);
		Integer quantity = namedParameterJdbcTemplate.queryForObject(sql,param, Integer.class);
		if (quantity != null) {
			return quantity;
		}	
		
		throw new StockManagementException("No stock found with BatchId: " + bId);
	}

	@Override
	public Boolean modifyStockQuantityByBId(Long bId, int qunatity, String modifiedBy) throws StockManagementException{
		String sql = MODIFY_STOCK_QUANTITY_BY_ID;
		MapSqlParameterSource params = sotckParameterMapper.mapStockUpdateParameters(bId, qunatity);
		int flag = namedParameterJdbcTemplate.update(sql, params);
		if (flag > 0) {
			return true;
		}
		throw new StockManagementException("Failed to modify stock quantity for BatchId: " + bId);
	}

	@Override
	public List<StockMaster> getAllStocksWithPostiveQuantity() throws StockManagementException{
		String sql = GET_ALL_STOCKS_WITH_POSITIVE_QUANTITY;
		List<StockMaster> stocks = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(),new StockMapper());
		if (stocks != null && !stocks.isEmpty()) {
			return stocks;
		}
		throw new StockManagementException("no stocks found with positive quantity");

	}

	@Override
	public boolean addStock(StockTrack stocktrack) {
		// TODO Auto-generated method stub
		return false;
	}

}
