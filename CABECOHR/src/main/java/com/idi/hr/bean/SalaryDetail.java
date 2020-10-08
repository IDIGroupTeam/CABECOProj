package com.idi.hr.bean;

import java.io.Serializable;

public class SalaryDetail implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -1871003330726286159L;
	
	private int employeeId; 
	private String workGroup;
	private String finalSalary;
	private String basicSalary;
	private String unionFee;
	private String subInsurance;
	private String workedTime;
	private String workedTimePrice;
	private String overTimeSalary;
	private float salaryPerHour;
	private String bounus;
	private String maintainDay;
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
	private String desc;
	private String payedInsurance;
	private String fullName;
	private String phoneNo;
	private String bankNo;
	private String bankName;
	private String bankBranch;
	private String salary;
	private String group;
	private String department;
	private String jobTitle;
	private String salaryInsurance;
	private String percentCompanyPay;
	private String percentEmployeePay;
	private int workComplete;
	private String workedDay;
	private String salaryForWorkedDay;
	private String other;
	private String arrears;
	private String payStatus;
	private String totalReduce;
	private String totalIncome;
	private String rSalary; //hs luong
	private String saProduct;
	private String saTime;
		
	public SalaryDetail() {
		
	}

	public SalaryDetail(int employeeId, String workGroup, String basicSalary, String finalSalary, String unionFee, String subInsurance, String workedTime, String workedTimePrice,
			String overTimeSalary, String bounus, String maintainDay, String subsidize, String subLunch, String subPhone, String subGas, 
			String subSkill, String overWork, String advancePayed, String taxPersonal, int month, int year, String totalIncome, String totalReduce,
			String description, String payedInsurance, String fullName, String phoneNo, String bankNo, String bankName, String bankBranch,
			String salary, String group, String department, String jobTitle, String salaryInsurance, String percentCompanyPay, String percentEmployeePay,
			int workComplete, String workedDay, String other, String arrears, String payStatus, String rSalary, String saProduct, String saTime) {
		
		this.employeeId = employeeId;
		this.workGroup = workGroup;
		this.finalSalary = finalSalary;
		this.basicSalary = basicSalary;
		this.unionFee = unionFee;
		this.subInsurance = subInsurance;
		this.workedTime = workedTime;
		this.workedTimePrice = workedTimePrice;
		this.overTimeSalary = overTimeSalary;
		this.bounus = bounus;
		this.maintainDay = maintainDay;
		this.subsidize = subsidize;
		this.subLunch = subLunch;
		this.subPhone = subPhone;
		this.subGas = subGas;
		this.subSkill = subSkill;
		this.overWork = overWork;
		this.advancePayed = advancePayed;
		this.taxPersonal = taxPersonal;
		this.totalIncome = totalIncome;
		this.totalReduce = totalReduce;
		this.month = month;
		this.year = year;
		this.desc = description;		
		this.payedInsurance = payedInsurance;
		
		this.fullName = fullName;
		this.phoneNo = phoneNo;
		this.bankNo = bankNo;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.salary = salary;
		this.group = group;
		this.department = department;
		this.jobTitle = jobTitle;
		this.salaryInsurance = salaryInsurance;
		this.percentCompanyPay = percentCompanyPay;
		this.percentEmployeePay = percentEmployeePay;
		this.workComplete = workComplete;
		this.workedDay = workedDay;
		this.other = other;
		this.arrears = arrears;
		this.payStatus = payStatus;
		this.rSalary = rSalary;
		this.saProduct = saProduct;
		this.saTime = saTime;
	}	

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
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

	public String getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}

	public String getUnionFee() {
		return unionFee;
	}

	public void setUnionFee(String unionFee) {
		this.unionFee = unionFee;
	}

	public String getSubInsurance() {
		return subInsurance;
	}

	public void setSubInsurance(String subInsurance) {
		this.subInsurance = subInsurance;
	}

	public String getWorkedTime() {
		return workedTime;
	}

	public String getWorkedTimePrice() {
		return workedTimePrice;
	}

	public void setWorkedTimePrice(String workedTimePrice) {
		this.workedTimePrice = workedTimePrice;
	}

	public void setWorkedTime(String workedTime) {
		this.workedTime = workedTime;
	}

	public String getOverTimeSalary() {
		return overTimeSalary;
	}

	public void setOverTimeSalary(String overTimeSalary) {
		this.overTimeSalary = overTimeSalary;
	}

	public float getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(float salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
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

	public String getDesc() {
		return desc;
	}
	
	public String getPayedInsurance() {
		return payedInsurance;
	}

	public void setPayedInsurance(String payedInsurance) {
		this.payedInsurance = payedInsurance;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
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

	public String getSalaryInsurance() {
		return salaryInsurance;
	}

	public void setSalaryInsurance(String salaryInsurance) {
		this.salaryInsurance = salaryInsurance;
	}

	public String getPercentCompanyPay() {
		return percentCompanyPay;
	}

	public void setPercentCompanyPay(String percentCompanyPay) {
		this.percentCompanyPay = percentCompanyPay;
	}

	public String getPercentEmployeePay() {
		return percentEmployeePay;
	}

	public void setPercentEmployeePay(String percentEmployeePay) {
		this.percentEmployeePay = percentEmployeePay;
	}

	public int getWorkComplete() {
		return workComplete;
	}

	public void setWorkComplete(int workComplete) {
		this.workComplete = workComplete;
	}

	public String getWorkedDay() {
		return workedDay;
	}

	public void setWorkedDay(String workedDay) {
		this.workedDay = workedDay;
	}

	public String getSalaryForWorkedDay() {
		return salaryForWorkedDay;
	}

	public void setSalaryForWorkedDay(String salaryForWorkedDay) {
		this.salaryForWorkedDay = salaryForWorkedDay;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getArrears() {
		return arrears;
	}

	public void setArrears(String arrears) {
		this.arrears = arrears;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
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

	public String getTotalReduce() {
		return totalReduce;
	}

	public void setTotalReduce(String totalReduce) {
		this.totalReduce = totalReduce;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getMaintainDay() {
		return maintainDay;
	}

	public void setMaintainDay(String maintainDay) {
		this.maintainDay = maintainDay;
	}

	public String getrSalary() {
		return rSalary;
	}

	public void setrSalary(String rSalary) {
		this.rSalary = rSalary;
	}

	public String getSaProduct() {
		return saProduct;
	}

	public void setSaProduct(String saProduct) {
		this.saProduct = saProduct;
	}

	public String getSaTime() {
		return saTime;
	}

	public void setSaTime(String saTime) {
		this.saTime = saTime;
	}
		
}