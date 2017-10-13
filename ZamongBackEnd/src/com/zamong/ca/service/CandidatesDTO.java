package com.zamong.ca.service;

import java.sql.Date;

public class CandidatesDTO {
	private String ca_no;
	private java.sql.Date ca_regidate;
	private String ss_no;
	private String at_no;
	private String ma_no;
	
	
	
	public CandidatesDTO() {
		super();
	}
	
	
	public CandidatesDTO(String ca_no, Date ca_regidate, String ss_no, String at_no, String ma_no) {
		super();
		this.ca_no = ca_no;
		this.ca_regidate = ca_regidate;
		this.ss_no = ss_no;
		this.at_no = at_no;
		this.ma_no = ma_no;
	}

	public String getCa_no() {
		return ca_no;
	}
	public void setCa_no(String ca_no) {
		this.ca_no = ca_no;
	}
	public java.sql.Date getCa_regidate() {
		return ca_regidate;
	}
	public void setCa_regidate(java.sql.Date ca_regidate) {
		this.ca_regidate = ca_regidate;
	}
	public String getSs_no() {
		return ss_no;
	}
	public void setSs_no(String ss_no) {
		this.ss_no = ss_no;
	}
	public String getAt_no() {
		return at_no;
	}
	public void setAt_no(String at_no) {
		this.at_no = at_no;
	}
	public String getMa_no() {
		return ma_no;
	}
	public void setMa_no(String ma_no) {
		this.ma_no = ma_no;
	}
	
/*	CA_NO
	CA_REGIDATE
	SS_NO
	AT_NO
	MA_NO*/
	
	
}
