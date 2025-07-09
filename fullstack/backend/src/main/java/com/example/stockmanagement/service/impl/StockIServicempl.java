package com.example.stockmanagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.stockmanagement.dao.AdjustmentDao;
import com.example.stockmanagement.dao.StockDao;
import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentDetail;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.service.StockService;

@Service
public class StockIServicempl implements StockService {

	@Autowired
	private StockDao stockDao;
	@Autowired
	private AdjustmentDao adjustmentDao;

	@Override
	@Transactional
	public boolean stockUp(Adjustment adjustmentHeader) {
		List<AdjustmentDetail> adjustmentDetails = adjustmentHeader.getAdjustmentDetails();
		for (AdjustmentDetail detail : adjustmentDetails) {
			long generatedId = stockDao.insertAndSendBackBId(new StockMaster(detail.getProductId(), detail.getBatch(),
					null, detail.getQuantity(), detail.getExpirydate(), detail.getMrp(),
					adjustmentHeader.getCreatedBy(), new Date(), "Admin1", new Date()));
			

			adjustmentDao.updateGeneratedBatchId(adjustmentHeader.getAdjustmentId(), detail.getBatchId(), generatedId);

		}
		return true;
	}

	@Override
	public boolean stockDown(Adjustment adjustmentHeader) {
		return false;
	}

	@Override
	public List<StockMaster> getAllStocks() {
		return null;
	}

}
