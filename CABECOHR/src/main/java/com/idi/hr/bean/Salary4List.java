package com.idi.hr.bean;

import java.io.Serializable;

public class Salary4List implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -1871007330726286155L;
	
	private String salary;
	private int employeeId; 
	private String constSalary;
	private String bankNo;
	private String bankName;
	private String bankBranch;
	private String fullName; 
	private String department;
	private String jobTitle;
	private String desc;
	private String workedDay;
	private String actualSalary;
	
	public Salary4List() {
		
	}

	public Salary4List(int employeeId, String fullName, String salary, String constSalary, String bankNo, 
			String bankName, String bankBranch, String department, String jobTitle, String description,
			String workedDay, String actualSalary) {
		this.employeeId = employeeId;
		this.fullName = fullName;
		this.salary = salary;
		this.constSalary = constSalary;
		this.bankNo = bankNo;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.department = department;
		this.jobTitle = jobTitle;
		this.desc = description;	
		this.workedDay = workedDay;
		this.actualSalary = actualSalary;
	}	

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getConstSalary() {
		return constSalary;
	}

	public void setConstSalary(String constSalary) {
		this.constSalary = constSalary;
	}

	public String getWorkedDay() {
		return workedDay;
	}

	public void setWorkedDay(String workedDay) {
		this.workedDay = workedDay;
	}

	public String getActualSalary() {
		return actualSalary;
	}

	public void setActualSalary(String actualSalary) {
		this.actualSalary = actualSalary;
	}

}