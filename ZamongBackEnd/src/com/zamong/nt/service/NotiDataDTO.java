package com.zamong.nt.service;

import java.util.Date;

public class NotiDataDTO {

	/*
	 *  NT_NO                                     NOT NULL NUMBER
 NT_REGIDATE                               NOT NULL DATE
 AD_NO                                     NOT NULL NUMBER
 NT_CLASSIFICATION                         NOT NULL VARCHAR2(20)
 NT_TITLE                                  NOT NULL NVARCHAR2(30)
 NT_CONTENTS                               NOT NULL NVARCHAR2(2000)
 NT_HITCOUNT                               NOT NULL NUMBER
	 */
	//공지사항
	private String nt_no;//키값
	private java.util.Date nt_regidate;//날짜
	private String ad_no;//관리자 키값
	private String nt_classification;//분류(서비스소식,종료,오픈등등)
	private String nt_title;//제목
	private String nt_contents;//내용
	private String nt_hitcount;//조회수
	
	public NotiDataDTO() {}

	public NotiDataDTO(String nt_no, Date nt_regidate, String ad_no, String nt_classification, String nt_title,
			String nt_contents, String nt_hitcount) {

		this.nt_no = nt_no;
		this.nt_regidate = nt_regidate;
		this.ad_no = ad_no;
		this.nt_classification = nt_classification;
		this.nt_title = nt_title;
		this.nt_contents = nt_contents;
		this.nt_hitcount = nt_hitcount;
	}

	public String getNt_no() {
		return nt_no;
	}

	public void setNt_no(String nt_no) {
		this.nt_no = nt_no;
	}

	public java.util.Date getNt_regidate() {
		return nt_regidate;
	}

	public void setNt_regidate(java.util.Date nt_regidate) {
		this.nt_regidate = nt_regidate;
	}

	public String getAd_no() {
		return ad_no;
	}

	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}

	public String getNt_classification() {
		return nt_classification;
	}

	public void setNt_classification(String nt_classification) {
		this.nt_classification = nt_classification;
	}

	public String getNt_title() {
		return nt_title;
	}

	public void setNt_title(String nt_title) {
		this.nt_title = nt_title;
	}

	public String getNt_contents() {
		return nt_contents;
	}

	public void setNt_contents(String nt_contents) {
		this.nt_contents = nt_contents;
	}

	public String getNt_hitcount() {
		return nt_hitcount;
	}

	public void setNt_hitcount(String nt_hitcount) {
		this.nt_hitcount = nt_hitcount;
	}

	
	
	
}