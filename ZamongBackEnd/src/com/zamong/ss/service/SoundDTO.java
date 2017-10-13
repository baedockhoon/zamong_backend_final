package com.zamong.ss.service;

import com.zamong.al.service.AlbumDTO;

//음원 DTO 수정 필요할 수 있음
public class SoundDTO extends AlbumDTO{
	private String ss_no; //음원 고유번호
	private String ss_regidate; //등록일
	private String al_no; //엘범 고유번호(FK)
	private String ss_title; //음원 이름
	private String ss_genre; //장르
	private String ss_duration; //재생시간
	private String ss_path; //노래파일경로
	private String ss_albumtitle; //대표여부
	private String ly_contents;//가사
	private String ss_likecount;//좋아요
	
	
	
	public SoundDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public SoundDTO(String ss_no, String ss_regidate, String al_no, String ss_title, String ss_genre,
			String ss_duration, String ss_path, String ss_albumtitle, String ly_contents, String al_albumimage) {
		super();
		this.ss_no = ss_no;
		this.ss_regidate = ss_regidate;
		this.al_no = al_no;
		this.ss_title = ss_title;
		this.ss_genre = ss_genre;
		this.ss_duration = ss_duration;
		this.ss_path = ss_path;
		this.ss_albumtitle = ss_albumtitle;
		this.ly_contents = ly_contents;
	}


	public String getSs_no() {
		return ss_no;
	}
	public void setSs_no(String ss_no) {
		this.ss_no = ss_no;
	}
	public String getSs_regidate() {
		return ss_regidate;
	}
	public void setSs_regidate(String ss_regidate) {
		this.ss_regidate = ss_regidate;
	}
	public String getAl_no() {
		return al_no;
	}
	public void setAl_no(String al_no) {
		this.al_no = al_no;
	}
	public String getSs_title() {
		return ss_title;
	}
	public void setSs_title(String ss_title) {
		this.ss_title = ss_title;
	}
	public String getSs_genre() {
		return ss_genre;
	}
	public void setSs_genre(String ss_genre) {
		this.ss_genre = ss_genre;
	}
	public String getSs_duration() {
		return ss_duration;
	}
	public void setSs_duration(String ss_duration) {
		this.ss_duration = ss_duration;
	}
	public String getSs_path() {
		return ss_path;
	}
	public void setSs_path(String ss_path) {
		this.ss_path = ss_path;
	}
	public String getSs_albumtitle() {
		return ss_albumtitle;
	}
	public void setSs_albumtitle(String ss_albumtitle) {
		this.ss_albumtitle = ss_albumtitle;
	}

	public String getLy_contents() {
		return ly_contents;
	}

	public void setLy_contents(String ly_contents) {
		this.ly_contents = ly_contents;
	}


	public String getSs_likecount() {
		return ss_likecount;
	}


	public void setSs_likecount(String ss_likecount) {
		this.ss_likecount = ss_likecount;
	}
	

}