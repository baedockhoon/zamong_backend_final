package com.zamong.se.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.nt.service.NotiDataDTO;
import com.zamong.se.service.SearchDTO;

public class SearchDAO {
	private Connection conn; 
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public SearchDAO(ServletContext context){
		try {
			Context ctx = new InitialContext();
			DataSource source=(DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jdbc/zamong");
			conn = source.getConnection();	
			
		} 
		catch (NamingException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		
	}//////////////////DataRoomDAO()
	
	public void close(){
		try {
			
			if(rs !=null) rs.close();
			if(psmt !=null) psmt.close();
			if(conn !=null) conn.close();
			
		} catch (Exception e) {e.printStackTrace();}
	}/////////////////close()
	
	public int insert(SearchDTO dto) {
		int affected=0;
		String sql="INSERT INTO SE_SEARCH VALUES(se_seq.nextval,SYSDATE,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getSearch());
			affected = psmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return affected;
	}/////////////////insert
}
