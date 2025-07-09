package com.example.stockmanagement.service;

import java.util.List;

import com.example.stockmanagement.domain.Adjustment;

public interface AdjustmentService {
	
	public boolean AddAdjustment(Adjustment adjustment);

	public boolean updateStatus(int adjustmentId, char status);

	public List<Adjustment> getAdjustmentHeaders();

}
