package com.zamong.br.service.impl;

import java.sql.Date;

public class BroadCastDTO {
	private String br_no;
	private java.sql.Date br_regidate;
	private String at_no;
	private String br_title;
	private String br_contents;
	private String br_link;
	private String br_hitcount;
	private String br_programname;
	private String br_endof;
	private String br_image;
	public BroadCastDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BroadCastDTO(String br_no, Date br_regidate, String at_no, String br_title, String br_contents,
			String br_link, String br_hitcount, String br_programname, String br_endof, String br_image) {
		super();
		this.br_no = br_no;
		this.br_regidate = br_regidate;
		this.at_no = at_no;
		this.br_title = br_title;
		this.br_contents = br_contents;
		this.br_link = br_link;
		this.br_hitcount = br_hitcount;
		this.br_programname = br_programname;
		this.br_endof = br_endof;
		this.br_image = br_image;
	}
	public String getBr_no() {
		return br_no;
	}
	public void setBr_no(String br_no) {
		this.br_no = br_no;
	}
	public java.sql.Date getBr_regidate() {
		return br_regidate;
	}
	public void setBr_regidate(java.sql.Date br_regidate) {
		this.br_regidate = br_regidate;
	}
	public String getAt_no() {
		return at_no;
	}
	public void setAt_no(String at_no) {
		this.at_no = at_no;
	}
	public String getBr_title() {
		return br_title;
	}
	public void setBr_title(String br_title) {
		this.br_title = br_title;
	}
	public String getBr_contents() {
		return br_contents;
	}
	public void setBr_contents(String br_contents) {
		this.br_contents = br_contents;
	}
	public String getBr_link() {
		return br_link;
	}
	public void setBr_link(String br_link) {
		this.br_link = br_link;
	}
	public String getBr_hitcount() {
		return br_hitcount;
	}
	public void setBr_hitcount(String br_hitcount) {
		this.br_hitcount = br_hitcount;
	}
	public String getBr_programname() {
		return br_programname;
	}
	public void setBr_programname(String br_programname) {
		this.br_programname = br_programname;
	}
	public String getBr_endof() {
		return br_endof;
	}
	public void setBr_endof(String br_endof) {
		this.br_endof = br_endof;
	}
	public String getBr_image() {
		return br_image;
	}
	public void setBr_image(String br_image) {
		this.br_image = br_image;
	}
	
	
	
}
