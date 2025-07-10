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
import com.example.stockmanagement.domain.StockTrack;
import com.example.stockmanagement.service.StockService;

@Service
public class StockIServicempl implements StockService {

	@Autowired
	private StockDao stockDao;
	@Autowired
	private AdjustmentDao adjustmentDao;

	@Override
	@Transactional
	public String stockUp(Adjustment adjustmentHeader) throws Exception {

		List<AdjustmentDetail> adjustmentDetails = adjustmentDao
				.getAdjustmentDetails(adjustmentHeader.getAdjustmentId());

		for (AdjustmentDetail detail : adjustmentDetails) {
			StockMaster stock = new StockMaster(detail.getProductId(), detail.getBatch(), null, detail.getQuantity(),
					detail.getExpiryDate(), detail.getMrp(), adjustmentHeader.getCreatedBy(), new Date(), "Admin1",
					new Date());

			int openingStock = stockDao.getQunatityById(detail.getBatchId());

			long generatedId = stockDao.insertAndSendBackBId(stock);

			adjustmentDao.updateGeneratedBatchId(adjustmentHeader.getAdjustmentId(), detail.getBatchId(), generatedId);

			StockTrack stockTrack = new StockTrack(generatedId, adjustmentHeader.getAdjustmentType(),
					detail.getQuantity(), openingStock, new Date(), adjustmentHeader.getModifiedBy());

			stockDao.addStockTrack(stockTrack);

		}
		return "";
	}

	@Override
	@Transactional
	public boolean stockDown(Adjustment adjustmentHeader) throws Exception {

		List<AdjustmentDetail> adjustmentDetails = adjustmentDao
				.getAdjustmentDetails(adjustmentHeader.getAdjustmentId());

		for (AdjustmentDetail detail : adjustmentDetails) {

			int openingStock = stockDao.getQunatityById(detail.getBatchId());

			if (openingStock < detail.getQuantity()) {
				throw new Exception("Insufficent Stock Quantity");
			}

			stockDao.modifyStockQuantityByBId(detail.getBatchId(), openingStock - detail.getQuantity(),
					adjustmentHeader.getModifiedBy());

			StockTrack stockTrack = new StockTrack(detail.getBatchId(), adjustmentHeader.getAdjustmentType(),
					-detail.getQuantity(), openingStock, new Date(), adjustmentHeader.getModifiedBy());

		}
		return true;

	}

	@Override
	public List<StockMaster> getAllStocks() throws Exception {
		return stockDao.getAllStocksWithPostiveQuantity();
	}

}
