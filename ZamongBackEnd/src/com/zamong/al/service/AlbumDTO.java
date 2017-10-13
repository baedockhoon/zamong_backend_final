package com.zamong.al.service;

//엘범 DTO 수정 필요할 수 있음
public class AlbumDTO{
	private String al_no; //엘범고유번호
	private String al_regidate; //등록일
	private String al_albumname; //엘범이름
	private String al_artist; //엘범아티스트
	private String al_releasedate; //발매일
	private String al_publishcorp; //발매사
	private String al_entertaincorp; //기획사
	private String al_albumimage; //엘범사진
	private String al_albuminfo; //엘범정보
	private String al_divide; //아티스트 구별
	private String al_artistno; //가수고유번호
	private String al_soundcount; //앨범 보유 음원수
	private String al_likecount;//좋아요
	
	public AlbumDTO() {}
	
	public AlbumDTO(String al_no, String al_regidate, String al_albumname, String al_artist, String al_releasedate,
			String al_publishcorp, String al_entertaincorp, String al_albumimage, String al_albuminfo, String al_divide,
			String al_artistno) {
		super();
		this.al_no = al_no;
		this.al_regidate = al_regidate;
		this.al_albumname = al_albumname;
		this.al_artist = al_artist;
		this.al_releasedate = al_releasedate;
		this.al_publishcorp = al_publishcorp;
		this.al_entertaincorp = al_entertaincorp;
		this.al_albumimage = al_albumimage;
		this.al_albuminfo = al_albuminfo;
		this.al_divide = al_divide;
		this.al_artistno = al_artistno;
	}
	public String getAl_no() {
		return al_no;
	}
	public void setAl_no(String al_no) {
		this.al_no = al_no;
	}
	public String getAl_regidate() {
		return al_regidate;
	}
	public void setAl_regidate(String al_regidate) {
		this.al_regidate = al_regidate;
	}
	public String getAl_albumname() {
		return al_albumname;
	}
	public void setAl_albumname(String al_albumname) {
		this.al_albumname = al_albumname;
	}
	public String getAl_artist() {
		return al_artist;
	}
	public void setAl_artist(String al_artist) {
		this.al_artist = al_artist;
	}
	public String getAl_releasedate() {
		return al_releasedate;
	}
	public void setAl_releasedate(String al_releasedate) {
		this.al_releasedate = al_releasedate;
	}
	public String getAl_publishcorp() {
		return al_publishcorp;
	}
	public void setAl_publishcorp(String al_publishcorp) {
		this.al_publishcorp = al_publishcorp;
	}
	public String getAl_entertaincorp() {
		return al_entertaincorp;
	}

	public void setAl_entertaincorp(String al_entertaincorp) {
		this.al_entertaincorp = al_entertaincorp;
	}

	public String getAl_albumimage() {
		return al_albumimage;
	}
	public void setAl_albumimage(String al_albumimage) {
		this.al_albumimage = al_albumimage;
	}
	public String getAl_albuminfo() {
		return al_albuminfo;
	}
	public void setAl_albuminfo(String al_albuminfo) {
		this.al_albuminfo = al_albuminfo;
	}
	public String getAl_divide() {
		return al_divide;
	}
	public void setAl_divide(String al_divide) {
		this.al_divide = al_divide;
	}
	public String getAl_artistno() {
		return al_artistno;
	}
	public void setAl_artistno(String al_artistno) {
		this.al_artistno = al_artistno;
	}

	public String getAl_soundcount() {
		return al_soundcount;
	}

	public void setAl_soundcount(String al_soundcount) {
		this.al_soundcount = al_soundcount;
	}

	public String getAl_likecount() {
		return al_likecount;
	}

	public void setAl_likecount(String al_likecount) {
		this.al_likecount = al_likecount;
	}
	

}