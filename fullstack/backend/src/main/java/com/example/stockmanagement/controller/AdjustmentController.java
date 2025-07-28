package com.example.stockmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
import com.example.stockmanagement.domain.Response;
import com.example.stockmanagement.domain.StockMaster;
import com.example.stockmanagement.exception.StockManagementException;
import com.example.stockmanagement.service.AdjustmentService;
import com.example.stockmanagement.service.StockService;

@RestController
@RequestMapping("/api")
public class AdjustmentController {
	@Autowired
	AdjustmentService adjustmentService;

	@Autowired
	StockService stockService;

	@PostMapping("/addAdjustment")
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

	@PostMapping("/approveAdjustment")
	public ResponseEntity<Response> approveAdjustment(@RequestBody Map<String, String> parameters) {
		try {
			long id = Long.valueOf(parameters.get("adjustmentId"));
			String modifiedBy = parameters.get("modifiedBy");
			adjustmentService.approveAdjustment(id, modifiedBy);
			return ResponseEntity.status(200).body(new Response("200", "Approved Adjustment Sucessfully"));

		} catch (StockManagementException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new Response("500", e.getMessage()));

		}

	}

	@PostMapping("/withdrawAdjustment")
	public ResponseEntity<Response> withdrawAdjustment(@RequestBody Map<String, String> parameters) {
		try {
			long id = Long.valueOf(parameters.get("adjustmentId"));
			String modifiedBy = parameters.get("modifiedBy");
			String remarks = parameters.get("remarks");
			adjustmentService.withdrawAdjustment(id, modifiedBy, remarks);
			return ResponseEntity.status(200).body(new Response("200", "Approved Adjustment Sucessfully"));

		} catch (StockManagementException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new Response("500", e.getMessage()));

		}

	}

	@PostMapping("/getAdjustmentCount")
	public ResponseEntity<?> getAdjustmentCount(@RequestBody AdjustmentCriteria adjustmentCriteria) {
		try {
			long val = adjustmentService.getAdjustmentsCount(adjustmentCriteria);
			return ResponseEntity.status(200).body(val);

		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(new Response("500", e.getMessage()));
		}

	}

	@PostMapping("/getAdjustmentsByCriteria")
	public List<Adjustment> getAdjusmtent(@RequestBody @Valid AdjustmentCriteria criteria) {
		try {

			List<Adjustment> adjustments = new ArrayList<>();
			adjustments = adjustmentService.getAdjustmentsByCriteria(criteria);
			return adjustments;
		} catch (StockManagementException e) {
			return null;
		}

	}

	@GetMapping("/getProductIds")
	public ResponseEntity<?> getProductIds(@RequestParam boolean isRequired,@RequestParam(required = false) String productId) {
		List<String> productIds = null;
		try {
			productIds = stockService.getProductIds(isRequired,productId);
			return ResponseEntity.status(200).body(productIds);
		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(new Response("500",e.getMessage()));
		}

	}

	@GetMapping("/getBatches")
	public Map<Long, String> getBatches(@RequestParam String productId) {
		return stockService.getBatches(productId);

	}

	@GetMapping("getStockDetail")
	public ResponseEntity<?> getStockDetail(@RequestParam String productId, @RequestParam long batchId) {
		try {
			StockMaster stock = stockService.getStockDetail(productId, batchId);
			return ResponseEntity.status(200).body(stock);
		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(new Response("500", e.getMessage()));
		}
	}
}
