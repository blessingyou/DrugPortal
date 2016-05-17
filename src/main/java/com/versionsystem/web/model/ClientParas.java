package com.versionsystem.web.model;

public class ClientParas {
	
	private String appLocale;
	private String appDateFormat;
	private String appTimezone;
	private String appDateTimeFormat;
	private String jsonDateTimeFormat;
	private String currentUser;
	private String currentUserName;
	private String userRole;
	private int appGridPageSize=20;
	private boolean appGridSortable=true;
	private boolean appGridSelectable=true;
	private boolean appGridGroupable=true;
	private boolean appGridFilterable=true;
	private boolean appGridReorderable=true;
	private boolean appGridResizeable=true;
	private boolean appGridEditable=true;
	private boolean appGridColumnMenu=true;
	private boolean appGridPageable=true;
	
	
	
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getCurrentUserName() {
		return currentUserName;
	}
	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	public int getAppGridPageSize() {
		return appGridPageSize;
	}
	public void setAppGridPageSize(int appGridPageSize) {
		this.appGridPageSize = appGridPageSize;
	}
	public boolean isAppGridSortable() {
		return appGridSortable;
	}
	public void setAppGridSortable(boolean appGridSortable) {
		this.appGridSortable = appGridSortable;
	}
	public boolean isAppGridSelectable() {
		return appGridSelectable;
	}
	public void setAppGridSelectable(boolean appGridSelectable) {
		this.appGridSelectable = appGridSelectable;
	}
	public boolean isAppGridGroupable() {
		return appGridGroupable;
	}
	public void setAppGridGroupable(boolean appGridGroupable) {
		this.appGridGroupable = appGridGroupable;
	}
	public boolean isAppGridFilterable() {
		return appGridFilterable;
	}
	public void setAppGridFilterable(boolean appGridFilterable) {
		this.appGridFilterable = appGridFilterable;
	}
	public boolean isAppGridReorderable() {
		return appGridReorderable;
	}
	public void setAppGridReorderable(boolean appGridReorderable) {
		this.appGridReorderable = appGridReorderable;
	}
	public boolean isAppGridResizeable() {
		return appGridResizeable;
	}
	public void setAppGridResizeable(boolean appGridResizeable) {
		this.appGridResizeable = appGridResizeable;
	}
	public boolean isAppGridEditable() {
		return appGridEditable;
	}
	public void setAppGridEditable(boolean appGridEditable) {
		this.appGridEditable = appGridEditable;
	}
	public boolean isAppGridColumnMenu() {
		return appGridColumnMenu;
	}
	public void setAppGridColumnMenu(boolean appGridColumnMenu) {
		this.appGridColumnMenu = appGridColumnMenu;
	}
	public boolean isAppGridPageable() {
		return appGridPageable;
	}
	public void setAppGridPageable(boolean appGridPageable) {
		this.appGridPageable = appGridPageable;
	}
	public String getJsonDateTimeFormat() {
		return jsonDateTimeFormat;
	}
	public void setJsonDateTimeFormat(String jsonDateTimeFormat) {
		this.jsonDateTimeFormat = jsonDateTimeFormat;
	}
	public String getAppLocale() {
		return appLocale;
	}
	public void setAppLocale(String appLocale) {
		this.appLocale = appLocale;
	}
	public String getAppDateFormat() {
		return appDateFormat;
	}
	public void setAppDateFormat(String appDateFormat) {
		this.appDateFormat = appDateFormat;
	}
	public String getAppTimezone() {
		return appTimezone;
	}
	public void setAppTimezone(String appTimezone) {
		this.appTimezone = appTimezone;
	}
	public String getAppDateTimeFormat() {
		return appDateTimeFormat;
	}
	public void setAppDateTimeFormat(String appDateTimeFormat) {
		this.appDateTimeFormat = appDateTimeFormat;
	}
	
	

}
