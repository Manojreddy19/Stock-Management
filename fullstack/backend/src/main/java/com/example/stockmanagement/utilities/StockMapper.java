package com.example.stockmanagement.utilities;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.example.stockmanagement.domain.StockMaster;

public class StockMapper implements RowMapper<StockMaster> {

	 @Override
	 public StockMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		 StockMaster stock = new StockMaster();
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
	 }}
