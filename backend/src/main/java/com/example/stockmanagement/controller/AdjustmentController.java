package com.example.stockmanagement.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockmanagement.domain.Adjustment;
import com.example.stockmanagement.domain.AdjustmentCriteria;
import com.example.stockmanagement.domain.Response;
import com.example.stockmanagement.domain.StockDetail;
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
//	@CrossOrigin(origins = "*")
	public ResponseEntity<Response> addAdjustment(@RequestBody @Valid Adjustment adjustment) {
		try {

			long id=adjustmentService.addAdjustment(adjustment);
			Response response = new Response("201", "Adjustment Id: " +id+" Added Successfully");

			return ResponseEntity.status(201).body(response);

		} catch (StockManagementException e) {
			e.printStackTrace();
			Response response = new Response("400", e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
	}
	
	@GetMapping("/getBatches/{productId}")
//	@CrossOrigin(origins = "*")
	public Map<Long, String> getBatches(@PathVariable String productId)
	{
		System.out.println(productId);
		return stockService.getBatches(productId);
	}
	
	@PostMapping("/getAdjustmentsByCriteria")
	public List<Adjustment> getAdjusmtent(@RequestBody @Valid AdjustmentCriteria criteria)
	{
		 List<Adjustment> adjustments=adjustmentService.getAdjustmentsByCriteria(criteria);
		 return adjustments;
	}

//	@CrossOrigin(origins = "*")
	@GetMapping("/getStocks" )
	public ResponseEntity<List<StockDetail>> getStocks(@CookieValue(value = "JSESSIONID", required = false) String jSessionId) {

		System.out.println(jSessionId);
		List<StockDetail> stock = null;
		try {
			stock = stockService.getAllStocks();
			return ResponseEntity.status(200).body(stock);

		} catch (StockManagementException e) {
			return ResponseEntity.status(500).body(null);

		}

	}

	
	@GetMapping("/getProducts")
	public ResponseEntity<List<String>> getProductIds(@RequestParam boolean isRequired){
		List<String> productIds=null;
		try {
			productIds=adjustmentService.getProductIds(isRequired);
			return ResponseEntity.status(200).body(productIds);
		}catch(StockManagementException e) {
			return ResponseEntity.status(500).body(null);
		}
			
		
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

	
//	@GetMapping("/sendData")
//	public ResponseEntity<SearchCriteria> getSearchCriteriaDetails() {
//		
//	}
}

//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//System.out.println( auth.getPrincipal());

