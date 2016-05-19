package com.versionsystem.web.model.drug;

import java.io.Serializable;
import java.math.BigDecimal;





public class WDrugMappingUI implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long id;

	
	private String drugBrand;

	
	private String drugCode;

	
	private String mappingBrand;

	
	private String mappingCode;

	
	private String orgCode;
	
	private String mappingName;
	private String mappingUnit;
	private BigDecimal mappingPrice;

	public WDrugMappingUI() {
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

	public String getMappingBrand() {
		return this.mappingBrand;
	}

	public void setMappingBrand(String mappingBrand) {
		this.mappingBrand = mappingBrand;
	}

	public String getMappingCode() {
		return this.mappingCode;
	}

	public void setMappingCode(String mappingCode) {
		this.mappingCode = mappingCode;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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