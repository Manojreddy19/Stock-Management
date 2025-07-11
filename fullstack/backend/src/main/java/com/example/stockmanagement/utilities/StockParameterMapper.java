package com.example.stockmanagement.utilities;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;

@Component
public class StockParameterMapper {

	public MapSqlParameterSource mapStockParameters(StockMaster stock) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productId", stock.getProductId());
		params.addValue("batch", stock.getBatch());
		params.addValue("quantity", stock.getQuantity());
		params.addValue("expiryDate", stock.getExpiryDate());
		params.addValue("mrp", stock.getMrp());
		params.addValue("createdBy", stock.getCreatedBy());
		return params;
	}
	
	public MapSqlParameterSource mapStockTrackParameters(StockTrack stockTrack) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("BatchId", stockTrack.getBatchId());
		params.addValue("TransactionType", String.valueOf( stockTrack.getTransactionType().getValue()));
		params.addValue("Quantity", stockTrack.getQuantity());
		params.addValue("OpenStock", stockTrack.getOpenStock());
		params.addValue("CreatedBy", stockTrack.getCreatedBy());
		return params;
	}

	public MapSqlParameterSource mapStockUpdateParameters(Long batchId, int quantity, String modifiedBy) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("BatchId", batchId);
		params.addValue("quantity", quantity);
		params.addValue("modifiedBy", modifiedBy);
		return params;
	}

	public MapSqlParameterSource mapStockIdParameter(Long batchId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("BatchId", batchId);
		return params;
	}

}