package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the W_DRUG_MAPPING_ALERT database table.
 * 
 */
@Entity
@Table(name="W_DRUG_MAPPING_ALERT")
@NamedQuery(name="WDrugMappingAlert.findAll", query="SELECT w FROM WDrugMappingAlert w")
public class WDrugMappingAlert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="DRUG_BRAND")
	private String drugBrand;

	@Column(name="DRUG_CODE")
	private String drugCode;

	@Column(name="DRUG_NAME")
	private String drugName;

	@Column(name="DRUG_PRICE")
	private BigDecimal drugPrice;

	@Column(name="DRUG_UNIT")
	private String drugUnit;

	@Column(name="MAP_BY")
	private String mapBy;

	@Column(name="MAP_DATE")
	private Date mapDate;

	@Column(name="MAP_FLAG")
	private Boolean mapFlag;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="REQUEST_BY")
	private String requestBy;

	
	@Column(name="REQUEST_DATE")
	private Date requestDate;
	
	@Column(name="MAPPING_CODE")
	private String mappingCode;
	
	@Column(name="MAPPING_BRAND")
	private String mappingBrand;
	
	@Column(name="MAPPING_NAME")
	private String mappingName;
	
	@Column(name="MAPPING_UNIT")
	private String mappingUnit;
	
	@Column(name="MAPPING_PRICE")
	private BigDecimal mappingPrice;

	public WDrugMappingAlert() {
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