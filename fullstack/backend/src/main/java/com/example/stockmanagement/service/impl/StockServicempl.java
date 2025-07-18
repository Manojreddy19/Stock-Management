package com.example.stockmanagement.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.stockmanagement.dao.AdjustmentDao;
import com.example.stockmanagement.dao.StockDao;
import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentDetail;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.domain.StockTrack;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.service.StockService;

@Service
public class StockServicempl implements StockService {


	@Autowired
	private StockDao stockDao;
	@Autowired
	private AdjustmentDao adjustmentDao;

	@Override
	@Transactional
	public void stockUp(Adjustment adjustmentHeader) throws StockManagementException {
		try {

			List<AdjustmentDetail> adjustmentDetails = adjustmentDao
					.getAdjustmentDetails(adjustmentHeader.getAdjustmentId());
			Date now=new Date();
			for (AdjustmentDetail detail : adjustmentDetails) {
				StockMaster stock = new StockMaster(detail.getProductId(), detail.getBatch(), null,
						detail.getQuantity(), detail.getExpiryDate(), detail.getMrp(), adjustmentHeader.getCreatedBy(),
						adjustmentHeader.getCreatedAt(), adjustmentHeader.getModifiedBy(), now);

				int openingStock = stockDao.getQunatityById(detail.getBatchId());

				long generatedId = stockDao.insertAndSendBackBId(stock);

				adjustmentDao.updateGeneratedBatchId(adjustmentHeader.getAdjustmentId(), detail.getBatchId(),
						generatedId);

				StockTrack stockTrack = new StockTrack(generatedId, adjustmentHeader.getAdjustmentType(),
						detail.getQuantity(), openingStock, now, adjustmentHeader.getModifiedBy());
				stockDao.addStockTrack(stockTrack);

			}

		} catch (StockManagementException e) {
			e.printStackTrace();
			throw new StockManagementException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void stockDown(Adjustment adjustmentHeader) throws StockManagementException {

		try {
			List<AdjustmentDetail> adjustmentDetails = adjustmentDao
					.getAdjustmentDetails(adjustmentHeader.getAdjustmentId());

			for (AdjustmentDetail detail : adjustmentDetails) {

				int openingStock = stockDao.getQunatityById(detail.getBatchId());

				if (openingStock < detail.getQuantity()) {
					throw new StockManagementException("Insufficent Stock Quantity");
				}

				stockDao.modifyStockQuantityByBId(detail.getBatchId(), openingStock - detail.getQuantity(),
						adjustmentHeader.getModifiedBy());

				StockTrack stockTrack = new StockTrack(detail.getBatchId(), adjustmentHeader.getAdjustmentType(),
						-detail.getQuantity(), openingStock, new Date(), adjustmentHeader.getModifiedBy());
				stockDao.addStockTrack(stockTrack);

			}

		} catch (StockManagementException e) {
			throw new StockManagementException(e.getMessage());
		}

	}

	@Override
	public List<StockMaster> getAllStocks() throws StockManagementException {
		return stockDao.getAllStocks();
	}
	@Override
	public Map<Long, String> getBatches(String productId) throws StockManagementException {
		
		Map<Long, String>batches=null;
		try {
			batches = stockDao.getProductBatches(productId);
			return batches;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new StockManagementException(e.getMessage());
		}
	}

}
