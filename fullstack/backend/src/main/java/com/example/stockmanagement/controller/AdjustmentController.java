package com.example.stockmanagement.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
import com.example.stockmanagement.domain.Response;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.service.AdjustmentService;
import com.example.stockmanagement.service.StockService;
import com.example.stockmanagement.utilities.AdjustmentType;
import com.example.stockmanagement.utilities.Status;

@RestController
@RequestMapping("/api")
public class AdjustmentController {
	@Autowired
	AdjustmentService adjustmentService;

	@Autowired
	StockService stockService;

	@PostMapping("/addAdjustment")
//	@CrossOrigin(origins = "*")
	public ResponseEntity<Response> addAdjustment(@RequestBody @Valid Adjustment adjustment) {
		try {

			long id = adjustmentService.addAdjustment(adjustment);
			Response response = new Response("201", "Adjustment Id: " + id + " Added Successfully");

			return ResponseEntity.status(201).body(response);

		} catch (StockManagementException e) {
			e.printStackTrace();
			Response response = new Response("400", e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
	}

	@GetMapping("/getAdjustments")
//	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Adjustment>> getAdjustments() {
		List<Adjustment> adjustments = null;
		try {
			adjustments = adjustmentService.getAdjustments();
			return ResponseEntity.status(200).body(adjustments);
		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(null);

		}

	}

//	@CrossOrigin(origins = "*")
	@GetMapping("/getStocks")
	public ResponseEntity<List<StockMaster>> getStocks() {

		List<StockMaster> stock = null;
		try {
			stock = stockService.getAllStocks();
			return ResponseEntity.status(200).body(stock);

		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(null);

		}

	}

	@PostMapping("/updateStatus")
//	@CrossOrigin(origins = "*")
	public ResponseEntity<Response> updateStatus(@RequestBody Map<String, String> requestParameters) {
		try {

			long adjustmentId = Long.parseLong(requestParameters.get("adjustmentId"));
			String statusChar = requestParameters.get("status");
			Status status = Status.valueOf(statusChar);
			String modifiedBy = requestParameters.get("modifiedBy");
			String remarks = requestParameters.get("remarks");

			adjustmentService.updateStatus(adjustmentId, status, modifiedBy, remarks);

		} catch (StockManagementException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(new Response("400", e.getMessage()));

		}
		return ResponseEntity.status(204).body(new Response("204", "Updated Sucessfully"));

	}

	@GetMapping("/getAdjustmentsCount")
	public ResponseEntity<?> getAdjustmentCount(@RequestBody AdjustmentCriteria adjustmentCriteria) {
		try {
			long val = adjustmentService.getAdjustmentsCount(adjustmentCriteria);
			return ResponseEntity.status(200).body(val);

		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(new Response("500", e.getMessage()));
		}

	}

}
