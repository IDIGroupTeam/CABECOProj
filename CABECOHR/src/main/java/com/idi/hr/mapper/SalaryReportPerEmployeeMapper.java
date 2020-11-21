package com.idi.hr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.idi.hr.bean.SalaryReportPerEmployee;

public class SalaryReportPerEmployeeMapper  implements RowMapper<SalaryReportPerEmployee> {

	public SalaryReportPerEmployee mapRow(ResultSet rs, int nowNum) throws SQLException {
		int employeeId = rs.getInt("EMPLOYEE_ID");
		String fullName = rs.getString("FULL_NAME");
		String salary = rs.getString("SALARY");
		String department = rs.getString("DEPARTMENT");
		String workGroup = rs.getString("WORK_GROUP");
		String jobTitle = rs.getString("JOB_TITLE");
		String finalSalary = rs.getString("ACTUAL_SALARY");
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

		return new SalaryReportPerEmployee(employeeId, fullName, department, workGroup, jobTitle,
				salary, finalSalary, overTimeSalary, bounus, subsidize, subLunch, subPhone, subGas, 
				subSkill, overWork,	advancePayed, taxPersonal, payedInsurance);
	}	
}
