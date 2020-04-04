package com.idi.hr.bean;

import java.io.Serializable;

public class SalaryRe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080179696590953048L;

	private int month;
	private int year;
	private String value;
	private String group;
	private String groupName;
	private String des;

	public SalaryRe() {

	}

	public SalaryRe(int month, int year, String value, String group, String des) {
		this.month = month;
		this.year = year;
		this.value = value;
		this.group = group;
		this.des = des;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
