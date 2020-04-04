package com.idi.hr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.idi.hr.bean.SalaryReport;

public class SalaryReportMapper  implements RowMapper<SalaryReport> {

	public SalaryReport mapRow(ResultSet rs, int nowNum) throws SQLException {
		String finalSalary = rs.getString("ACTUAL_SALARY");
		String overTimeN = rs.getString("OVER_TIME_N");
		String overTimeW = rs.getString("OVER_TIME_W");
		String overTimeH = rs.getString("OVER_TIME_H");
		String overTimeSalary = rs.getString("OVER_TIME_SALARY");
		String bounus = rs.getString("BOUNUS");
		String subsidize = rs.getString("SUBSIDIZE");
		String subLunch = rs.getString("SUB_LUNCH");
		String subPhone = rs.getString("SUB_PHONE");
		String subGas = rs.getString("SUB_GAS");
		String subSkill = rs.getString("SUB_SKILL");
		String overWork = rs.getString("OVER_WORK");
		String advancePayed = rs.getString("ADVANCE_PAYED");
		String taxPersonal = rs.getString("TAX_PERSONAL");		
		//int month = rs.getInt("MONTH");
		//int year = rs.getInt("YEAR");
		String payedInsurance = rs.getString("PAYED_INSURANCE");

		return new SalaryReport(finalSalary, overTimeN, overTimeW, overTimeH, 
			overTimeSalary, bounus, subsidize, subLunch, subPhone, subGas, subSkill, 
			overWork,advancePayed, taxPersonal, payedInsurance);
	}	
}
