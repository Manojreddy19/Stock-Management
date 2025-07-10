package com.example.stockmanagement.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.stockmanagement.domain.Adjustment;

public class AdjustmentRowMapper implements RowMapper<Adjustment> {

	@Override
	public Adjustment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Adjustment adjustment = new Adjustment();
		adjustment.setAdjustmentId(rs.getLong("AdjustmentId"));
		adjustment.setAdjustmentType(AdjustmentType.valueOf(rs.getString("AdjustmentType")));
		adjustment.setAmount(rs.getDouble("Amount"));
		adjustment.setStatus(Status.valueOf( rs.getString("Status")));
		adjustment.setRemarks(rs.getString("Remarks"));
		adjustment.setCreatedBy(rs.getString("CreatedBy"));
		adjustment.setCreatedAt(rs.getTimestamp("CreatedAt"));
		adjustment.setModifiedBy(rs.getString("ModifiedBy"));
		adjustment.setModifiedAt(rs.getTimestamp("ModifiedAt"));
		return adjustment;
	}
}