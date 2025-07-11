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

		char adj = rs.getString("AdjustmentType").charAt(0);
		if (adj == 'U') {
			adjustment.setAdjustmentType(AdjustmentType.valueOf("UP"));


		} else {
			adjustment.setAdjustmentType(AdjustmentType.valueOf("DOWN"));

		}
		char sta = rs.getString("Status").charAt(0);
		if (sta == 'O') {
			adjustment.setStatus(Status.OPEN);

		} else if (sta == 'A') {
			adjustment.setStatus(Status.ACCEPT);

		} else {
			adjustment.setStatus(Status.REJECT);

		}

		adjustment.setAmount(rs.getDouble("Amount"));

		adjustment.setRemarks(rs.getString("Remarks"));
		adjustment.setCreatedBy(rs.getString("CreatedBy"));
		adjustment.setCreatedAt(rs.getTimestamp("CreatedAt"));
		adjustment.setModifiedBy(rs.getString("ModifiedBy"));
		adjustment.setModifiedAt(rs.getTimestamp("ModifiedAt"));
		return adjustment;
	}
}