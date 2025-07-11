package com.example.stockmanagement.utilities;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.example.stockmanagement.domain.Adjustment;

public class StaticHelperForAdjustment {
	public static MapSqlParameterSource getParamsToUpdateOnApproval(Long adjustmentId, Status status, String modifiedBy) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AdjustmentId", adjustmentId);
		System.out.println("Status Value "+status.getValue());
		params.addValue("Status", String.valueOf(status.getValue()));
		params.addValue("ModifiedBy", modifiedBy);
		return params;
	}

	public static MapSqlParameterSource getParamsToUpdateOnApproval(Long adjustmentId, Long batchId, Long generatedBid) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AdjustmentId", adjustmentId);
		params.addValue("BatchId", batchId);
		params.addValue("GeneratedBid", generatedBid);
		return params;
	}

	public static MapSqlParameterSource getParamsToUpdateOnApproval(Adjustment adjustment) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AdjustmentType",  String.valueOf(adjustment.getAdjustmentType().getValue()));
		params.addValue("Amount", adjustment.getAmount());
		params.addValue("Status",  String.valueOf(adjustment.getStatus().getValue()));
		params.addValue("Remarks", adjustment.getRemarks());
		params.addValue("CreatedBy", adjustment.getCreatedBy());
		params.addValue("ModifiedBy", adjustment.getModifiedBy());
		return params;
	}
	public static MapSqlParameterSource getParamsToAdjustmentId(Long adjustmentId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("adjustmentId", adjustmentId);
		return params;
	}

}