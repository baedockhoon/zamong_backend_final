package com.zamong.mv.service;

import java.sql.Date;

public class MusicVideoDTO {

	private String mv_no;
	private java.sql.Date mv_regidate;
	private String at_no;
	private String ss_no;
	private String mv_title;
	private String mv_contents;
	private String mv_link;
	private String mv_hitcount;
	private String mv_image;
	
	/*	MV_NO
	MV_REGIDATE
	AT_NO
	SS_NO
	MV_TITLE
	MV_CONTENTS
	MV_LINK
	MV_HITCOUNT*/
	
	public MusicVideoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MusicVideoDTO(String mv_no, Date mv_regidate, String at_no, String ss_no, String mv_title,
			String mv_contents, String mv_link, String mv_hitcount, String mv_image) {
		super();
		this.mv_no = mv_no;
		this.mv_regidate = mv_regidate;
		this.at_no = at_no;
		this.ss_no = ss_no;
		this.mv_title = mv_title;
		this.mv_contents = mv_contents;
		this.mv_link = mv_link;
		this.mv_hitcount = mv_hitcount;
		this.mv_image = mv_image;
	}
	
	
	public String getMv_no() {
		return mv_no;
	}
	public void setMv_no(String mv_no) {
		this.mv_no = mv_no;
	}
	public java.sql.Date getMv_regidate() {
		return mv_regidate;
	}
	public void setMv_regidate(java.sql.Date mv_regidate) {
		this.mv_regidate = mv_regidate;
	}
	public String getAt_no() {
		return at_no;
	}
	public void setAt_no(String at_no) {
		this.at_no = at_no;
	}
	public String getSs_no() {
		return ss_no;
	}
	public void setSs_no(String ss_no) {
		this.ss_no = ss_no;
	}
	public String getMv_title() {
		return mv_title;
	}
	public void setMv_title(String mv_title) {
		this.mv_title = mv_title;
	}
	public String getMv_contents() {
		return mv_contents;
	}
	public void setMv_contents(String mv_contents) {
		this.mv_contents = mv_contents;
	}
	public String getMv_link() {
		return mv_link;
	}
	public void setMv_link(String mv_link) {
		this.mv_link = mv_link;
	}
	public String getMv_hitcount() {
		return mv_hitcount;
	}
	public void setMv_hitcount(String mv_hitcount) {
		this.mv_hitcount = mv_hitcount;
	}
	
	public String getMv_image() {
		return mv_image;
	}
	public void setMv_image(String mv_image) {
		this.mv_image = mv_image;
	}
	
}
