package com.zamong.me.service;

import java.util.Date;

public class AddressDTO {
	String ad_no;
	Date ad_regidate;
	String me_no;
	String ad_zip;
	String ad_basic_addr;
	String ad_detail_addr;
	
	public AddressDTO() {}

	public AddressDTO(String ad_no, Date ad_regidate, String me_no, String ad_zip, String ad_basic_addr,
			String ad_detail_addr) {
		super();
		this.ad_no = ad_no;
		this.ad_regidate = ad_regidate;
		this.me_no = me_no;
		this.ad_zip = ad_zip;
		this.ad_basic_addr = ad_basic_addr;
		this.ad_detail_addr = ad_detail_addr;
	}

	public String getAd_no() {
		return ad_no;
	}

	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}

	public Date getAd_regidate() {
		return ad_regidate;
	}

	public void setAd_regidate(Date ad_regidate) {
		this.ad_regidate = ad_regidate;
	}

	public String getMe_no() {
		return me_no;
	}

	public void setMe_no(String me_no) {
		this.me_no = me_no;
	}

	public String getAd_zip() {
		return ad_zip;
	}

	public void setAd_zip(String ad_zip) {
		this.ad_zip = ad_zip;
	}

	public String getAd_basic_addr() {
		return ad_basic_addr;
	}

	public void setAd_basic_addr(String ad_basic_addr) {
		this.ad_basic_addr = ad_basic_addr;
	}

	public String getAd_detail_addr() {
		return ad_detail_addr;
	}

	public void setAd_detail_addr(String ad_detail_addr) {
		this.ad_detail_addr = ad_detail_addr;
	}
	
	
	
	
}
