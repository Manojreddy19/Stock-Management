package com.example.stockmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.stockmanagement.dao.AdjustmentDao;
import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.service.AdjustmentService;
import com.example.stockmanagement.service.StockService;
import com.example.stockmanagement.utilities.AdjustmentType;
import com.example.stockmanagement.utilities.Status;

@Service
public class AdjustmentServiceImpl implements AdjustmentService {

	@Autowired
	private AdjustmentDao adjustmentDao;
	@Autowired
	private StockService stockService;

	@Override
	@Transactional
	public long addAdjustment(Adjustment adjustment) throws StockManagementException {
		try {
			long id = adjustmentDao.addAdjustment(adjustment);
			adjustmentDao.addAdjustmentDetails(id, adjustment.getAdjustmentDetails());
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StockManagementException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void updateStatus(Long adjustmentId, Status status, String modifiedBy) throws StockManagementException {

		try {
			Adjustment adjustment = adjustmentDao.getAdjustmentById(adjustmentId);
			adjustment.setModifiedBy(modifiedBy);
			if (adjustment.getStatus() != Status.OPEN) {
				throw new StockManagementException("Adjustment Alredy Closed");
			}
			if (status.getValue() == 'A') {
				adjustmentDao.updateAdjustment(adjustmentId, status, modifiedBy);
				if (adjustment.getAdjustmentType() == AdjustmentType.UP) {
					stockService.stockUp(adjustment);
				} else {
					stockService.stockDown(adjustment);
				}
			} else if (status == Status.REJECT) {
				adjustmentDao.updateAdjustment(adjustmentId, status, modifiedBy);
			}
		} catch (Exception e) {
			throw new StockManagementException(e.getMessage());
		}

	}

	@Override
	public List<Adjustment> getAdjustments() throws StockManagementException {

		try {
			List<Adjustment> adjustments = adjustmentDao.getAdjustments();
			for (Adjustment adj : adjustments) {
				adj.setAdjustmentDetails(adjustmentDao.getAdjustmentDetails(adj.getAdjustmentId()));
			}
			return adjustments;
		} catch (Exception e) {
			throw new StockManagementException(e.getMessage());
		}

	}
}