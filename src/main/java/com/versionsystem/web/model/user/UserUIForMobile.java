package com.versionsystem.web.model.user;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;



public class UserUIForMobile {

	
	private Long id;
	private String password;
	private String securitycode;
	private String telNo;	
	private String userId;	
	private String mobileDeviceId;
	private String mobileDeviceType;
	private String email;


	public UserUIForMobile() {
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getTelNo() {
		return telNo;
	}


	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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

	
	
}
