package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the W_ACCESS_SERVICE_LOG database table.
 * 
 */
@Entity
@Table(name="W_ACCESS_SERVICE_LOG")
@NamedQuery(name="WAccessServiceLog.findAll", query="SELECT w FROM WAccessServiceLog w")
public class WAccessServiceLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="W_ACCESS_SERVICE_LOG_ID_GENERATOR", sequenceName="LOG_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="W_ACCESS_SERVICE_LOG_ID_GENERATOR")
	private long id;

	
	@Column(name="ACTION_DATE")
	private Date actionDate;

	@Column(name="ACTION_IP")
	private String actionIp;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="SERVICE_KEY")
	private String serviceKey;

	public WAccessServiceLog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getActionDate() {
		return this.actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionIp() {
		return this.actionIp;
	}

	public void setActionIp(String actionIp) {
		this.actionIp = actionIp;
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