package com.example.stockmanagement.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.stockmanagement.domain.StockDetail;

public class StockMapper implements RowMapper<StockDetail> {

	@Override
	public StockDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockDetail stock = new StockDetail();
		stock.setProductId(rs.getString("ProductId"));
		stock.setBatch(rs.getString("Batch"));
		stock.setBatchId(rs.getLong("BatchId"));
		stock.setQuantity(rs.getInt("Quantity"));
		stock.setExpiryDate(rs.getDate("ExpiryDate"));
		stock.setMrp(rs.getDouble("Mrp"));
		stock.setCreatedBy(rs.getString("CreatedBy"));
		stock.setCreatedAt(rs.getTimestamp("CreatedAt"));
		stock.setModifiedBy(rs.getString("ModifiedBy"));
		stock.setModifiedAt(rs.getTimestamp("ModifiedAt"));
		return stock;
	}
}