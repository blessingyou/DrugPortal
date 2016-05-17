package com.versionsystem.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.sql.Date;


/**
 * The persistent class for the USER_ID database table.
 * 
 */
@Entity
@Table(name="W_USER")
@NamedQuery(name="UserId.findAll", query="SELECT u FROM UserId u")
public class UserId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="APPROVE_DATE")
	private Timestamp approveDate;

	@Column(name="APPROVE_USER")
	private String approveUser;

	@Column(name="COMPANY_CODE")
	private String companyCode;

	@Column(name="CREATE_DATE")
	private Timestamp createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	private String email;

	@Column(name="LAST_UPDATE_DATE")
	private Timestamp lastUpdateDate;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	private String locale;

	private String password;

	@Column(name="ROLE_ID")
	private java.math.BigDecimal roleId;

	private String securitycode;

	@Column(name="SECURITYCODE_GEN_TIME")
	private Timestamp securitycodeGenTime;

	@Column(name="TEL_NO")
	private String telNo;

	@Column(name="USER_ID")
	private String userId;

	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name="AGE")
	private Integer age;
	
	@Column(name="MOBILE_DEVICE_ID")
	private String mobileDeviceId;
	
	@Column(name="MOBILE_DEVICE_TYPE")
	private String mobileDeviceType;
	
	@Column(name="DOCTOR_ID")
	private Long doctorId;

	public UserId() {
	}
		

	public Long getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}


	public String getMobileDeviceId() {
		return mobileDeviceId;
	}

	public void setMobileDeviceId(String mobileDeviceId) {
		this.mobileDeviceId = mobileDeviceId;
	}


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getApproveDate() {
		return this.approveDate;
	}

	public void setApproveDate(Timestamp approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproveUser() {
		return this.approveUser;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public java.math.BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(java.math.BigDecimal roleId) {
		this.roleId = roleId;
	}

	public String getSecuritycode() {
		return this.securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

	public Timestamp getSecuritycodeGenTime() {
		return this.securitycodeGenTime;
	}

	public void setSecuritycodeGenTime(Timestamp securitycodeGenTime) {
		this.securitycodeGenTime = securitycodeGenTime;
	}

	public String getTelNo() {
		return this.telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
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


	public String getMobileDeviceType() {
		return mobileDeviceType;
	}


	public void setMobileDeviceType(String mobileDeviceType) {
		this.mobileDeviceType = mobileDeviceType;
	}
	

}