package com.versionsystem.web.model.role;

import java.sql.Timestamp;


public class RoleUI {

	private long id;

	
	private Timestamp approveDate;

	
	private String approveUser;

	
	private String companyCode;

	
	private Timestamp createDate;

	
	private String createUser;

	
	private Timestamp lastUpdateDate;

	
	private String lastUpdateUser;


	private String role;

	private String roleDesc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Override
	public String toString() {
		return "RoleUI [id=" + id + ", approveDate=" + approveDate
				+ ", approveUser=" + approveUser + ", companyCode="
				+ companyCode + ", createDate=" + createDate + ", createUser="
				+ createUser + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdateUser=" + lastUpdateUser + ", role=" + role
				+ ", roleDesc=" + roleDesc + "]";
	}
	
	
}
