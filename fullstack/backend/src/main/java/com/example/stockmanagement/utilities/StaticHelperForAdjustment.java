package com.example.stockmanagement.utilities;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
import com.example.stockmanagement.domain.AdjustmentDetail;

public class StaticHelperForAdjustment {
	public static MapSqlParameterSource getParamsToUpdateOnApproval(Long adjustmentId, Status status,
			String modifiedBy, String remarks) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AdjustmentId", adjustmentId);
		System.out.println("Status Value " + status.getValue());
		params.addValue("Status", String.valueOf(status.getValue()));
		params.addValue("ModifiedBy", modifiedBy);
		params.addValue("Remarks", remarks);
		return params;
	}

	public static MapSqlParameterSource getParamsToUpdateOnApproval(Long adjustmentId, Long batchId,
			Long generatedBid) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AdjustmentId", adjustmentId);
		params.addValue("BatchId", batchId);
		params.addValue("GeneratedBid", generatedBid);
		return params;
	}

	public static MapSqlParameterSource getParamsToUpdateOnApproval(Adjustment adjustment) {
		System.out.println(adjustment.getAdjustmentType().getValue());
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("adjustmentType", String.valueOf(adjustment.getAdjustmentType().getValue()));
		params.addValue("Amount", adjustment.getAmount());
		params.addValue("Status", "O");
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

	public static MapSqlParameterSource getParamsToAdjustmentId(AdjustmentDetail detail) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AdjustmentId", detail.getAdjustmentId());
		params.addValue("ProductId", detail.getProductId());
		params.addValue("Batch", detail.getBatch());
		params.addValue("BatchId", detail.getBatchId());
		params.addValue("Quantity", detail.getQuantity());
		params.addValue("Mrp", detail.getMrp());
		params.addValue("ExpiryDate", detail.getExpiryDate());
		params.addValue("Amount", detail.getAmount());
		return params;
	}
	
	public static String queryForGetAdjustmentCount(String sql,AdjustmentCriteria adjustmentCriteria)
	{
		sql+="WHERE AdjustmentType="+String.valueOf( adjustmentCriteria.getAdjustmentType().getValue());
		sql+="AND Status="+String.valueOf(adjustmentCriteria.getStatus().getValue()) ;
		if(adjustmentCriteria.getAdjustmentId()!=null)
		{
			sql+="AND AdjustmentId="+ String.valueOf(adjustmentCriteria.getAdjustmentId());
		}
		if(adjustmentCriteria.getCreatedFrom()!=null && adjustmentCriteria.getCreatedTo()!=null)
		{
			sql+="AND CreatedAT BETWEEN "+adjustmentCriteria.getCreatedFrom()
			+" And "+adjustmentCriteria.getCreatedTo();
		}

		
		
		return sql+";";
	}

}