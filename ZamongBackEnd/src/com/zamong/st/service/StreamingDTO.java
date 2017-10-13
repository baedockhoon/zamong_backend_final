package com.zamong.st.service;

import java.util.Date;

public class StreamingDTO {
/*	ST_NO                                     NOT NULL NUMBER
	ST_START_DATE                                      DATE
	ST_END_DATE                                        DATE
	BP_NO                                     NOT NULL NUMBER
	ME_NO                                     NOT NULL NUMBER*/
	
	
	private String me_id;
	private  String st_no;
	private java.util.Date st_start_date;
	private java.util.Date st_end_date;
	private String bp_no;
	private String me_no;
	private String pd_name;
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	public String getSt_no() {
		return st_no;
	}
	public void setSt_no(String st_no) {
		this.st_no = st_no;
	}
	public java.util.Date getSt_start_date() {
		return st_start_date;
	}
	public void setSt_start_date(java.util.Date st_start_date) {
		this.st_start_date = st_start_date;
	}
	public java.util.Date getSt_end_date() {
		return st_end_date;
	}
	public void setSt_end_date(java.util.Date st_end_date) {
		this.st_end_date = st_end_date;
	}
	public String getBp_no() {
		return bp_no;
	}
	public void setBp_no(String bp_no) {
		this.bp_no = bp_no;
	}
	public String getMe_no() {
		return me_no;
	}
	public void setMe_no(String me_no) {
		this.me_no = me_no;
	}
	
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public StreamingDTO(String me_id, String st_no, Date st_start_date, Date st_end_date, String bp_no, String me_no,String pd_name) {

		this.me_id = me_id;
		this.st_no = st_no;
		this.st_start_date = st_start_date;
		this.st_end_date = st_end_date;
		this.bp_no = bp_no;
		this.me_no = me_no;
		this.pd_name = pd_name;
	}
	public StreamingDTO() {}
	
	

}
