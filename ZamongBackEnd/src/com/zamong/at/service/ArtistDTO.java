package com.zamong.at.service;

import java.util.Date;
//음원 DTO 수정 필요할 수 있음
public class ArtistDTO extends ArtistGropDTO{
	private String at_no; //엘범고유번호
	private java.sql.Date at_regidate; //등록일
	private String at_name; //가수이름
	private String at_belong; //소속사
	private String at_debutdate; //데뷔일
	private String at_debutsong; //데뷔곡
	private String at_birth; //생일
	private String at_contry; //국적
	private String at_gender; //성별
	private String at_artistinfo; //아티스트 정보
	private String at_image; //이미지
	
	
	public ArtistDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtistDTO(String at_no, java.sql.Date at_regidate, String at_name, String at_belong, String at_debutdate,
			String at_debutsong, String at_birth, String at_contry, String at_gender, String at_artistinfo,
			String at_image) {
		super();
		this.at_no = at_no;
		this.at_regidate = at_regidate;
		this.at_name = at_name;
		this.at_belong = at_belong;
		this.at_debutdate = at_debutdate;
		this.at_debutsong = at_debutsong;
		this.at_birth = at_birth;
		this.at_contry = at_contry;
		this.at_gender = at_gender;
		this.at_artistinfo = at_artistinfo;
		this.at_image = at_image;
	}
	
	public String getAt_no() {
		return at_no;
	}
	public void setAt_no(String at_no) {
		this.at_no = at_no;
	}
	public java.sql.Date getAt_regidate() {
		return at_regidate;
	}
	public void setAt_regidate(java.sql.Date at_regidate) {
		this.at_regidate = at_regidate;
	}
	public String getAt_name() {
		return at_name;
	}
	public void setAt_name(String at_name) {
		this.at_name = at_name;
	}
	public String getAt_belong() {
		return at_belong;
	}
	public void setAt_belong(String at_belong) {
		this.at_belong = at_belong;
	}
	public String getAt_debutdate() {
		return at_debutdate;
	}
	public void setAt_debutdate(String at_debutdate) {
		this.at_debutdate = at_debutdate;
	}
	public String getAt_debutsong() {
		return at_debutsong;
	}
	public void setAt_debutsong(String at_debutsong) {
		this.at_debutsong = at_debutsong;
	}
	public String getAt_birth() {
		return at_birth;
	}
	public void setAt_birth(String at_birth) {
		this.at_birth = at_birth;
	}
	public String getAt_contry() {
		return at_contry;
	}
	public void setAt_contry(String at_contry) {
		this.at_contry = at_contry;
	}
	public String getAt_gender() {
		return at_gender;
	}
	public void setAt_gender(String at_gender) {
		this.at_gender = at_gender;
	}
	public String getAt_artistinfo() {
		return at_artistinfo;
	}
	public void setAt_artistinfo(String at_artistinfo) {
		this.at_artistinfo = at_artistinfo;
	}
	public String getAt_image() {
		return at_image;
	}
	public void setAt_image(String at_image) {
		this.at_image = at_image;
	}
	
	
	
	
}