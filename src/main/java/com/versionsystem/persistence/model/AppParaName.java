package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the APP_PARA_NAME database table.
 * 
 */
@Entity
@Table(name="APP_MENU_ACCESS_NAME")
@NamedQuery(name="AppParaName.findAll", query="SELECT a FROM AppParaName a")
public class AppParaName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PARAMETER_KEY")
	private String parameterKey;

	private String name;

	@Column(name="NAME_L2")
	private String nameL2;

	@Column(name="NAME_L3")
	private String nameL3;

	public AppParaName() {
	}

	public String getParameterKey() {
		return this.parameterKey;
	}

	public void setParameterKey(String parameterKey) {
		this.parameterKey = parameterKey;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameL2() {
		return this.nameL2;
	}

	public void setNameL2(String nameL2) {
		this.nameL2 = nameL2;
	}

	public String getNameL3() {
		return this.nameL3;
	}

	public void setNameL3(String nameL3) {
		this.nameL3 = nameL3;
	}

}