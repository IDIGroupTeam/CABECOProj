package com.idi.hr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.idi.hr.bean.SalaryRe;

public class SalaryReMapper implements RowMapper<SalaryRe>{

	@Override
	public SalaryRe mapRow(ResultSet rs, int rowNum) throws SQLException {
		int month = rs.getInt("MONTH");
		int year = rs.getInt("YEAR");
		String value = rs.getString("VALUE");
		String group = rs.getString("WORK_GROUP");
		String des = rs.getString("DESCRIPTION");
		return new SalaryRe(month, year, value, group, des);
	}
}
