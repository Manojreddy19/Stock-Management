package com.example.stockmanagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.stockmanagement.dao.StockDao;
import com.example.stockmanagement.dao.queries.StockQueries;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.utilities.StockMapper;
import com.example.stockmanagement.utilities.StockParameterMapper;

@Repository
public class StockDaoImpl extends StockQueries implements StockDao {
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	StockParameterMapper stockParameterMapper;

	@Override
	public Long insertAndSendBackBId(StockMaster stock) throws StockManagementException {
		try {
			String sql = INSERT_STOCK;
			KeyHolder keyHolder = new GeneratedKeyHolder();
			MapSqlParameterSource params = stockParameterMapper.mapStockParameters(stock);
			int rowsAffected = namedParameterJdbcTemplate.update(sql, params, keyHolder);

			if (rowsAffected > 0) {
				return keyHolder.getKey().longValue();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StockManagementException("Failed to insert stock record for ProductId: " + stock.getProductId());
	}

	public Integer getQunatityById(Long bId) throws StockManagementException {
		try {
			String sql = GET_QUANTITY_BY_ID;
			MapSqlParameterSource param = stockParameterMapper.mapStockIdParameter(bId);

			Integer quantity = namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
			if (quantity != null) {
				return quantity;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StockManagementException("No stock found with BatchId: " + bId);
	}

	@Override
	public void modifyStockQuantityByBId(Long bId, int qunatity, String modifiedBy) throws StockManagementException {
		try {
			String sql = MODIFY_STOCK_QUANTITY_BY_ID;
			MapSqlParameterSource params = stockParameterMapper.mapStockUpdateParameters(bId, qunatity, modifiedBy);
			int flag = namedParameterJdbcTemplate.update(sql, params);
			if (flag > 0) {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StockManagementException("Failed to modify stock quantity for BatchId: " + bId);
	}

	@Override
	public List<StockMaster> getAllStocks() throws StockManagementException {
		String sql = GET_ALL_STOCKS_WITH_POSITIVE_QUANTITY;
		List<StockMaster> stocks = null;
		try {
			stocks = namedParameterJdbcTemplate.query(sql, new StockMapper());
			return stocks;

		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StockManagementException("no stocks found with positive quantity");

	}

	@Override
	public void addStockTrack(StockTrack stocktrack) throws StockManagementException {

		try {
			String sql = INSERT_INTO_STOCK_TRACK;
			MapSqlParameterSource params = stockParameterMapper.mapStockTrackParameters(stocktrack);
			int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
			if (rowsAffected > 0) {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StockManagementException("Failed to insert stockTrack ");
	}

}