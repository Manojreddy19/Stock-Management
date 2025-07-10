package com.example.stockmanagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import com.example.stockmanagement.dao.AdjustmentDao;
import com.example.stockmanagement.dao.queries.AdjustmentQueries;
import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentDetail;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.service.AdjustmentService;
import com.example.stockmanagement.utilities.AdjustmentDetailRowMapper;
import com.example.stockmanagement.utilities.AdjustmentRowMapper;
import com.example.stockmanagement.utilities.StaticHelperForAdjustment;
import com.example.stockmanagement.utilities.Status;

public class AdjustmentDaoImpl extends AdjustmentQueries implements AdjustmentDao {

	@Autowired
	private AdjustmentService adjustmentService;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void addAdjustment(Adjustment adjustment) throws StockManagementException {
		MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustment);
		int insertedCount = namedParameterJdbcTemplate.update(INSERT_INTO_ADJUSTMENT_HEADER, params);
		throw new StockManagementException("");
	}

	@Override
	public void updateAdjustment(Long adjustmentId, Status status, String modifiedBy) throws StockManagementException {
		MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustmentId, status,
				modifiedBy);
		int updatedRows = namedParameterJdbcTemplate.update(UPDATE_STATUS_AND_MODIFIEDBY, params);
		throw new StockManagementException("");
	}

	@Override
	public List<Adjustment> getAdjustments() throws StockManagementException {
		return namedParameterJdbcTemplate.query(SELECT_ALL_ADJUSTMENTS, new AdjustmentRowMapper());
	}

	@Override
	public void addAdjustmentDetails(List<AdjustmentDetail> adjustmentsToBeAdded) throws StockManagementException {
		SqlParameterSource[] listOfAdjustmentDetail = SqlParameterSourceUtils
				.createBatch(adjustmentsToBeAdded.toArray());
		int[] insertedCount = namedParameterJdbcTemplate.batchUpdate(INSERT_INTO_ADJUSTMENT_DETAIL,
				listOfAdjustmentDetail);
		throw new StockManagementException("");
	}

	@Override
	public void updateGeneratedBatchId(Long adjustmentId, Long batchId, Long generatedBid)
			throws StockManagementException {
		MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustmentId, batchId,
				generatedBid);
		int updatedRows = namedParameterJdbcTemplate.update(UPDATE_BATCHID_AND_GENERATEDBID, params);
		throw new StockManagementException("");
	}

	@Override
	public List<AdjustmentDetail> getAdjustmentDetails(long adjustmentId) throws StockManagementException {
		return namedParameterJdbcTemplate.query(SELECT_ALL_ADJUSTMENT_DETAILS, new AdjustmentDetailRowMapper());
	}

	@Override
	public Adjustment getAdjustmentById(long adjustmentId) throws StockManagementException{
		
		MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToAdjustmentId(adjustmentId);
		return namedParameterJdbcTemplate.queryForObject(SELECT_ALL_ADJUSTMENTS_BY_ID,params,new AdjustmentRowMapper());
}

}