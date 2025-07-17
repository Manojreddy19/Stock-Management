package com.example.stockmanagement.dao.queries;

public class AdjustmentQueries {
	protected String INSERT_INTO_ADJUSTMENT_HEADER = "INSERT INTO tbl_adjustment_header( AdjustmentType, Amount, Status, Remarks, CreatedBy, ModifiedBy) VALUES( :adjustmentType, :Amount, :Status, :Remarks, :CreatedBy, :ModifiedBy)";
	protected String INSERT_INTO_ADJUSTMENT_DETAIL = "INSERT INTO tbl_adjustment_details(AdjustmentId, ProductId, Batch, BatchId, Quantity, Mrp, ExpiryDate, Amount) VALUES(:AdjustmentId, :ProductId, :Batch, :BatchId, :Quantity, :Mrp, :ExpiryDate, :Amount)";
	protected String UPDATE_STATUS_AND_MODIFIEDBY = "UPDATE tbl_adjustment_header SET status=:Status, ModifiedBy=:ModifiedBy, Remarks=:Remarks WHERE AdjustmentId=:AdjustmentId";
	protected String SELECT_ALL_ADJUSTMENTS = "SELECT * FROM tbl_adjustment_header";
	protected String UPDATE_GENERATEDBID = "UPDATE tbl_adjustment_details SET GeneratedBatchId=:GeneratedBid WHERE AdjustmentId=:AdjustmentId AND BatchId=:BatchId";
	protected String SELECT_ALL_ADJUSTMENT_DETAILS = "SELECT * FROM tbl_adjustment_details WHERE AdjustmentId = :adjustmentId";
	protected String SELECT_ALL_ADJUSTMENTS_BY_ID = "SELECT * FROM tbl_adjustment_header WHERE AdjustmentId = :adjustmentId";
	
	protected String GET_ADJUSTMENT_COUNT="SELECT COUNT(*) FROM tbl_adjustment_header WHERE AdjustmentType=:AdjustmentType and Status=:Status ";
}