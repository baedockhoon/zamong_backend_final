package com.zamong.as.service;

public class AssignDTO {
	private int as_no;
	private String as_regidate;
	private int me_no;
	private int al_no;
	private String as_getpoint;
	private String me_id;
	
	public AssignDTO() {}

	public AssignDTO(int as_no, String as_regidate, int me_no, int al_no, String as_getpoint, String me_id) {
		super();
		this.as_no = as_no;
		this.as_regidate = as_regidate;
		this.me_no = me_no;
		this.al_no = al_no;
		this.as_getpoint = as_getpoint;
		this.me_id = me_id;
	}

	public int getAs_no() {
		return as_no;
	}

	public void setAs_no(int as_no) {
		this.as_no = as_no;
	}

	public String getAs_regidate() {
		return as_regidate;
	}

	public void setAs_regidate(String as_regidate) {
		this.as_regidate = as_regidate;
	}

	public int getMe_no() {
		return me_no;
	}

	public void setMe_no(int me_no) {
		this.me_no = me_no;
	}

	public int getAl_no() {
		return al_no;
	}

	public void setAl_no(int al_no) {
		this.al_no = al_no;
	}

	public String getAs_getpoint() {
		return as_getpoint;
	}

	public void setAs_getpoint(String as_getpoint) {
		this.as_getpoint = as_getpoint;
	}

	public String getMe_id() {
		return me_id;
	}

	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}

	
}
