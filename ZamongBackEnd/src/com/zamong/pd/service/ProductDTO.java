package com.zamong.pd.service;

import java.util.Date;

public class ProductDTO {

	private String pd_no;
	private String pd_name;
	private String pd_price;
	private java.util.Date pd_regidate;
	public String getPd_no() {
		return pd_no;
	}
	public void setPd_no(String pd_no) {
		this.pd_no = pd_no;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public String getPd_price() {
		return pd_price;
	}
	public void setPd_price(String pd_price) {
		this.pd_price = pd_price;
	}
	
	public java.util.Date getPd_regidate() {
		return pd_regidate;
	}
	public void setPd_regidate(java.util.Date pd_regidate) {
		this.pd_regidate = pd_regidate;
	}
	public ProductDTO() {}
	public ProductDTO(String pd_no, String pd_name, String pd_price, Date pd_regidate) {
		this.pd_no = pd_no;
		this.pd_name = pd_name;
		this.pd_price = pd_price;
		this.pd_regidate = pd_regidate;
	}
	
	
	


}
