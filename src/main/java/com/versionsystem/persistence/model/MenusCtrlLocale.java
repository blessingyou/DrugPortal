package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the MENUS_CTRL_LOCALE database table.
 * 
 */
@Entity
@Table(name="W_MENUS_CTRL_LOCALE")
@NamedQuery(name="MenusCtrlLocale.findAll", query="SELECT m FROM MenusCtrlLocale m")
public class MenusCtrlLocale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="APPROVE_DATE")
	private Timestamp approveDate;

	@Column(name="APPROVE_USER")
	private String approveUser;

	@Column(name="CREATE_DATE")
	private Timestamp createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="FORM_LABEL")
	private String formLabel;

	@Column(name="LAST_UPDATE_DATE")
	private Timestamp lastUpdateDate;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	private String locale;

	//bi-directional many-to-one association to MenusCtrl
	@ManyToOne
	@JoinColumn(name="MENU_ID")
	private MenusCtrl menusCtrl;

	public MenusCtrlLocale() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getApproveDate() {
		return this.approveDate;
	}

	public void setApproveDate(Timestamp approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproveUser() {
		return this.approveUser;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
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

	public String getFormLabel() {
		return this.formLabel;
	}

	public void setFormLabel(String formLabel) {
		this.formLabel = formLabel;
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

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public MenusCtrl getMenusCtrl() {
		return this.menusCtrl;
	}

	public void setMenusCtrl(MenusCtrl menusCtrl) {
		this.menusCtrl = menusCtrl;
	}

}