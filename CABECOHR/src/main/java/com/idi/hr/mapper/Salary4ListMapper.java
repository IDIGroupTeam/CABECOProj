package com.idi.hr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.idi.hr.bean.Salary4List;

public class Salary4ListMapper implements RowMapper<Salary4List> {

	public Salary4List mapRow(ResultSet rs, int nowNum) throws SQLException {
		int employeeId = rs.getInt("EMPLOYEE_ID");
		String fullName = rs.getString("FULL_NAME");
		String salary = rs.getString("SALARY");
		String constSalary = rs.getString("CONST_SALARY");
		String bankNo = rs.getString("BANK_NO");
		String bankName = rs.getString("BANK_NAME");
		String bankBranch = rs.getString("BANK_BRANCH");		
		String group = rs.getString("WORK_GROUP");
		String department = rs.getString("DEPARTMENT");
		String jobTitle = rs.getString("JOB_TITLE");
		String description = rs.getString("COMMENT");
		String workedDay = rs.getString("WORKED_DAY"); 
		String actualSalary = rs.getString("ACTUAL_SALARY");
		return new Salary4List(employeeId, fullName, salary, constSalary, bankNo, bankName, bankBranch, group,
				department, jobTitle, description, workedDay, actualSalary);
	}
}
