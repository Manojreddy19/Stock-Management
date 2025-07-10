package com.example.stockmanagement.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.stockmanagement.domain.AdjustmentDetail;

public class AdjustmentDetailRowMapper implements RowMapper<AdjustmentDetail> {
	@Override
	public AdjustmentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdjustmentDetail adjustmentDetail = new AdjustmentDetail();
		adjustmentDetail.setAdjustmentId(rs.getLong("AdjustmentId"));
		adjustmentDetail.setProductId(rs.getString("ProductId"));
		adjustmentDetail.setBatch(rs.getString("Batch"));
		adjustmentDetail.setBatchId(rs.getLong("BatchId"));
		adjustmentDetail.setQuantity(rs.getInt("Quantity"));
		adjustmentDetail.setMrp(rs.getDouble("Mrp"));
		adjustmentDetail.setExpiryDate(rs.getDate("ExpiryDate"));
		adjustmentDetail.setAmount(rs.getDouble("Amount"));
		adjustmentDetail.setGeneratedBatchId(rs.getLong("GeneratedBatchId"));
		return adjustmentDetail;
	}
}