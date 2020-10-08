package com.idi.hr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.idi.hr.bean.SalaryDetail;

public class SalaryDetailMapper  implements RowMapper<SalaryDetail> {

	public SalaryDetail mapRow(ResultSet rs, int nowNum) throws SQLException {
		int employeeId = rs.getInt("EMPLOYEE_ID");
		String workGroup = rs.getString("WORK_GROUP");
		String basicSalary = rs.getString("BASIC_SALARY");
		String finalSalary = rs.getString("ACTUAL_SALARY");
		String unionFee = rs.getString("UNION_FEE");
		String subInsurance = rs.getString("SUB_INSURANCE");
		String workedTime = rs.getString("WORKED_TIME");
		String workedTimePrice = rs.getString("WORKED_TIME_PRICE");
		String overTimeSalary = rs.getString("OVER_TIME_SALARY");
		String bounus = rs.getString("BOUNUS");
		String maintainDay = rs.getString("MAINTAIN_DAY");
		String subsidize = rs.getString("SUBSIDIZE");
		String subLunch = rs.getString("SUB_LUNCH");
		String subPhone = rs.getString("SUB_PHONE");
		String subGas = rs.getString("SUB_GAS");
		String subSkill = rs.getString("SUB_SKILL");
		String overWork = rs.getString("OVER_WORK");
		String advancePayed = rs.getString("ADVANCE_PAYED");
		String taxPersonal = rs.getString("TAX_PERSONAL");
		String totalIncome = rs.getString("TOTAL_INCOME");
		String totalReduce = rs.getString("TOTAL_REDUCE");
		int month = rs.getInt("MONTH");
		int year = rs.getInt("YEAR");
		String desc = rs.getString("S.COMMENT");		
		String payedInsurance = rs.getString("PAYED_INSURANCE");
		String fullName = rs.getString("FULL_NAME");
		String phoneNo = rs.getString("PHONE_NO");
		String bankNo = rs.getString("BANK_NO");
		String bankName = rs.getString("BANK_NAME");
		String bankBranch = rs.getString("BANK_BRANCH");
		String salary = rs.getString("SALARY");
		String group = rs.getString("E.WORK_GROUP");
		String department = rs.getString("DEPARTMENT");
		String jobTitle = rs.getString("JOB_TITLE");
		String salaryInsurance = rs.getString("SALA_SOCI_INSU");
		String percentCompanyPay = rs.getString("PERCENT_SOCI_INSU_C");
		String percentEmployeePay = rs.getString("PERCENT_SOCI_INSU_E");
		int workcomplete = rs.getInt("WORK_COMPLETE");
		String workedDay = rs.getString("WORKED_DAY");
		String other = rs.getString("OTHER");
		String arrears = rs.getString("ARREARS");
		String payStatus = rs.getString("PAY_STATUS");
		String rSalary = rs.getString("R_SALARY");
		String saProduct = rs.getString("SA_PRODUCT");
		String saTime = rs.getString("SA_TIME");
		
		return new SalaryDetail(employeeId, workGroup, basicSalary, finalSalary, unionFee, subInsurance, workedTime, 
				workedTimePrice, overTimeSalary, bounus, maintainDay, subsidize, subLunch, subPhone, subGas, subSkill, 
				overWork, advancePayed, taxPersonal, month, year, totalIncome, totalReduce, desc,
				payedInsurance, fullName, phoneNo, bankNo, bankName, bankBranch, salary, group, department, 
				jobTitle, salaryInsurance, percentCompanyPay, percentEmployeePay, workcomplete, 
				workedDay, other, arrears, payStatus, rSalary, saProduct, saTime);
	}	
}
