package com.zamong.me.service;

import java.util.Date;

public class LikeDTO {
	String lk_no;
	Date lk_regidate;
	String me_no;
	String lk_flag;
	String lk_targetno;
	
	public LikeDTO() {}

	public LikeDTO(String lk_no, Date lk_regidate, String me_no, String lk_flag, String lk_targetno) {
		super();
		this.lk_no = lk_no;
		this.lk_regidate = lk_regidate;
		this.me_no = me_no;
		this.lk_flag = lk_flag;
		this.lk_targetno = lk_targetno;
	}

	public String getLk_no() {
		return lk_no;
	}

	public void setLk_no(String lk_no) {
		this.lk_no = lk_no;
	}

	public Date getLk_regidate() {
		return lk_regidate;
	}

	public void setLk_regidate(Date lk_regidate) {
		this.lk_regidate = lk_regidate;
	}

	public String getMe_no() {
		return me_no;
	}

	public void setMe_no(String me_no) {
		this.me_no = me_no;
	}

	public String getLk_flag() {
		return lk_flag;
	}

	public void setLk_flag(String lk_flag) {
		this.lk_flag = lk_flag;
	}

	public String getLk_targetno() {
		return lk_targetno;
	}

	public void setLk_targetno(String lk_targetno) {
		this.lk_targetno = lk_targetno;
	}
	
	
	
}
