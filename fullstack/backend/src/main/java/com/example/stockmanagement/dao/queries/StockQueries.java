package com.example.stockmanagement.dao.queries;

public class StockQueries {

	public static final String INSERT_STOCK = "INSERT INTO tbl_stock_master (ProductId,Batch,Quantity,ExpiryDate,Mrp,CreatedBy) VALUES (:productId,:batch,s:quantity,:expiryDate,:mrp,:createdBy)";
	public static final String GET_QUANTITY_BY_ID = "SELECT Quantity FROM tbl_stock_master WHERE BatchId = :batchId";
	public static final String MODIFY_STOCK_QUANTITY_BY_ID = "UPDATE tbl_stock_master SET Quantity = :quantity , ModifiedBy= :modifiedBy WHERE BatchId = :batchId";
	public static final String GET_ALL_STOCKS_WITH_POSITIVE_QUANTITY = "SELECT * FROM tbl_stock_master WHERE Quantity > 0";
	public static final String INSERT_INTO_STACK_TRACK = "SELECT * FROM tbl_stock_track (BatchId,TransactionType,Quantity,OpenStock,CreatedBy) VALUES (:batchId,:transactionType,:quantity,:openStock,:createdBy) ";


}
