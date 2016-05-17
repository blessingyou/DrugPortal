package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the W_WEB_SERVICE_KEY database table.
 * 
 */
@Entity
@Table(name="W_WEB_SERVICE_KEY")
@NamedQuery(name="WWebServiceKey.findAll", query="SELECT w FROM WWebServiceKey w")
public class WWebServiceKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="SERVICE_KEY")
	private String serviceKey;

	public WWebServiceKey() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getServiceKey() {
		return this.serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

}