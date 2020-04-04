package com.idi.hr.bean;

import java.io.Serializable;

public class EmployeeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6492149113228672629L;
	private int employeeId;
	private String emerName;
	private String emerPhoneNo;
	private String fullName;
	private String gender;
	private String jobTitle;
	private String DOB;
	private String maritalStatus;
	private String loginAccount;
	private String personalId;
	private String issueDate;
	private String issuePlace;
	private String department;
	private String phoneNo;
	private String joinDate;
	private String officalJoinDate;
	private String email;
	private String terminationDate;
	private String reasonforLeave;
	private String currentAdress;
	private String permanentAdress;
	private String note;
	private String nation;
	//private File image;
	private String workStatus;
	private String statusName;
	private String eUnion;
	private String expiryDate;
	private String taxCode;
	private String academyLevel;
	private String imagePath;
	private String workGroup;
	private String socicalInsuNo;
	private String healthInsuNo;
	private String percentSocicalInsu;
	private int seniority;
		
	public EmployeeInfo() {

	}

	public EmployeeInfo(int employeeId, String loginAccount, String gender, String fullName, String maritalStatus,
			String jobTitle, String department, String DOB, String personalId, String issueDate, String issuePlace, String phoneNo,
			String joinDate, String officalJoinDate, String email, String terminationDate, String reasonforLeave,
			String currentAdress, String permanentAdress, String note, String emerName, String emerPhoneNo,
			String nation, String workStatus, String statusName, String eUnion, String taxCode, String expiryDate, String academyLevel,
			String imagePath, String workGroup, String socicalInsuNo, String healthInsuNo,
			String percentSocicalInsu) {

		this.employeeId = employeeId;
		this.loginAccount = loginAccount;
		this.gender = gender;
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.department = department;
		this.DOB = DOB;
		this.maritalStatus = maritalStatus;
		this.personalId = personalId;
		this.issueDate = issueDate;
		this.issuePlace = issuePlace;
		this.phoneNo = phoneNo;
		this.joinDate = joinDate;
		this.officalJoinDate = officalJoinDate;
		this.email = email;
		this.terminationDate = terminationDate;
		this.reasonforLeave = reasonforLeave;
		this.currentAdress = currentAdress;
		this.permanentAdress = permanentAdress;
		this.note = note;
		this.emerName = emerName;
		this.emerPhoneNo = emerPhoneNo;
		this.nation = nation;
		//this.image = image;
		this.workStatus = workStatus;
		this.statusName = statusName;
		this.eUnion = eUnion;
		this.taxCode = taxCode;
		this.expiryDate = expiryDate;
		this.academyLevel = academyLevel;
		this.imagePath = imagePath;
		this.workGroup = workGroup;
		this.socicalInsuNo = socicalInsuNo;
		this.healthInsuNo = healthInsuNo;
		this.percentSocicalInsu = percentSocicalInsu;
	}

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public String getIssuePlace() {
		return issuePlace;
	}

	public void setIssuePlace(String issuePlace) {
		this.issuePlace = issuePlace;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getOfficalJoinDate() {
		return officalJoinDate;
	}

	public void setOfficalJoinDate(String officalJoinDate) {
		this.officalJoinDate = officalJoinDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getReasonforLeave() {
		return reasonforLeave;
	}

	public void setReasonforLeave(String reasonforLeave) {
		this.reasonforLeave = reasonforLeave;
	}

	public String getCurrentAdress() {
		return currentAdress;
	}

	public void setCurrentAdress(String currentAdress) {
		this.currentAdress = currentAdress;
	}

	public String getPermanentAdress() {
		return permanentAdress;
	}

	public void setPermanentAdress(String permanentAdress) {
		this.permanentAdress = permanentAdress;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

/*	public File getImage() {
		return image;
	}*/

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getEmerName() {
		return emerName;
	}

	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}

	public String getEmerPhoneNo() {
		return emerPhoneNo;
	}

	public void setEmerPhoneNo(String emerPhoneNo) {
		this.emerPhoneNo = emerPhoneNo;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
		
	public String geteUnion() {
		return eUnion;
	}

	public void seteUnion(String eUnion) {
		this.eUnion = eUnion;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getAcademyLevel() {
		return academyLevel;
	}

	public void setAcademyLevel(String academyLevel) {
		this.academyLevel = academyLevel;
	}

	public String getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}

	public String getSocicalInsuNo() {
		return socicalInsuNo;
	}

	public void setSocicalInsuNo(String socicalInsuNo) {
		this.socicalInsuNo = socicalInsuNo;
	}

	public String getHealthInsuNo() {
		return healthInsuNo;
	}

	public void setHealthInsuNo(String healthInsuNo) {
		this.healthInsuNo = healthInsuNo;
	}

	public String getPercentSocicalInsu() {
		return percentSocicalInsu;
	}

	public void setPercentSocicalInsu(String percentSocicalInsu) {
		this.percentSocicalInsu = percentSocicalInsu;
	}
	
/*	public void setImage(File image) {
		this.image = image;
	}*/

	/*	public void setImage(InputStream input) {
		try {
			if (input != null) {
				ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
				byte[] dataBuffer = new byte[4096];
				int numberBytes = 0;
				while ((numberBytes = input.read(dataBuffer, 0, 4096)) != -1) {
					byteStream.write(dataBuffer, 0, numberBytes);
				}
				input.close();
				image = byteStream.toByteArray();
			}
		} catch (IOException e) {}
	}*/

}
