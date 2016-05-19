package com.versionsystem.web.model.drug;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the W_DRUG_MAPPING_ALERT database table.
 * 
 */

public class WDrugMappingAlertUI implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long id;

	
	private String drugBrand;

	
	private String drugCode;

	
	private String drugName;

	
	private BigDecimal drugPrice;

	
	private String drugUnit;

	
	private String mapBy;

	
	private Date mapDate;

	
	private Boolean mapFlag;

	
	private String orgCode;

	
	private String requestBy;

	
	
	private Date requestDate;
	
	
	private String mappingCode;
	
	
	private String mappingBrand;
	
	
	private String mappingName;
	
	
	private String mappingUnit;
	
	
	private BigDecimal mappingPrice;

	public WDrugMappingAlertUI() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getDrugUnit() {
		return this.drugUnit;
	}

	public void setDrugUnit(String drugUnit) {
		this.drugUnit = drugUnit;
	}

	public String getMapBy() {
		return this.mapBy;
	}

	public void setMapBy(String mapBy) {
		this.mapBy = mapBy;
	}

	public Date getMapDate() {
		return this.mapDate;
	}

	public void setMapDate(Date mapDate) {
		this.mapDate = mapDate;
	}

	public Boolean getMapFlag() {
		return this.mapFlag;
	}

	public void setMapFlag(Boolean mapFlag) {
		this.mapFlag = mapFlag;
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

	public String getMappingCode() {
		return mappingCode;
	}

	public void setMappingCode(String mappingCode) {
		this.mappingCode = mappingCode;
	}

	public String getMappingBrand() {
		return mappingBrand;
	}

	public void setMappingBrand(String mappingBrand) {
		this.mappingBrand = mappingBrand;
	}

	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public String getMappingUnit() {
		return mappingUnit;
	}

	public void setMappingUnit(String mappingUnit) {
		this.mappingUnit = mappingUnit;
	}

	public BigDecimal getMappingPrice() {
		return mappingPrice;
	}

	public void setMappingPrice(BigDecimal mappingPrice) {
		this.mappingPrice = mappingPrice;
	}

}