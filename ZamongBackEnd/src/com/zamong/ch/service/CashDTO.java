package com.zamong.ch.service;

import java.util.Date;

/*
 * CH_NO                                     NOT NULL NUMBER
CH_REGIDATE                               NOT NULL DATE
ME_NO                                     NOT NULL NUMBER
BP_NO                                     NOT NULL NUMBER
CH_HAVECASH                               NOT NULL NUMBER
 */
public class CashDTO {
	private String me_id;
private String ch_no;
private java.util.Date ch_regidate;
private String me_no;
private String ch_havecash;


public CashDTO() {	
}


public String getMe_id() {
	return me_id;
}


public void setMe_id(String me_id) {
	this.me_id = me_id;
}


public String getCh_no() {
	return ch_no;
}


public void setCh_no(String ch_no) {
	this.ch_no = ch_no;
}


public java.util.Date getCh_regidate() {
	return ch_regidate;
}


public void setCh_regidate(java.util.Date ch_regidate) {
	this.ch_regidate = ch_regidate;
}


public String getMe_no() {
	return me_no;
}


public void setMe_no(String me_no) {
	this.me_no = me_no;
}


public String getCh_havecash() {
	return ch_havecash;
}


public void setCh_havecash(String ch_havecash) {
	this.ch_havecash = ch_havecash;
}


public CashDTO(String me_id, String ch_no, Date ch_regidate, String me_no, String ch_havecash) {
	this.me_id = me_id;
	this.ch_no = ch_no;
	this.ch_regidate = ch_regidate;
	this.me_no = me_no;
	this.ch_havecash = ch_havecash;
}



}
