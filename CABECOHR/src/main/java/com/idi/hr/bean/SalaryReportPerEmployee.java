package com.idi.hr.bean;

import java.io.Serializable;

public class SalaryReportPerEmployee implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -1871003330726282259L;
	
	private int employeeId; 
	private String fullName;
	private String salary;
	private String department;
	private String workGroup;
	private String jobTitle;
	private String finalSalary;
	private String overTimeSalary;
	private String bounus;
	private String subsidize;
	private String subLunch;
	private String subPhone;
	private String subGas;
	private String subSkill;
	private String overWork;
	private String advancePayed;
	private String taxPersonal;
	private int month;
	private int year;	
	private String payedInsurance;
	
	public SalaryReportPerEmployee() {
		
	}

	public SalaryReportPerEmployee(int employeeId, String fullName, String department, String workGroup, String jobTitle,
			String salary, String finalSalary, String overTimeSalary, String bounus, String subsidize, String subLunch, 
			String subPhone, String subGas, String subSkill, String overWork, String advancePayed, String taxPersonal, String payedInsurance) {
		this.employeeId = employeeId;
		this.fullName = fullName;
		this.salary = salary;
		this.department = department;
		this.workGroup = workGroup;
		this.jobTitle = jobTitle;
		this.finalSalary = finalSalary;
		this.overTimeSalary = overTimeSalary;
		this.bounus = bounus;
		this.subsidize = subsidize;
		this.subLunch = subLunch;
		this.subPhone = subPhone;
		this.subGas = subGas;
		this.subSkill = subSkill;
		this.overWork = overWork;
		this.advancePayed = advancePayed;
		this.taxPersonal = taxPersonal;
				
/*		this.month = month;
		this.year = year;*/
		this.payedInsurance = payedInsurance;

	}	

	public String getFinalSalary() {
		return finalSalary;
	}

	public void setSalaryFinalSalary(String finalSalary) {
		this.finalSalary = finalSalary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public String getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}

	public void setPayedInsurance(String payedInsurance) {
		this.payedInsurance = payedInsurance;
	}

	public String getOverTimeSalary() {
		return overTimeSalary;
	}

	public void setOverTimeSalary(String overTimeSalary) {
		this.overTimeSalary = overTimeSalary;
	}

	public String getBounus() {
		return bounus;
	}

	public void setBounus(String bounus) {
		this.bounus = bounus;
	}

	public String getSubsidize() {
		return subsidize;
	}

	public void setSubsidize(String subsidize) {
		this.subsidize = subsidize;
	}

	public String getAdvancePayed() {
		return advancePayed;
	}

	public void setAdvancePayed(String advancePayed) {
		this.advancePayed = advancePayed;
	}

	public String getTaxPersonal() {
		return taxPersonal;
	}

	public void setTaxPersonal(String taxPersonal) {
		this.taxPersonal = taxPersonal;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setFinalSalary(String finalSalary) {
		this.finalSalary = finalSalary;
	}

	public String getPayedInsurance() {
		return payedInsurance;
	}

	public void setDepartment(String payedInsurance) {
		this.payedInsurance = payedInsurance;
	}

	public String getSubLunch() {
		return subLunch;
	}

	public void setSubLunch(String subLunch) {
		this.subLunch = subLunch;
	}

	public String getSubPhone() {
		return subPhone;
	}

	public void setSubPhone(String subPhone) {
		this.subPhone = subPhone;
	}

	public String getSubGas() {
		return subGas;
	}

	public void setSubGas(String subGas) {
		this.subGas = subGas;
	}

	public String getSubSkill() {
		return subSkill;
	}

	public void setSubSkill(String subSkill) {
		this.subSkill = subSkill;
	}

	public String getOverWork() {
		return overWork;
	}

	public void setOverWork(String overWork) {
		this.overWork = overWork;
	}	
	
}