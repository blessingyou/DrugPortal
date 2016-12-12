package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the W_DRUG_APP_FORM_LOG database table.
 * 
 */
@Entity
@Table(name="W_DRUG_APP_FORM_LOG")
@NamedQuery(name="WDrugAppFormLog.findAll", query="SELECT w FROM WDrugAppFormLog w")
public class WDrugAppFormLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="W_DRUG_APP_FORM_LOG_ID_GENERATOR", sequenceName="LOG_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="W_DRUG_APP_FORM_LOG_ID_GENERATOR")
	private long id;

	@Column(name="ACTION_BY")
	private String actionBy;

	@Column(name="ACTION_DATE")
	private Date actionDate;

	@Column(name="ACTION_TYPE")
	private String actionType;

	@Column(name="APP_BY")
	private String appBy;

	@Column(name="APP_CODE")
	private String appCode;

	
	@Column(name="APP_DATE")
	private Date appDate;

	@Column(name="APP_ID")
	private long appId;

	@Column(name="APP_PRICE")
	private BigDecimal appPrice;

	@Column(name="APP_STATUS")
	private String appStatus;

	@Column(name="DOCTOR_CODE")
	private String doctorCode;

	@Column(name="DRUG_BRAND")
	private String drugBrand;

	@Column(name="DRUG_CODE")
	private String drugCode;

	@Column(name="DRUG_DURATION")
	private BigDecimal drugDuration;

	@Column(name="DRUG_NAME")
	private String drugName;

	@Column(name="DRUG_PRICE")
	private BigDecimal drugPrice;

	@Column(name="DRUG_QTY")
	private BigDecimal drugQty;

	@Column(name="DRUG_UNIT")
	private String drugUnit;

	@Temporal(TemporalType.DATE)
	@Column(name="INCURRED_DATE")
	private Date incurredDate;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="REQUEST_BY")
	private String requestBy;

	
	@Column(name="REQUEST_DATE")
	private Date requestDate;

	@Column(name="VOUCHER_NO")
	private String voucherNo;

	public WDrugAppFormLog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionBy() {
		return this.actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public Date getActionDate() {
		return this.actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAppBy() {
		return this.appBy;
	}

	public void setAppBy(String appBy) {
		this.appBy = appBy;
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

	public long getAppId() {
		return this.appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
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

	public String getDoctorCode() {
		return this.doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
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

	public Date getIncurredDate() {
		return this.incurredDate;
	}

	public void setIncurredDate(Date incurredDate) {
		this.incurredDate = incurredDate;
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

	public String getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

}