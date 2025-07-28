package com.example.stockmanagement.utilities;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
import com.example.stockmanagement.domain.AdjustmentDetail;

public class StaticHelperForAdjustment {
	public static MapSqlParameterSource getParamsToUpdateOnApproval(Long adjustmentId, Status status, String modifiedBy,
			String remarks) {
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

	public static MapSqlParameterSource getParamsToAdjustmentCount(AdjustmentCriteria criteria) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AdjustmentType", String.valueOf(criteria.getAdjustmentType().getValue()));

		if (criteria.getStatus() != null) {
			params.addValue("statusFlag", 1);
			params.addValue("Status", String.valueOf(criteria.getStatus().getValue()));

		} else {
			params.addValue("statusFlag", 0);
			params.addValue("Status", 1);

		}

		if (criteria.getAdjustmentId() != null) {
			params.addValue("idFlag", 1);

		} else {
			params.addValue("idFlag", 0);
			params.addValue("AdjustmentId", 1);

		}
		params.addValue("AdjustmentId", criteria.getAdjustmentId());

		if (criteria.getCreatedFrom() != null) {
			params.addValue("createdFromFlag", 1);

		} else {
			params.addValue("createdFromFlag", 0);

		}
		params.addValue("from", criteria.getCreatedFrom());

		if (criteria.getCreatedTo() != null) {
			params.addValue("createdToFlag", 1);

		} else {
			params.addValue("createdToFlag", 0);

		}
		params.addValue("to", criteria.getCreatedTo());

		return params;

	}

	public static MapSqlParameterSource getParamForSearchCriteria(AdjustmentCriteria criteria) {
		MapSqlParameterSource params = new MapSqlParameterSource();

		// AdjustmentType (mandatory)
		String type = String.valueOf(criteria.getAdjustmentType().getValue());
		params.addValue("AdjustmentType", type);

		// AdjustmentId
		if (criteria.getAdjustmentId() != null) {
			params.addValue("adjustmentIdFlag", 1);
			params.addValue("AdjustmentId", criteria.getAdjustmentId());
		} else {
			params.addValue("adjustmentIdFlag", 0);
			params.addValue("AdjustmentId", -1); // dummy
		}

		// CreatedFrom
		if (criteria.getCreatedFrom() != null) {
			params.addValue("createdFromFlag", 1);
			params.addValue("CreatedFrom", criteria.getCreatedFrom());
		} else {
			params.addValue("createdFromFlag", 0);
			params.addValue("CreatedFrom", new java.util.Date(0)); // dummy (e.g. 1970)
		}

		// CreatedTo
		if (criteria.getCreatedTo() != null) {
			params.addValue("createdToFlag", 1);
			params.addValue("CreatedTo", criteria.getCreatedTo());
		} else {
			params.addValue("createdToFlag", 0);
			params.addValue("CreatedTo", new java.util.Date()); // now (safe dummy)
		}

		// Status
		if (criteria.getStatus() != null) {
			params.addValue("statusFlag", 1);
			params.addValue("Status", String.valueOf(criteria.getStatus().getValue()));
		} else {
			params.addValue("statusFlag", 0);
			params.addValue("Status", ""); // dummy
		}

		// Pagination: LimitFrom and NoOfRows
		params.addValue("LimitFrom", criteria.getLimitFrom() != null ? criteria.getLimitFrom() : 0);
		params.addValue("NoOfRows", criteria.getNoOfRows() != null ? criteria.getNoOfRows() : 10);

		return params;
	}

	public static MapSqlParameterSource getParamsForProductIds(Boolean isStockRequired) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		int flag=1;
		if(isStockRequired) {
			flag=0;
		}
		
		params.addValue("flag", flag);
		return params;
	}

}