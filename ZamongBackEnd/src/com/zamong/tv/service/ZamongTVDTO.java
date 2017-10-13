package com.zamong.tv.service;

public class ZamongTVDTO {
	private String tv_no;
	private java.sql.Date tv_regidate;
	private String at_no;
	private String tv_title;
	private String tv_contents;
	private String tv_link;
	private String tv_type;
	private String tv_hitcount;
	
	/*TV_NO number NOT NULL,
	TV_REGIDATE date NOT NULL,
	AT_NO number NOT NULL,
	TV_TITLE nvarchar2(100) NOT NULL,
	TV_CONTENT nvarchar2(1000) NOT NULL,
	TV_LINK varchar2(500) NOT NULL,
	TV_TYPE char(1) NOT NULL,
	TV_HITCOUNT number DEFAULT 0 NOT NULL,*/
	//게터 세터
	
	public String getTv_no() {
		return tv_no;
	}
	public void setTv_no(String tv_no) {
		this.tv_no = tv_no;
	}
	public java.sql.Date getTv_regidate() {
		return tv_regidate;
	}
	public void setTv_regidate(java.sql.Date tv_regidate) {
		this.tv_regidate = tv_regidate;
	}
	public String getAt_no() {
		return at_no;
	}
	public void setAt_no(String at_no) {
		this.at_no = at_no;
	}
	public String getTv_title() {
		return tv_title;
	}
	public void setTv_title(String tv_title) {
		this.tv_title = tv_title;
	}
	public String getTv_contents() {
		return tv_contents;
	}
	public void setTv_contents(String tv_contents) {
		this.tv_contents = tv_contents;
	}
	public String getTv_link() {
		return tv_link;
	}
	public void setTv_link(String tv_link) {
		this.tv_link = tv_link;
	}
	public String getTv_type() {
		return tv_type;
	}
	public void setTv_type(String tv_type) {
		this.tv_type = tv_type;
	}
	public String getTv_hitcount() {
		return tv_hitcount;
	}
	public void setTv_hitcount(String tv_hitcount) {
		this.tv_hitcount = tv_hitcount;
	}
	

	
}
