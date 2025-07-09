package com.example.stockmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.stockmanagement.dao.AdjustmentDao;
import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentDetail;
import com.example.stockmanagement.utilities.Status;

@Repository
public class AdjustmentDaoImpl implements AdjustmentDao{

	@Override
	public boolean addAdjustmentDetails(AdjustmentDetail adjustment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AdjustmentDetail> getAdjustmentDetails(int adjustmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateGeneratedBatchId(long adjustmentId, long batchId, long generatedBid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAdjustment(Adjustment adjustmentHeader) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAdjustment(int adjustmentId, Status status, String modifiedBy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Adjustment> getAdjustments() {
		// TODO Auto-generated method stub
		return null;
	}

}
