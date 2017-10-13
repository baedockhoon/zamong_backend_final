package com.zamong.pr.service;

import java.util.Date;

/*
 MQ_NO                                     NOT NULL NUMBER
 MQ_REGIDATE                               NOT NULL DATE
 ME_NO                                     NOT NULL NUMBER
 MQ_USEENV                                 NOT NULL NVARCHAR2(15)
 MQ_OS                                     NOT NULL NVARCHAR2(10)
 MQ_BROWSER                                NOT NULL NVARCHAR2(8)
 MQ_USEMODEL                               NOT NULL NVARCHAR2(20)
 MQ_TEL                                    NOT NULL NVARCHAR2(15)
 MQ_EMAIL                                  NOT NULL VARCHAR2(30)
 MQ_TITLE                                  NOT NULL NVARCHAR2(30)
 MQ_CONTENTS                               NOT NULL NVARCHAR2(2000)
 MQ_UPLOADFILE                             NOT NULL VARCHAR2(100)
 */
public class ProblemDTO {
private String mq_no;
private java.util.Date mq_regidate;
private String me_no;
private String mq_useenv;
private String mq_os;
private String mq_browser;
private String mq_usemodel;
private String mq_tel;
private String mq_email;
private String mq_title;
private String mq_contents;
private String mq_uploadfile;
private String mq_status;
private String mq_largedivide;
private String mq_mediumdivide;




public String getMq_no() {
	return mq_no;
}




public void setMq_no(String mq_no) {
	this.mq_no = mq_no;
}




public java.util.Date getMq_regidate() {
	return mq_regidate;
}




public void setMq_regidate(java.util.Date mq_regidate) {
	this.mq_regidate = mq_regidate;
}




public String getMe_no() {
	return me_no;
}




public void setMe_no(String me_no) {
	this.me_no = me_no;
}




public String getMq_useenv() {
	return mq_useenv;
}




public void setMq_useenv(String mq_useenv) {
	this.mq_useenv = mq_useenv;
}




public String getMq_os() {
	return mq_os;
}




public void setMq_os(String mq_os) {
	this.mq_os = mq_os;
}




public String getMq_browser() {
	return mq_browser;
}




public void setMq_browser(String mq_browser) {
	this.mq_browser = mq_browser;
}




public String getMq_usemodel() {
	return mq_usemodel;
}




public void setMq_usemodel(String mq_usemodel) {
	this.mq_usemodel = mq_usemodel;
}




public String getMq_tel() {
	return mq_tel;
}




public void setMq_tel(String mq_tel) {
	this.mq_tel = mq_tel;
}




public String getMq_email() {
	return mq_email;
}




public void setMq_email(String mq_email) {
	this.mq_email = mq_email;
}




public String getMq_title() {
	return mq_title;
}




public void setMq_title(String mq_title) {
	this.mq_title = mq_title;
}




public String getMq_contents() {
	return mq_contents;
}




public void setMq_contents(String mq_contents) {
	this.mq_contents = mq_contents;
}




public String getMq_uploadfile() {
	return mq_uploadfile;
}




public void setMq_uploadfile(String mq_uploadfile) {
	this.mq_uploadfile = mq_uploadfile;
}




public String getMq_status() {
	return mq_status;
}




public void setMq_status(String mq_status) {
	this.mq_status = mq_status;
}




public String getMq_largedivide() {
	return mq_largedivide;
}




public void setMq_largedivide(String mq_largedivide) {
	this.mq_largedivide = mq_largedivide;
}




public String getMq_mediumdivide() {
	return mq_mediumdivide;
}




public void setMq_mediumdivide(String mq_mediumdivide) {
	this.mq_mediumdivide = mq_mediumdivide;
}




public ProblemDTO(String mq_no, Date mq_regidate, String me_no, String mq_useenv, String mq_os, String mq_browser,
		String mq_usemodel, String mq_tel, String mq_email, String mq_title, String mq_contents, String mq_uploadfile,
		String mq_status, String mq_largedivide, String mq_mediumdivide) {
	this.mq_no = mq_no;
	this.mq_regidate = mq_regidate;
	this.me_no = me_no;
	this.mq_useenv = mq_useenv;
	this.mq_os = mq_os;
	this.mq_browser = mq_browser;
	this.mq_usemodel = mq_usemodel;
	this.mq_tel = mq_tel;
	this.mq_email = mq_email;
	this.mq_title = mq_title;
	this.mq_contents = mq_contents;
	this.mq_uploadfile = mq_uploadfile;
	this.mq_status = mq_status;
	this.mq_largedivide = mq_largedivide;
	this.mq_mediumdivide = mq_mediumdivide;
}




public ProblemDTO() {}

}
