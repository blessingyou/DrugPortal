package com.versionsystem.web.model.menu;

import java.sql.Timestamp;



public class MenuAccessUI {
	
	private long id;

	private long menuId;
	private Timestamp createDate;

	
	private String createUser;

	
	private Timestamp lastUpdateDate;

	
	private String lastUpdateUser;

	
	private String parameterKey;

	
	private String parameterValue;
	
	private Boolean ticked;


	public Boolean getTicked() {
		return ticked;
	}


	public void setTicked(Boolean ticked) {
		this.ticked = ticked;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getMenuId() {
		return menuId;
	}


	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}


	public Timestamp getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}


	public String getCreateUser() {
		return createUser;
	}


	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}


	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


	public String getLastUpdateUser() {
		return lastUpdateUser;
	}


	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}


	public String getParameterKey() {
		return parameterKey;
	}


	public void setParameterKey(String parameterKey) {
		this.parameterKey = parameterKey;
	}


	public String getParameterValue() {
		return parameterValue;
	}


	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}


}
