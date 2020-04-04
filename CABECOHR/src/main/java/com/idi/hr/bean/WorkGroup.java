package com.idi.hr.bean;

import java.io.Serializable;

public class WorkGroup implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -3871003330721186155L;
	
	private String groupId;
	private String groupName;
	private String desc;
	
	public WorkGroup() {
		
	}

	public WorkGroup(String groupId, String groupName, String description) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.desc = description;		
	}
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}	

}