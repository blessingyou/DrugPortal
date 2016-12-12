package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the W_MENUS_CTRL_ACCESS database table.
 * 
 */
@Entity
@Table(name="W_MENUS_CTRL_ACCESS")
@NamedQuery(name="MenusCtrlAccess.findAll", query="SELECT w FROM MenusCtrlAccess w")
public class MenusCtrlAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="CREATE_DATE")
	private Timestamp createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="LAST_UPDATE_DATE")
	private Timestamp lastUpdateDate;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name="PARAMETER_KEY")
	private String parameterKey;

	@Column(name="PARAMETER_VALUE")
	private String parameterValue;

	//bi-directional many-to-one association to WMenusCtrl
	@ManyToOne
	@JoinColumn(name="MENU_ID")
	private MenusCtrl menusCtrl;

	public MenusCtrlAccess() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
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

	public void setMenusCtrl(MenusCtrl menusCtrl) {
		this.menusCtrl = menusCtrl;
	}

	public String getParameterValue() {
		return this.parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public MenusCtrl getMenusCtrl() {
		return this.menusCtrl;
	}

	public void setWMenusCtrl(MenusCtrl menusCtrl) {
		this.menusCtrl = menusCtrl;
	}

}