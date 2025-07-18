package com.example.stockmanagement.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductIdRowMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
    	System.out.println(rs.getString("ProductId"));
        return rs.getString("ProductId");
    }
}