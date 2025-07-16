package com.example.stockmanagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	public long addAdjustment(Adjustment adjustment) throws StockManagementException {
		MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustment);
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			namedParameterJdbcTemplate.update(INSERT_INTO_ADJUSTMENT_HEADER, params, keyHolder);
			return keyHolder.getKey().longValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new StockManagementException("Data not added");

	}

	@Override
	public void updateAdjustment(Long adjustmentId, Status status, String modifiedBy,String remarks) throws StockManagementException {
		try {
			MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustmentId, status,
					modifiedBy,remarks);
			int updatedRows = namedParameterJdbcTemplate.update(UPDATE_STATUS_AND_MODIFIEDBY, params);
			if (updatedRows > 0)
				return;

		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StockManagementException("Cannot update Adjustment");
	}

	@Override
	public List<Adjustment> getAdjustments() throws StockManagementException {
		try {
			List<Adjustment> getAllAdjustments = namedParameterJdbcTemplate.query(SELECT_ALL_ADJUSTMENTS,
					new AdjustmentRowMapper());
			if (!getAllAdjustments.isEmpty())
				return getAllAdjustments;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		throw new StockManagementException("No adjustments to show");
	}

	@Override
	public void addAdjustmentDetails(long id, List<AdjustmentDetail> adjustmentsToBeAdded)
			throws StockManagementException {
		try {
			for (AdjustmentDetail detail : adjustmentsToBeAdded) {
				detail.setAdjustmentId(id);
				MapSqlParameterSource mapper = StaticHelperForAdjustment.getParamsToAdjustmentId(detail);
				int updatedRows = namedParameterJdbcTemplate.update(INSERT_INTO_ADJUSTMENT_DETAIL, mapper);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new StockManagementException("Error in DAO");
		}
	}

	@Override
	public void updateGeneratedBatchId(Long adjustmentId, Long batchId, Long generatedBid)
			throws StockManagementException {
		try {
			MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToUpdateOnApproval(adjustmentId, batchId,
					generatedBid);
			int updatedRows = namedParameterJdbcTemplate.update(UPDATE_GENERATEDBID, params);


		} catch (Exception e) {
			e.printStackTrace();
			throw new StockManagementException("Cannot update Generated Batch Id");
		}

	}

	@Override
	public List<AdjustmentDetail> getAdjustmentDetails(long adjustmentId) throws StockManagementException {
		try {
			MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToAdjustmentId(adjustmentId);
			List<AdjustmentDetail> getAllAdjustmentDetails = namedParameterJdbcTemplate.query(SELECT_ALL_ADJUSTMENT_DETAILS,
					params, new AdjustmentDetailRowMapper());
				return getAllAdjustmentDetails;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		throw new StockManagementException("No adjustment Details to show");
	}

	@Override
	public Adjustment getAdjustmentById(long adjustmentId) throws StockManagementException {
		try {
			MapSqlParameterSource params = StaticHelperForAdjustment.getParamsToAdjustmentId(adjustmentId);
			return namedParameterJdbcTemplate.queryForObject(SELECT_ALL_ADJUSTMENTS_BY_ID, params,
					new AdjustmentRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			throw new StockManagementException("Error In dao No Adjustments found");
		}
	}

}