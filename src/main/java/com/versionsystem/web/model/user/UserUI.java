package com.versionsystem.web.model.user;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;



public class UserUI {

	
	private Long id;

	private java.math.BigDecimal roleId;
	private Timestamp approveDate;

	
	private String approveUser;

	
	private Timestamp createDate;

	
	private String createUser;

	private String email;

	
	private Timestamp lastUpdateDate;

	
	private String lastUpdateUser;
	private String userId;
	private String password;

	private String securitycode;

	
	private Timestamp securitycodeGenTime;

	
	private String telNo;

	
	private String userName;
	
	private Date dob;
	
	private Integer age;
	
	private String mobileDeviceId;
	private String mobileDeviceType;
	private Long doctorId;
	private String locale;


	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public UserUI() {
	}

	public UserUI(Long id, Timestamp approveDate, String approveUser,
			Timestamp createDate, String createUser, String email,
			Timestamp lastUpdateDate, String lastUpdateUser, String password,
			String securitycode, Timestamp securitycodeGenTime, String telNo,
			String userName) {
		super();
		this.id = id;
		this.approveDate = approveDate;
		this.approveUser = approveUser;
		this.createDate = createDate;
		this.createUser = createUser;
		this.email = email;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdateUser = lastUpdateUser;
		this.password = password;
		this.securitycode = securitycode;
		this.securitycodeGenTime = securitycodeGenTime;
		this.telNo = telNo;
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.math.BigDecimal getRoleId() {
		return roleId;
	}

	public void setRoleId(java.math.BigDecimal roleId) {
		this.roleId = roleId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Timestamp getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Timestamp approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproveUser() {
		return approveUser;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecuritycode() {
		return securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

	public Timestamp getSecuritycodeGenTime() {
		return securitycodeGenTime;
	}

	public void setSecuritycodeGenTime(Timestamp securitycodeGenTime) {
		this.securitycodeGenTime = securitycodeGenTime;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMobileDeviceId() {
		return mobileDeviceId;
	}

	public void setMobileDeviceId(String mobileDeviceId) {
		this.mobileDeviceId = mobileDeviceId;
	}

	public String getMobileDeviceType() {
		return mobileDeviceType;
	}

	public void setMobileDeviceType(String mobileDeviceType) {
		this.mobileDeviceType = mobileDeviceType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
