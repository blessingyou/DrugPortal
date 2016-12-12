package com.versionsystem.web.model.drug;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class ClientDrugFormItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String drugBrand;

	private String drugCode;

	private BigDecimal drugDuration;

	private String drugName;

	private BigDecimal drugPrice;

	private BigDecimal drugQty;

	private String drugUnit;
	
	private String appCode;

	private Date appDate;

	private BigDecimal appPrice;



	

	public ClientDrugFormItem() {
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


	public String getAppCode() {
		return appCode;
	}


	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}


	public Date getAppDate() {
		return appDate;
	}


	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public BigDecimal getAppPrice() {
		return appPrice;
	}


	public void setAppPrice(BigDecimal appPrice) {
		this.appPrice = appPrice;
	}


	

}