package com.versionsystem.web.model.role;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class MultiFileUploadBean {

	private CommonsMultipartFile[] file;
	private String roleDesc;

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public CommonsMultipartFile[] getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile[] file) {
		this.file = file;
	}

	
}
