package com.idi.hr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.idi.hr.bean.ProductSold;

public class ProductSoldMapper implements RowMapper<ProductSold>{

	@Override
	public ProductSold mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String code = rs.getString("CODE");		
		String name = rs.getString("P.NAME");
		String price = rs.getString("PRICE");
		String amount = rs.getString("AMOUNT");
		String month = rs.getString("MONTH"); 
		String scale = rs.getString("SCALE"); 
		String moneyIncome = rs.getString("MONEY_INCOME"); 
		String department = rs.getString("DEPARTMENT");
		String comment = rs.getString("COMMENT");
		
		return new ProductSold(code, name, price, amount, month, scale, moneyIncome, department, comment);
	}

}
