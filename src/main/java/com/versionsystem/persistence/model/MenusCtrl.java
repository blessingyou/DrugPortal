package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the MENUS_CTRL database table.
 * 
 */
@Entity
@Table(name="W_MENUS_CTRL")
@NamedQuery(name="MenusCtrl.findAll", query="SELECT m FROM MenusCtrl m")
public class MenusCtrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="ACCESS_LOCK")
	private String accessLock;

	@Column(name="ALLOWED_ACTION")
	private String allowedAction;

	@Column(name="APPROVE_DATE")
	private Timestamp approveDate;

	@Column(name="APPROVE_USER")
	private String approveUser;

	@Column(name="COMPANY_CODE")
	private String companyCode;

	@Column(name="CREATE_DATE")
	private Timestamp createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="DISPALY_SEQ_NO")
	private BigDecimal dispalySeqNo;

	@Column(name="LAST_UPDATE_DATE")
	private Timestamp lastUpdateDate;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	private String leaf;

	@Column(name="MENU_NAME")
	private String menuName;

	@Column(name="SEQ_NO")
	private String seqNo;

	@Column(name="SYS_ROLE")
	private String sysRole;

	//bi-directional many-to-one association to MenusCtrlLocale
	@OneToMany(mappedBy="menusCtrl")
	private List<MenusCtrlLocale> menusCtrlLocales;

	public MenusCtrl() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessLock() {
		return this.accessLock;
	}

	public void setAccessLock(String accessLock) {
		this.accessLock = accessLock;
	}

	public String getAllowedAction() {
		return this.allowedAction;
	}

	public void setAllowedAction(String allowedAction) {
		this.allowedAction = allowedAction;
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

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public BigDecimal getDispalySeqNo() {
		return this.dispalySeqNo;
	}

	public void setDispalySeqNo(BigDecimal dispalySeqNo) {
		this.dispalySeqNo = dispalySeqNo;
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

	public String getLeaf() {
		return this.leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(String sysRole) {
		this.sysRole = sysRole;
	}

	public List<MenusCtrlLocale> getMenusCtrlLocales() {
		return this.menusCtrlLocales;
	}

	public void setMenusCtrlLocales(List<MenusCtrlLocale> menusCtrlLocales) {
		this.menusCtrlLocales = menusCtrlLocales;
	}

	public MenusCtrlLocale addMenusCtrlLocale(MenusCtrlLocale menusCtrlLocale) {
		getMenusCtrlLocales().add(menusCtrlLocale);
		menusCtrlLocale.setMenusCtrl(this);

		return menusCtrlLocale;
	}

	public MenusCtrlLocale removeMenusCtrlLocale(MenusCtrlLocale menusCtrlLocale) {
		getMenusCtrlLocales().remove(menusCtrlLocale);
		menusCtrlLocale.setMenusCtrl(null);

		return menusCtrlLocale;
	}

}