package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the W_SECURITY_LOG database table.
 * 
 */
@Entity
@Table(name="W_SECURITY_LOG")
@NamedQuery(name="WSecurityLog.findAll", query="SELECT w FROM WSecurityLog w")
public class WSecurityLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="BROWSER_TYPE")
	private String browserType;

	@Column(name="LOGIN_IP")
	private String loginIp;

	@Column(name="LOGIN_TIME")
	private Timestamp loginTime;

	@Column(name="LOGIN_USER")
	private String loginUser;

	public WSecurityLog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrowserType() {
		return this.browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginUser() {
		return this.loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

}