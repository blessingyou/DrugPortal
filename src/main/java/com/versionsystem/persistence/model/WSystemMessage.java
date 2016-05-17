package com.versionsystem.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the W_SYSTEM_MESSAGE database table.
 * 
 */
@Entity
@Table(name="W_SYSTEM_MESSAGE")
@NamedQuery(name="WSystemMessage.findAll", query="SELECT w FROM WSystemMessage w")
public class WSystemMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	
	private Timestamp lastUpdateDate;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name="MSG_CONTENT")
	private String msgContent;

	@Column(name="MSG_TITLE")
	private String msgTitle;

	@Column(name="MSG_TYPE")
	private String msgType;

	public WSystemMessage() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgTitle() {
		return this.msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}