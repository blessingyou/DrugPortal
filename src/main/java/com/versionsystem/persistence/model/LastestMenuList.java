package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the LASTEST_MENU_LIST database table.
 * 
 */
@Entity
@Table(name="W_LASTEST_MENU_LIST")
@NamedQuery(name="LastestMenuList.findAll", query="SELECT l FROM LastestMenuList l")
public class LastestMenuList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="DISPLAY_SEQ")
	private BigDecimal displaySeq;

	@Column(name="MENU_ID")
	private long menuId;

	@Column(name="USER_ID")
	private String userId;

	public LastestMenuList() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getDisplaySeq() {
		return this.displaySeq;
	}

	public void setDisplaySeq(BigDecimal displaySeq) {
		this.displaySeq = displaySeq;
	}

	

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}