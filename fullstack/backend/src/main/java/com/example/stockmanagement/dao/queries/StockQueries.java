package com.example.stockmanagement.dao.queries;

public class StockQueries {
	public static final String INSERT_STOCK = "INSERT INTO tbl_stock_master (ProductId,Batch,Quantity,ExpiryDate,Mrp,CreatedBy) VALUES (:productId,:batch,:quantity,:expiryDate,:mrp,:createdBy)";
	public static final String GET_QUANTITY_BY_ID = "SELECT Quantity FROM tbl_stock_master WHERE BatchId = :BatchId";
	public static final String MODIFY_STOCK_QUANTITY_BY_ID = "UPDATE tbl_stock_master SET Quantity = :quantity , ModifiedBy= :modifiedBy WHERE BatchId = :BatchId";
	protected static  String SELECT_ALL_PRODUCT_IDS ="SELECT DISTINCT ProductId FROM tbl_stock_master WHERE 1=1 AND (:flag=1 OR Quantity > 0 )";
	public static final String GET_BATCH_AND_BATCHID_BY_PID = "SELECT Batch,BatchId FROM tbl_stock_master WHERE ProductId = :ProductId";
	protected static final String SELECT_STOCK_DETAIL_BY_PID_AND_BID = "SELECT * FROM tbl_stock_master WHERE productId = :productId AND batchId = :batchId";
	protected static final String INSERT_INTO_STOCK_TRACK = "INSERT INTO tbl_stock_track( BatchId, TransactionType, Quantity, OpenStock, CreatedBy) VALUES( :BatchId, :TransactionType, :Quantity, :OpenStock, :CreatedBy)";
}
