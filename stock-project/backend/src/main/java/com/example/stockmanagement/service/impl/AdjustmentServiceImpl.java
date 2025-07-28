package com.example.stockmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.stockmanagement.dao.AdjustmentDao;
import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
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
	public long getAdjustmentsCount(AdjustmentCriteria adjustmentCriteria) {
		try {	
			return adjustmentDao.getAdjustmentCount(adjustmentCriteria);			
		}catch(StockManagementException e)
		{
			throw new StockManagementException(e.getMessage());
		}

	}
	@Override
	public List<Adjustment> getAdjustmentsByCriteria(AdjustmentCriteria criteria) throws StockManagementException {

		try {

			List<Adjustment> adjustments = adjustmentDao.getAdjustmentsByCriteria(criteria);
//			System.out.println("adjustments: " + adjustments);
			System.out.println("The adjustments count By size() attribute: " + adjustments.size());
			System.out.println("The adjustments count By getAdjustmentsCount() method: " + getAdjustmentsCount(criteria));
			if (adjustments.size() == 0) {
				throw new StockManagementException("no Adjusmtents are found");
			}
			if (criteria.isDetailsRequired()) {
				for (Adjustment adjustment : adjustments) {
					adjustment.setAdjustmentDetails(adjustmentDao.getAdjustmentDetails(adjustment.getAdjustmentId()));
				}
			}

			return adjustments;
		} catch (Exception e) {
			throw new StockManagementException(e.getMessage());
		}
	}
	@Override
	@Transactional
	public void approveAdjustment(Long adjustmentId, String modifiedBy) {

		try {
			Adjustment adjustment = adjustmentDao.getAdjustmentById(adjustmentId);
			adjustmentDao.updateAdjustment(adjustmentId, Status.ACCEPT, modifiedBy, "");
			adjustment.setModifiedBy(modifiedBy);
			adjustment.setAdjustmentDetails(adjustmentDao.getAdjustmentDetails(adjustmentId));
			if (adjustment.getAdjustmentType() == AdjustmentType.UP) {
				stockService.stockUp(adjustment);
			} else {
				stockService.stockDown(adjustment);
			}

		} catch (Exception e) {
			throw new StockManagementException(e.getMessage());
		}

	}

	@Override
	public void withdrawAdjustment(Long adjustmentId, String modifiedBy, String remarks) {
		adjustmentDao.updateAdjustment(adjustmentId, Status.REJECT, modifiedBy, remarks);
	}

	

}