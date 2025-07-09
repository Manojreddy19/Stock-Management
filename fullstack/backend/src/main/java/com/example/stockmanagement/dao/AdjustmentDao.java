package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentDetail;
import com.example.stockmanagement.utilities.Status;

public interface AdjustmentDao {

	boolean addAdjustmentDetails(AdjustmentDetail adjustment);

	List<AdjustmentDetail> getAdjustmentDetails(int adjustmentId);

	boolean updateGeneratedBatchId(long adjustmentId, long batchId, long generatedBid);

	boolean addAdjustment(Adjustment adjustmentHeader);

	boolean updateAdjustment(int adjustmentId, Status status, String modifiedBy);

	List<Adjustment> getAdjustments();

}
