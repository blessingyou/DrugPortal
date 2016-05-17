package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SYSTEM_PARAMETER database table.
 * 
 */
@Entity
@Table(name="W_SYSTEM_PARAMETER")
@NamedQuery(name="SystemParameter.findAll", query="SELECT s FROM SystemParameter s")
public class SystemParameter implements Serializable {
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

	@Column(name="LAST_UPDATE_DATE")
	private Timestamp lastUpdateDate;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name="PARAMETER_DESC")
	private String parameterDesc;

	@Column(name="PARAMETER_DESC_CN")
	private String parameterDescCn;

	@Column(name="PARAMETER_DESC_HK")
	private String parameterDescHk;

	@Column(name="PARAMETER_KEY")
	private String parameterKey;

	@Column(name="PARAMETER_TYPE")
	private String parameterType;

	@Column(name="PARAMETER_VALUE")
	private String parameterValue;
	
	@Column(name="MODULE_NAME")
	private String moduleName;

	public SystemParameter() {
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

	public String getParameterDesc() {
		return this.parameterDesc;
	}

	public void setParameterDesc(String parameterDesc) {
		this.parameterDesc = parameterDesc;
	}

	public String getParameterDescCn() {
		return this.parameterDescCn;
	}

	public void setParameterDescCn(String parameterDescCn) {
		this.parameterDescCn = parameterDescCn;
	}

	public String getParameterDescHk() {
		return this.parameterDescHk;
	}

	public void setParameterDescHk(String parameterDescHk) {
		this.parameterDescHk = parameterDescHk;
	}

	public String getParameterKey() {
		return this.parameterKey;
	}

	public void setParameterKey(String parameterKey) {
		this.parameterKey = parameterKey;
	}

	public String getParameterType() {
		return this.parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getParameterValue() {
		return this.parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	

}