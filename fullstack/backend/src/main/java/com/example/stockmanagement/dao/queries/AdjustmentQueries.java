package com.example.stockmanagement.dao.queries;

public class AdjustmentQueries {
	protected String INSERT_INTO_ADJUSTMENT_HEADER = "INSERT INTO tbl_adjustment_header( AdjustmentType, Amount, Status, Remarks, CreatedBy, ModifiedBy) VALUES( :AdjustmentType, :Amount, :Status, :Remarks, :CreatedBy, :ModifiedBy)";
	protected String INSERT_INTO_ADJUSTMENT_DETAIL = "INSERT INTO tbl_adjustment_details(AdjustmentId, ProductId, Batch, BatchId, Quantity, Mrp, ExpiryDate, Amount) VALUES(:AdjustmentId, :ProductId, :Batch, :BatchId, :Quantity, :Mrp, :ExpiryDate, :Amount)";
	protected String UPDATE_STATUS_AND_MODIFIEDBY = "UPDATE tbl_adjustment_header SET status=:Status, modifiedBy=:modifiedBy WHERE adjustmentId=:adjustmentId";
	protected String SELECT_ALL_ADJUSTMENTS = "SELECT * FROM tbl_adjustment_header";
	protected String UPDATE_GENERATEDBID = "UPDATE tbl_adjustment_details SET generatedBid=:generatedBid WHERE adjustmentId=:adjustmentId AND batchId=:batchId";
	protected String SELECT_ALL_ADJUSTMENT_DETAILS = "SELECT * FROM tbl_adjustment_details";
	protected String SELECT_ALL_ADJUSTMENTS_BY_ID="SELECT * FROM tbl_adjustment_header WHERE AdjustmentId = :adjustmentId";
} 