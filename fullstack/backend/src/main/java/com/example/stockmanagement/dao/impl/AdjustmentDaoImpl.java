package com.example.stockmanagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.example.stockmanagement.dao.AdjustmentDao;
import com.example.stockmanagement.dao.queries.AdjustmentQueries;
import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentDetail;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.utilities.AdjustmentDetailRowMapper;
import com.example.stockmanagement.utilities.AdjustmentRowMapper;
import com.example.stockmanagement.utilities.StaticHelperForAdjustment;
import com.example.stockmanagement.utilities.Status;

@Repository
public class AdjustmentDaoImpl extends AdjustmentQueries implements AdjustmentDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void addAdjustment(Adjustment adjustment) throws StockManagementException {
		System.out.println("In adjustment Header");
		int insertedCount=0;
		try {
			MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustment);
			insertedCount = namedParameterJdbcTemplate.update(INSERT_INTO_ADJUSTMENT_HEADER, params);

			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(insertedCount > 0) return;
		throw new StockManagementException("Cannot add Adjustment");
	}

	@Override
	public void updateAdjustment(Long adjustmentId, Status status, String modifiedBy) throws StockManagementException {
		MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustmentId, status, modifiedBy);
		int updatedRows = namedParameterJdbcTemplate.update(UPDATE_STATUS_AND_MODIFIEDBY, params);
		if(updatedRows > 0) return;
		throw new StockManagementException("Cannot update Adjustment");
	}

	@Override
	public List<Adjustment> getAdjustments() throws StockManagementException {
		List<Adjustment> getAllAdjustments = namedParameterJdbcTemplate.query(SELECT_ALL_ADJUSTMENTS, new AdjustmentRowMapper());
		if(!getAllAdjustments.isEmpty()) return getAllAdjustments;
		throw new StockManagementException("No adjustments to show");
	}

	@Override
	public void addAdjustmentDetails(List<AdjustmentDetail> adjustmentsToBeAdded) throws StockManagementException {
		System.out.println("In Adjustment Details");
		try {
			SqlParameterSource[] listOfAdjustmentDetail = SqlParameterSourceUtils.createBatch(adjustmentsToBeAdded.toArray());
			int[] insertedCount = namedParameterJdbcTemplate.batchUpdate(INSERT_INTO_ADJUSTMENT_DETAIL, listOfAdjustmentDetail);

			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new StockManagementException("");
		}
		//throw new StockManagementException("Adjustment Details Not added");
	}
	
	@Override
	public void updateGeneratedBatchId(Long adjustmentId, Long batchId, Long generatedBid) throws StockManagementException {
		MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustmentId, batchId, generatedBid);
		int updatedRows = namedParameterJdbcTemplate.update(UPDATE_GENERATEDBID, params);
		if(updatedRows > 0) return;
		throw new StockManagementException("Cannot update Generated Batch Id");
	}

	@Override
	public List<AdjustmentDetail> getAdjustmentDetails(long adjustmentId) throws StockManagementException {
		List<AdjustmentDetail> getAllAdjustmentDetails = namedParameterJdbcTemplate.query(SELECT_ALL_ADJUSTMENT_DETAILS, new AdjustmentDetailRowMapper());
		if(!getAllAdjustmentDetails.isEmpty()) return getAllAdjustmentDetails;
		throw new StockManagementException("No adjustment Details to show");
	}

	@Override
	public Adjustment getAdjustmentById(long adjustmentId) throws StockManagementException{
		
		MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToAdjustmentId(adjustmentId);
		return namedParameterJdbcTemplate.queryForObject(SELECT_ALL_ADJUSTMENTS_BY_ID,params,new AdjustmentRowMapper());
}
	
}