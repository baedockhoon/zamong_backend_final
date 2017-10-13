package com.zamong.mg.service;

import java.util.Date;

public class MagazineDTO {
	//매거진 MG_MAGAZINE
	private String mg_no;//매거진 고유번호(키값)
	private java.util.Date mg_regidate;//등록날짜
	private String mg_title;//제목
	private String mg_contents;//내용
	private String mg_division;//분류
	private int mg_hitcount;//조회수
	
	public MagazineDTO() {}

	public MagazineDTO(String mg_no, Date mg_regidate, String mg_title, String mg_contents, String mg_division,
			int mg_hitcount) {
		super();
		this.mg_no = mg_no;
		this.mg_regidate = mg_regidate;
		this.mg_title = mg_title;
		this.mg_contents = mg_contents;
		this.mg_division = mg_division;
		this.mg_hitcount = mg_hitcount;
	}

	public String getMg_no() {
		return mg_no;
	}

	public void setMg_no(String mg_no) {
		this.mg_no = mg_no;
	}

	public java.util.Date getMg_regidate() {
		return mg_regidate;
	}

	public void setMg_regidate(java.util.Date mg_regidate) {
		this.mg_regidate = mg_regidate;
	}

	public String getMg_title() {
		return mg_title;
	}

	public void setMg_title(String mg_title) {
		this.mg_title = mg_title;
	}

	public String getMg_contents() {
		return mg_contents;
	}

	public void setMg_contents(String mg_contents) {
		this.mg_contents = mg_contents;
	}

	public String getMg_division() {
		return mg_division;
	}

	public void setMg_division(String mg_division) {
		this.mg_division = mg_division;
	}

	public int getMg_hitcount() {
		return mg_hitcount;
	}

	public void setMg_hitcount(int mg_hitcount) {
		this.mg_hitcount = mg_hitcount;
	}

	
	
	
}
