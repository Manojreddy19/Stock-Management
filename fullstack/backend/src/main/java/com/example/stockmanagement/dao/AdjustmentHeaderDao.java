package com.example.stockmanagement.dao;

import com.example.stockmanagement.domain.AdjustmentHeader;

public interface AdjustmentHeaderDao {
	boolean addAdjustment(AdjustmentHeader adjustmentHeader);
	
	boolean updateAdjustment();
	
}