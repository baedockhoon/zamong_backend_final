package com.zamong.ma.service;

import java.sql.Date;

public class MusicAwardDTO {
	private String ma_no;
	private java.sql.Date ma_regidate;
	private String ma_title;
	private String ma_contents;
	private String ma_endofday;
	
	/*MA_NO
	MA_REGIDATE
	MA_TITLE
	MA_CONTENT
	MA_REMAININGDAY*/
	
	public MusicAwardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MusicAwardDTO(String ma_no, Date ma_regidate, String ma_title, String ma_contents, String ma_endofday) {
		super();
		this.ma_no = ma_no;
		this.ma_regidate = ma_regidate;
		this.ma_title = ma_title;
		this.ma_contents = ma_contents;
		this.ma_endofday = ma_endofday;
	}
	public String getMa_no() {
		return ma_no;
	}
	public void setMa_no(String ma_no) {
		this.ma_no = ma_no;
	}
	public java.sql.Date getMa_regidate() {
		return ma_regidate;
	}
	public void setMa_regidate(java.sql.Date ma_regidate) {
		this.ma_regidate = ma_regidate;
	}
	public String getMa_title() {
		return ma_title;
	}
	public void setMa_title(String ma_title) {
		this.ma_title = ma_title;
	}
	public String getMa_contents() {
		return ma_contents;
	}
	public void setMa_contents(String ma_contents) {
		this.ma_contents = ma_contents;
	}
	public String getMa_endofday() {
		return ma_endofday;
	}
	public void setMa_endofday(String ma_endofday) {
		this.ma_endofday = ma_endofday;
	}

	
	
	

}
