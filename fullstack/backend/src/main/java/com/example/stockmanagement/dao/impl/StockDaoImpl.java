package com.example.stockmanagement.dao.impl;

import java.util.List;
import java.util.Map;

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
import com.example.stockmanagement.utilities.ProductIdRowMapper;
import com.example.stockmanagement.utilities.StaticHelperForAdjustment;
import com.example.stockmanagement.utilities.StockDetailsExtractor;
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

	@Override
	public Map<Long, String> getProductBatches(String productId) throws StockManagementException {
		String sqlString = GET_BATCH_AND_BATCHID_BY_PID;
		Map<Long, String> batches = null;
		try {
			MapSqlParameterSource params = stockParameterMapper.mapProductIdParameter(productId);
			batches = namedParameterJdbcTemplate.query(sqlString, params, new StockDetailsExtractor());
			if (batches == null) {
				throw new StockManagementException("no batches with the given productid");
			}
			return batches;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StockManagementException(e.getMessage());
		}
	}

	@Override
	public List<String> getProductIds(boolean isStockRequired,String productId) throws StockManagementException {
		try {
			MapSqlParameterSource params = StaticHelperForAdjustment.getParamsForProductIds(isStockRequired, productId);
			List<String> productIds = namedParameterJdbcTemplate.query(SELECT_ALL_PRODUCT_IDS, params,
					new ProductIdRowMapper());
			return productIds;

		} catch (Exception e) {
			e.printStackTrace();
			throw new StockManagementException("Error in fetching product ids");
		}

	}

	@Override
	public StockMaster getStockDetail(String productId, Long batchId) throws StockManagementException {
		MapSqlParameterSource params = stockParameterMapper.mapProductIdAndBatchIdParameter(productId, batchId);
		StockMaster stock = null;
		try {
			stock = namedParameterJdbcTemplate.queryForObject(SELECT_STOCK_DETAIL_BY_PID_AND_BID, params,
					new StockMapper());
		} catch (StockManagementException e) {
			e.printStackTrace();
		}
		if (stock == null)
			throw new StockManagementException(
					"No Stock Found for product Id: " + productId + " and BatchId: " + batchId);
		return stock;
	}

}