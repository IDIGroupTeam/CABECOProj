package com.idi.hr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.idi.hr.bean.Product;

public class ProductMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String code = rs.getString("CODE");
		String name = rs.getString("NAME");
		String comment = rs.getString("DESCRIPTION");
		
		return new Product(code, name, comment);
	}

}
