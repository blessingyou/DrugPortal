package com.versionsystem.mobile;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoucherVO {
	
	private String seqNo;
	private String voucherNo="V12345678";
	private String barcodeFile;
	private Image signatureImage;
	private Image signatureImageForDr;
	private Image barCodeImage;
	private Image logo1;
	private Image logo2;
	private String memberName;
	private String membershipNo;
	private String diagnosis;
	private long sickLeave;
	private Date leaveFrom;
	private Date leaveTo;
	private String doctorName="Dr.vio";
	private String doctorNo;
	private Date inCurredDate=new Date();
	
	private ArrayList<VoucherItem> items;
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getBarcodeFile() {
		return barcodeFile;
	}
	public void setBarcodeFile(String barcodeFile) {
		this.barcodeFile = barcodeFile;
	}
	public Image getSignatureImage() {
		return signatureImage;
	}
	public void setSignatureImage(Image signatureImage) {
		this.signatureImage = signatureImage;
	}
	public Image getSignatureImageForDr() {
		return signatureImageForDr;
	}
	public void setSignatureImageForDr(Image signatureImageForDr) {
		this.signatureImageForDr = signatureImageForDr;
	}
	public Image getBarCodeImage() {
		return barCodeImage;
	}
	public void setBarCodeImage(Image barCodeImage) {
		this.barCodeImage = barCodeImage;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public long getSickLeave() {
		return sickLeave;
	}
	public void setSickLeave(long sickLeave) {
		this.sickLeave = sickLeave;
	}
	public Date getLeaveFrom() {
		return leaveFrom;
	}
	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}
	public Date getLeaveTo() {
		return leaveTo;
	}
	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorNo() {
		return doctorNo;
	}
	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}
	public Date getInCurredDate() {
		return inCurredDate;
	}
	public void setInCurredDate(Date inCurredDate) {
		this.inCurredDate = inCurredDate;
	}
	public ArrayList<VoucherItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<VoucherItem> items) {
		this.items = items;
	}
	public Image getLogo1() {
		return logo1;
	}
	public void setLogo1(Image logo1) {
		this.logo1 = logo1;
	}
	public Image getLogo2() {
		return logo2;
	}
	public void setLogo2(Image logo2) {
		this.logo2 = logo2;
	}
	
	
}
