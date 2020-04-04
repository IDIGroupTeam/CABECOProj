package com.idi.hr.bean;

import java.io.Serializable;

public class Salary implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -1871003330726286155L;
	
	private String salary;
	private int employeeId; 
	private String constSalary;
	private String bankNo;
	private String bankName;
	private String bankBranch;
	private String fullName; 
	private String department;
	private String group;
	private String jobTitle;
	private String desc;
	private int month;
	private int year;
	
	public Salary() {
		
	}

	public Salary(int employeeId, String fullName, String salary, String constSalary, String bankNo, 
			String bankName, String bankBranch, String group, String department, String jobTitle, String description) {
		this.employeeId = employeeId;
		this.fullName = fullName;
		this.salary = salary;
		this.constSalary = constSalary;
		this.bankNo = bankNo;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.group = group;
		this.department = department;
		this.jobTitle = jobTitle;
		this.desc = description;		
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

}