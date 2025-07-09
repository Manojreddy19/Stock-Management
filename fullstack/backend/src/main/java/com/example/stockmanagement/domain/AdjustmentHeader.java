package com.example.stockmanagement.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AdjustmentHeader {

	private long adjustmentId;
	private char adjustmentType;
	private double amount;
	private char status;
	private String createdBy;
	private Date createdAt;
	private String modifiedBy;
	private Date modifiedAt;
	private String remarks;

	private List<AdjustmentDetail> adjustmentDetails;

}
