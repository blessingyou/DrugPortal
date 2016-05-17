package com.versionsystem.web.model.drug;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class WDrugAppFormUI implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long appId;

	private String appCode;

	private Date appDate;

	private BigDecimal appPrice;

	private String appStatus;

	private String doctorCode;
	private String drugBrand;

	
	private String drugCode;

	private BigDecimal drugDuration;

	private String drugName;

	private BigDecimal drugPrice;

	
	private BigDecimal drugQty;

	private String drugUnit;
	
	private Date incurredDate;

	private String orgCode;

	private String requestBy;

	private Date requestDate;

	

	private String voucherNo;

	public WDrugAppFormUI() {
	}

	public long getAppId() {
		return this.appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public String getAppCode() {
		return this.appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Date getAppDate() {
		return this.appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public BigDecimal getAppPrice() {
		return this.appPrice;
	}

	public void setAppPrice(BigDecimal appPrice) {
		this.appPrice = appPrice;
	}

	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getDrugBrand() {
		return this.drugBrand;
	}

	public void setDrugBrand(String drugBrand) {
		this.drugBrand = drugBrand;
	}

	public String getDrugCode() {
		return this.drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public BigDecimal getDrugDuration() {
		return this.drugDuration;
	}

	public void setDrugDuration(BigDecimal drugDuration) {
		this.drugDuration = drugDuration;
	}

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public BigDecimal getDrugPrice() {
		return this.drugPrice;
	}

	public void setDrugPrice(BigDecimal drugPrice) {
		this.drugPrice = drugPrice;
	}

	public BigDecimal getDrugQty() {
		return this.drugQty;
	}

	public void setDrugQty(BigDecimal drugQty) {
		this.drugQty = drugQty;
	}

	public String getDrugUnit() {
		return this.drugUnit;
	}

	public void setDrugUnit(String drugUnit) {
		this.drugUnit = drugUnit;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRequestBy() {
		return this.requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getDoctorCode() {
		return this.doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Date getIncurredDate() {
		return incurredDate;
	}

	public void setIncurredDate(Date incurredDate) {
		this.incurredDate = incurredDate;
	}

}