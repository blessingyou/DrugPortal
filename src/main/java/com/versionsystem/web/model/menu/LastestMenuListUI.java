package com.versionsystem.web.model.menu;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the LASTEST_MENU_LIST database table.
 * 
 */

public class LastestMenuListUI implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long id;
	private long displaySeq;
	private long menuId;
	private String userId;
	private String url;
	private String text;
	private String spriteCssClass;
	private String sysRole;
	private String allowedAction;
	
	

	public String getSysRole() {
		return sysRole;
	}




	public void setSysRole(String sysRole) {
		this.sysRole = sysRole;
	}




	public String getAllowedAction() {
		return allowedAction;
	}




	public void setAllowedAction(String allowedAction) {
		this.allowedAction = allowedAction;
	}




	public long getDisplaySeq() {
		return displaySeq;
	}




	public long getMenuId() {
		return menuId;
	}




	public LastestMenuListUI() {
	}
	
	
	

	public String getUrl() {
		return url;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	public String getText() {
		return text;
	}




	public void setText(String text) {
		this.text = text;
	}




	public String getSpriteCssClass() {
		return spriteCssClass;
	}




	public void setSpriteCssClass(String spriteCssClass) {
		this.spriteCssClass = spriteCssClass;
	}




	public void setDisplaySeq(long displaySeq) {
		this.displaySeq = displaySeq;
	}




	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}




	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}