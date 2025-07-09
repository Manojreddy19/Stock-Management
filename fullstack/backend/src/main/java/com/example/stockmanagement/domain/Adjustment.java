package com.example.stockmanagement.domain;

import java.util.Date;
import java.util.List;

import com.example.stockmanagement.utilities.AdjustmentType;
import com.example.stockmanagement.utilities.Status;

public class Adjustment {

	private Long adjustmentId;
	private AdjustmentType adjustmentType;
	private Double amount;
	private Status status;
	private String remarks;
	private List<AdjustmentDetail> adjustmentDetails;
	private String createdBy;
	private Date createdAt;
	private String modifiedBy;
	private Date modifiedAt;
	
	

}
