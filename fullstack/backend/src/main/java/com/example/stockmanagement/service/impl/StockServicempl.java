package com.example.stockmanagement.service.impl;

import java.io.Console;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(StockServicempl.class);

	@Autowired
	private StockDao stockDao;
	@Autowired
	private AdjustmentDao adjustmentDao;

	@Override
	@Transactional
	public String stockUp(Adjustment adjustmentHeader) throws StockManagementException {
		try {
			log.info("In stock Up");
			List<AdjustmentDetail> adjustmentDetails = adjustmentDao
					.getAdjustmentDetails(adjustmentHeader.getAdjustmentId());
			log.info("In stock Up");
			System.out.println(adjustmentDetails);

			for (AdjustmentDetail detail : adjustmentDetails) {
				StockMaster stock = new StockMaster(detail.getProductId(), detail.getBatch(), null, detail.getQuantity(),
						detail.getExpiryDate(), detail.getMrp(), adjustmentHeader.getCreatedBy(), new Date(), "Admin1",
						new Date());
				log.info("before quantitiyDao "+detail.getBatchId());
				
				int openingStock = stockDao.getQunatityById(detail.getBatchId());
				
				System.out.println("After getting quantity "+openingStock);
				long generatedId = stockDao.insertAndSendBackBId(stock);
				System.out.println("After stockDao.insertAndSendBackBId(stock)");

				adjustmentDao.updateGeneratedBatchId(adjustmentHeader.getAdjustmentId(), detail.getBatchId(), generatedId);
				
				System.out.println("After updateGeneratedBatchId(");
				StockTrack stockTrack = new StockTrack(generatedId, adjustmentHeader.getAdjustmentType(),
						detail.getQuantity(), openingStock, new Date(), adjustmentHeader.getModifiedBy());
				stockDao.addStockTrack(stockTrack);
				log.info("After Stock Track");

				

			}
			System.out.println("In stock Up end");
			
		}catch (StockManagementException e) {
			throw new StockManagementException(e.getMessage());

		}


		return "";
	}

	@Override
	@Transactional
	public void stockDown(Adjustment adjustmentHeader) throws StockManagementException {

		try {
			System.out.println("In stock Down Start");
			List<AdjustmentDetail> adjustmentDetails = adjustmentDao
					.getAdjustmentDetails(adjustmentHeader.getAdjustmentId());

			for (AdjustmentDetail detail : adjustmentDetails) {

				int openingStock = stockDao.getQunatityById(detail.getBatchId());
				
				System.out.println("In stock Down openingStock "+openingStock);
				

				if (openingStock < detail.getQuantity()) {
					throw new StockManagementException("Insufficent Stock Quantity");
				}

				stockDao.modifyStockQuantityByBId(detail.getBatchId(), openingStock - detail.getQuantity(),
						adjustmentHeader.getModifiedBy());
				System.out.println("In stock Down modifying "+openingStock);

				StockTrack stockTrack = new StockTrack(detail.getBatchId(), adjustmentHeader.getAdjustmentType(),
						-detail.getQuantity(), openingStock, new Date(), "Admin1");
				stockDao.addStockTrack(stockTrack);

			}
			
		}catch (StockManagementException e) {
			throw new StockManagementException(e.getMessage());
		}
		
		

	}

	@Override
	public List<StockMaster> getAllStocks() throws StockManagementException {
		return stockDao.getAllStocks();
	}

}
