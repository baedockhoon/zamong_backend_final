package com.zamong.mg.service;

import java.util.Date;

public class CommentDTO {
	//매거진 댓글 MG_COMMENT
	private int mg_cm_no;//매거진댓글 고유번호(키값)
	private java.util.Date mg_cm_regidate;//등록일
	private int mg_no;//매거진 고유번호(키값)
	private int me_no;//회원 고유번호(키값)
	private String mg_cm_contents;//댓글

	public CommentDTO() {}

	public CommentDTO(int mg_cm_no, Date mg_cm_regidate, int mg_no, int me_no, String mg_cm_contents) {
		super();
		this.mg_cm_no = mg_cm_no;
		this.mg_cm_regidate = mg_cm_regidate;
		this.mg_no = mg_no;
		this.me_no = me_no;
		this.mg_cm_contents = mg_cm_contents;
	}

	public int getMg_cm_no() {
		return mg_cm_no;
	}

	public void setMg_cm_no(int mg_cm_no) {
		this.mg_cm_no = mg_cm_no;
	}

	public java.util.Date getMg_cm_regidate() {
		return mg_cm_regidate;
	}

	public void setMg_cm_regidate(java.util.Date mg_cm_regidate) {
		this.mg_cm_regidate = mg_cm_regidate;
	}

	public int getMg_no() {
		return mg_no;
	}

	public void setMg_no(int mg_no) {
		this.mg_no = mg_no;
	}

	public int getMe_no() {
		return me_no;
	}

	public void setMe_no(int me_no) {
		this.me_no = me_no;
	}

	public String getMg_cm_contents() {
		return mg_cm_contents;
	}

	public void setMg_cm_contents(String mg_cm_contents) {
		this.mg_cm_contents = mg_cm_contents;
	}

	
	
	
}
