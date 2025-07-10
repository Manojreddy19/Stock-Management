package com.example.stockmanagement.dao.impl;

import java.util.List;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.example.stockmanagement.dao.StockDao;
import com.example.stockmanagement.dao.queries.StockQueries;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;
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
	public Long insertAndSendBackBId(StockMaster stock) throws Exception {

		String sql = INSERT_STOCK;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = sotckParameterMapper.mapStockParameters(stock);
		int rowsAffected = namedParameterJdbcTemplate.update(sql, params,keyHolder);
		
		if (rowsAffected > 0) {
			return keyHolder.getKey().longValue();
		}
		throw new Exception("Failed to insert stock record for ProductId: " + stock.getProductId());
	}

	public Integer getQunatityById(Long bId) throws Exception {
		String sql = GET_QUANTITY_BY_ID;
		MapSqlParameterSource param  = sotckParameterMapper.mapStockIdParameter( bId);
		Integer quantity = namedParameterJdbcTemplate.queryForObject(sql,param, Integer.class);
		if (quantity != null) {
			return quantity;
		}	
		
		throw new Exception("No stock found with BatchId: " + bId);
	}

	@Override
	public String modifyStockQuantityByBId(Long bId, int qunatity,String modifiedBy) throws Exception {
		String sql = MODIFY_STOCK_QUANTITY_BY_ID;
		MapSqlParameterSource params = sotckParameterMapper.mapStockUpdateParameters(bId, qunatity,modifiedBy);
		int flag = namedParameterJdbcTemplate.update(sql, params);
		if (flag > 0) {
			return "stock is modified succesfully!";
		}
		throw new Exception("Failed to modify stock quantity for BatchId: " + bId);
	}

	@Override
	public List<StockMaster> getAllStocksWithPostiveQuantity() throws Exception {
		String sql = GET_ALL_STOCKS_WITH_POSITIVE_QUANTITY;
		List<StockMaster> stocks = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(),new StockMapper());
		if (stocks != null && !stocks.isEmpty()) {
			return stocks;
		}
		throw new Exception("no stocks found with positive quantity");

	}

	@Override
	public String addStockTrack(StockTrack stocktrack) throws Exception {
		
		String sql= INSERT_INTO_STACK_TRACK;
		MapSqlParameterSource params = sotckParameterMapper.mapStockTrackParameters(stocktrack);
		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		if (rowsAffected > 0) {
			return "stock track is added";
		}
		throw new Exception("Failed to insert stockTrack ");
	}

}
