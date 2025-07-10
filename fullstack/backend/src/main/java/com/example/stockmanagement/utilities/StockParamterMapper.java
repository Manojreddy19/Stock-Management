package com.example.stockmanagement.utilities;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.example.stockmanagement.domain.StockMaster;
@Configuration
public class StockParamterMapper {

	public MapSqlParameterSource mapStockToParameters(StockMaster stock) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("ProductId", stock.getProductId());
		params.addValue("Batch", stock.getBatch());
		params.addValue("BatchId", stock.getBatchId());
		params.addValue("Quantity", stock.getQuantity());
		params.addValue("ExpiryDate", stock.getExpiryDate());
		params.addValue("Mrp", stock.getMrp());
		params.addValue("CreatedBy", stock.getCreatedBy());
		return params;
	}
	public MapSqlParameterSource mapStockUpdateParameters(Long batchId, int quantity) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("BatchId", batchId);
		params.addValue("Quantity", quantity);
		//params.addValue("ModifiedBy", session.getAttribute("username"));
		return params;
	}
	public MapSqlParameterSource mapStockIdParameter(Long batchId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("BatchId", batchId);
		return params;
	}
	
	
}
