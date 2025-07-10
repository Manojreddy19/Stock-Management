package com.example.stockmanagement.dao.queries;

public class StockQueries {

	public static final String INSERT_STOCK = "INSERT INTO stock_master (ProductId,Batch,BatchId,Quantity,ExpiryDate,Mrp,CreatedBy) VALUES (:productId,:batch,:BatchId,:quantity,:expiryDate,:mrp,:createdBy)";
	public static final String GET_QUANTITY_BY_ID = "SELECT Quantity FROM stock_master WHERE BatchId = :BatchId";
	public static final String MODIFY_STOCK_QUANTITY_BY_ID = "UPDATE stock_master SET Quantity = :quantity , ModifiedBy= :modifiedBy WHERE BatchId = :BatchId";
	public static final String GET_ALL_STOCKS_WITH_POSITIVE_QUANTITY = "SELECT * FROM stock_master WHERE Quantity > 0";
}
