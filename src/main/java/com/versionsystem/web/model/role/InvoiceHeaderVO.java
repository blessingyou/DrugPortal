package com.versionsystem.web.model.role;

public class InvoiceHeaderVO {
	private String invoiceNo;
	private String clientName;
	private String tel;
	private String address;
	private String consultant;
	private String designer;
	private Double deposite1;
	private Double deposite2;
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConsultant() {
		return consultant;
	}
	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}
	public String getDesigner() {
		return designer;
	}
	public void setDesigner(String designer) {
		this.designer = designer;
	}
	public Double getDeposite1() {
		return deposite1;
	}
	public void setDeposite1(Double deposite1) {
		this.deposite1 = deposite1;
	}
	public Double getDeposite2() {
		return deposite2;
	}
	public void setDeposite2(Double deposite2) {
		this.deposite2 = deposite2;
	}

}
