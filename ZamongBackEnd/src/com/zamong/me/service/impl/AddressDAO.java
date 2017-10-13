package com.zamong.me.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.me.service.AddressDTO;

public class AddressDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	//생성자]
	public AddressDAO(ServletContext context){
		try {
			Context ctx = new InitialContext();
			DataSource source=(DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jdbc/zamong");
			conn = source.getConnection();	
			
		} 
		catch (NamingException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		
	}//////////////////DataRoomDAO()
	
	public void close(){
		try{
			if(rs   != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}//try
		catch(Exception e){e.printStackTrace();}//catch
	}//close
	
	public int insert(AddressDTO dto) {
		int affected =0;
		String sql="INSERT INTO ad_address values(AD_SEQUENCE.NEXTVAL,sysdate, ? ,?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMe_no());
			psmt.setString(2, dto.getAd_zip());
			psmt.setString(3, dto.getAd_basic_addr());
			psmt.setString(4, dto.getAd_detail_addr());
			
			affected = psmt.executeUpdate();
		} 
		catch (SQLException e) {			
			e.printStackTrace();
		}		
		return affected;
		
	}
	
	public AddressDTO selectOne(String me_no) {
		AddressDTO ard=null;
		String sql="SELECT * FROM ad_address WHERE me_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, me_no);
			rs = psmt.executeQuery();
			if(rs.next()){
				ard = new AddressDTO();
				ard.setAd_no(rs.getString(1));
				ard.setAd_regidate(rs.getDate(2));
				ard.setMe_no(rs.getString(3));
				ard.setAd_zip(rs.getString(4));
				ard.setAd_basic_addr(rs.getString(5));
				ard.setAd_detail_addr(rs.getString(6));
			}			
		} catch (SQLException e) {e.printStackTrace();}		
		return ard;
	}/////////////////////selectOne()
	
	public void update(AddressDTO ad_dto) {
		
		String sql="UPDATE ad_address "
				+ "SET ad_zip=?,ad_basic_addr=?,ad_detail_addr=? "
				+ "WHERE me_no=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ad_dto.getAd_zip());
			psmt.setString(2, ad_dto.getAd_basic_addr());
			psmt.setString(3, ad_dto.getAd_detail_addr());
			psmt.setString(4, ad_dto.getMe_no());
			
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}////////////////////update
	
}//////////////////////////////AddressDAO
