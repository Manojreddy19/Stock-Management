package com.example.stockmanagement.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class StockDetailsExtractor implements ResultSetExtractor<Map<Long, String>> {

	@SuppressWarnings("null")
	@Override
	public Map<Long, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, String> batches=null;
		Long batchId;
		String batch;
		
		while(rs.next())
		{
			batchId=rs.getLong("BatchId");
			batch= rs.getString("Batch");
			batches.put(batchId, batch);
		}
		return batches;
	}
	
	

}
