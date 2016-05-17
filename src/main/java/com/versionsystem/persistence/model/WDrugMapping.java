package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the W_DRUG_MAPPING database table.
 * 
 */
@Entity
@Table(name="W_DRUG_MAPPING")
@NamedQuery(name="WDrugMapping.findAll", query="SELECT w FROM WDrugMapping w")
public class WDrugMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="DRUG_BRAND")
	private String drugBrand;

	@Column(name="DRUG_CODE")
	private String drugCode;

	@Column(name="MAPPING_BRAND")
	private String mappingBrand;

	@Column(name="MAPPING_CODE")
	private String mappingCode;

	@Column(name="ORG_CODE")
	private String orgCode;

	public WDrugMapping() {
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

}