package com.zamong.qu.service;

import java.util.Date;

/*
 *  QU_NO                                     NOT NULL NUMBER
 QU_REGIDATE                               NOT NULL DATE
 AD_NO                                     NOT NULL NUMBER
 QU_LARGEDIVIDE                            NOT NULL NVARCHAR2(20)
 QU_MEDIUMDIVIDE                           NOT NULL NVARCHAR2(20)
 QU_TITLE                                  NOT NULL NVARCHAR2(30)
 QU_CONTENTS                               NOT NULL NVARCHAR2(2000)
 QU_HITCOUNT                               NOT NULL NUMBER
 QU_LASTDATE                               NOT NULL DATE
 */
public class UserQuestionDTO {
private String qu_no;
private java.util.Date qu_regidate;
private String ad_no;
private String qu_largedivide;
private String qu_mediumdivide;
private String qu_title;
private String qu_contents;
private String qu_hitcount;
private java.util.Date qu_lastdate;
public String getQu_no() {
	return qu_no;
}
public void setQu_no(String qu_no) {
	this.qu_no = qu_no;
}
public java.util.Date getQu_regidate() {
	return qu_regidate;
}
public void setQu_regidate(java.util.Date qu_regidate) {
	this.qu_regidate = qu_regidate;
}
public String getAd_no() {
	return ad_no;
}
public void setAd_no(String ad_no) {
	this.ad_no = ad_no;
}
public String getQu_largedivide() {
	return qu_largedivide;
}
public void setQu_largedivide(String qu_largedivide) {
	this.qu_largedivide = qu_largedivide;
}
public String getQu_mediumdivide() {
	return qu_mediumdivide;
}
public void setQu_mediumdivide(String qu_mediumdivide) {
	this.qu_mediumdivide = qu_mediumdivide;
}
public String getQu_title() {
	return qu_title;
}
public void setQu_title(String qu_title) {
	this.qu_title = qu_title;
}
public String getQu_contents() {
	return qu_contents;
}
public void setQu_contents(String qu_contents) {
	this.qu_contents = qu_contents;
}
public String getQu_hitcount() {
	return qu_hitcount;
}
public void setQu_hitcount(String qu_hitcount) {
	this.qu_hitcount = qu_hitcount;
}
public java.util.Date getQu_lastdate() {
	return qu_lastdate;
}
public void setQu_lastdate(java.util.Date qu_lastdate) {
	this.qu_lastdate = qu_lastdate;
}
public UserQuestionDTO(String qu_no, Date qu_regidate, String ad_no, String qu_largedivide, String qu_mediumdivide,
		String qu_title, String qu_contents, String qu_hitcount, Date qu_lastdate) {
	this.qu_no = qu_no;
	this.qu_regidate = qu_regidate;
	this.ad_no = ad_no;
	this.qu_largedivide = qu_largedivide;
	this.qu_mediumdivide = qu_mediumdivide;
	this.qu_title = qu_title;
	this.qu_contents = qu_contents;
	this.qu_hitcount = qu_hitcount;
	this.qu_lastdate = qu_lastdate;
}
public UserQuestionDTO() {
}







}
