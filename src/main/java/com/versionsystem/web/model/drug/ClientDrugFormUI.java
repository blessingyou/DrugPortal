package com.versionsystem.web.model.drug;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



public class ClientDrugFormUI implements Serializable {
	private static final long serialVersionUID = 1L;


	private String apiKey;

	private String userId;

	private String orgCode;

	private String transactionNo;

	private String voucherNo;
	
	private List<ClientDrugFormItem> drugList;

	public ClientDrugFormUI() {
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public List<ClientDrugFormItem> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<ClientDrugFormItem> drugList) {
		this.drugList = drugList;
	}

	


	

}