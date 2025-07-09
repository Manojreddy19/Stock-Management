package com.example.stockmanagement.dao;

import java.util.List;
import com.example.stockmanagement.domain.AdjustmentDetail;

public interface AdjustmentDetailsDao {
	boolean addAdjustmentDetails(AdjustmentDetail adjustment);
	List<AdjustmentDetail> getAdjustmentDetails(int adjustmentId);
}