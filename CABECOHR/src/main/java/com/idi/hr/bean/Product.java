package com.idi.hr.bean;

import java.io.Serializable;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080179676190953048L;

	private String code;
	private String name;
	private String comment;

	public Product() {

	}

	public Product(String code, String name, String comment) {
		this.code = code;
		this.name = name;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
