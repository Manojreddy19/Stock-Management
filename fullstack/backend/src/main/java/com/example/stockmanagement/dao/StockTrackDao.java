package com.example.stockmanagement.dao;

import java.util.List;

import com.example.stockmanagement.domain.AdjustmentHeader;

public interface StockTrackDao {
	boolean addAdjustment(AdjustmentHeader adjustmentHeader);

	boolean updateAdjustment(int adjustmentId, char status, String modifiedBy);

	List<AdjustmentHeader> getAdjustments();

}
