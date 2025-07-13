package com.example.stockmanagement.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.Response;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.service.AdjustmentService;
import com.example.stockmanagement.service.StockService;
import com.example.stockmanagement.utilities.Status;

@RestController
@RequestMapping("/api")
public class AdjustmentController {
	@Autowired
	AdjustmentService adjustmentService;

	@Autowired
	StockService stockService;

	@PostMapping("/addAdjustment")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Response> addAdjustment(@RequestBody @Valid Adjustment adjustment) {
		try {
			System.out.println("here");
			System.out.println(adjustment);
			
			adjustmentService.addAdjustment(adjustment);
			Response response = new Response("201", "Adjustment Added Successfully");

			return ResponseEntity.status(201).body(response);

		} catch (StockManagementException e) {
			Response response = new Response("400", e.getMessage());
			return ResponseEntity.status(500).body(response);

		}

	}

	@GetMapping("/getAdjustments")
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Adjustment>> getAdjustments() {
		List<Adjustment> adjustments = null;
		try {
			adjustments = adjustmentService.getAdjustments();
			return ResponseEntity.status(200).body(adjustments);
		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(null);

		}

	}

	@CrossOrigin(origins = "*")
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
	@CrossOrigin(origins = "*")
	public ResponseEntity<Response> updateStatus(@RequestBody Map<String, String> requestParameters) {
		try {
			System.out.println(requestParameters);
			long adjustmentId = Long.parseLong(requestParameters.get("adjustmentId"));
			String statusChar = requestParameters.get("status");
			Status status = Status.valueOf(statusChar);
			System.out.println("In controller " + status + " " + adjustmentId);
			adjustmentService.updateStatus(adjustmentId, status, "Admin");

		} catch (StockManagementException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(400).body(new Response("400", e.getMessage()));

		}
		return ResponseEntity.status(204).body(new Response("204", "Updated Sucessfully"));

	}
	

}
