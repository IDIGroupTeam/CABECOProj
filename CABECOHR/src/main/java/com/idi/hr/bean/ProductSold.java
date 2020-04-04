package com.idi.hr.bean;

import java.io.Serializable;

public class ProductSold implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080179676590953048L;

	private String code;
	private String name;
	private String price;
	private String amount;
	private String month;
	private String scale;
	private String moneyIncome;
	private String group;
	private String comment;

	public ProductSold() {

	}

	public ProductSold(String code, String name, String price, String amount, String month, String scale,
			String moneyIncome, String group, String comment) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.month = month;
		this.scale = scale;
		this.moneyIncome = moneyIncome;
		this.group = group;
		this.comment = comment;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getMoneyIncome() {
		return moneyIncome;
	}

	public void setMoneyIncome(String moneyIncome) {
		this.moneyIncome = moneyIncome;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
