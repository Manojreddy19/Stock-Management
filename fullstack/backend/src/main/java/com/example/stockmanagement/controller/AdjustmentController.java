package com.example.stockmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.Response;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.service.AdjustmentService;
import com.example.stockmanagement.service.StockService;
import com.example.stockmanagement.utilities.Status;

@RestController
public class AdjustmentController {
	@Autowired
	AdjustmentService adjustmentService;

	@Autowired
	StockService stockService;

	@PostMapping("/addAdjustment")
	public ResponseEntity<Response> addAdjustment(@RequestBody Adjustment adjustment) {
		try {
			adjustmentService.addAdjustment(adjustment);

		} catch (Exception e) {
			Response response = new Response("400", e.getMessage());
			return ResponseEntity.status(201).body(response);

		}
		Response response = new Response("201", "Adjustment Added Successfully");

		return ResponseEntity.status(201).body(response);

	}

	@GetMapping("/getAdjustments")
	public ResponseEntity<List<Adjustment>> getAdjustments() {
		List<Adjustment> adjustments = null;
		try {
			adjustments = adjustmentService.getAdjustments();
		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(null);

		}
		return ResponseEntity.status(200).body(adjustments);

	}

	@GetMapping("/getStock")
	public ResponseEntity<List<StockMaster>> getStock() {

		List<StockMaster> stock = null;
		try {
			stock = stockService.getAllStocks();

		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);

		}
		return ResponseEntity.status(200).body(stock);

	}

	@PutMapping("/updateStatus")
	public ResponseEntity<Response> updateStatus(@RequestBody Map<String, String> requestParameters) {
		try {
			long adjustmentId = Long.parseLong(requestParameters.get("adjustmentId"));
			String statusChar = requestParameters.get("status");
			Status status = Status.valueOf(statusChar);
			adjustmentService.updateStatus(adjustmentId, status, "Admin");

		} catch (Exception e) {
			return ResponseEntity.status(400).body(new Response("400", e.getMessage()));

		}
		return ResponseEntity.status(204).body(new Response("204", "Updated Sucessfully"));

	}

}
