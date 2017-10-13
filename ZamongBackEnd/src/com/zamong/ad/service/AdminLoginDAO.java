package com.zamong.ad.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class AdminLoginDAO {
		//멤버변수]
		private Connection conn;
		private PreparedStatement psmt;
		private ResultSet rs;
		//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
		public AdminLoginDAO(ServletContext context){
			try {
				Context ctx = new InitialContext();
				DataSource source=(DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jdbc/zamong");
				conn = source.getConnection();	
			} 
			catch (NamingException e) {e.printStackTrace();}
			catch (SQLException e) {e.printStackTrace();}
		}
		
		 
		//자원반납용]-사용한 커넥션 객체를 다시 풀에 반납
		public void close(){
			try {
				//메모리 해제]
				if(rs !=null) rs.close();
				if(psmt !=null) psmt.close();
				// 커넥션 풀에 커넥션 객체 반납-메모리 해제 아님]
				if(conn !=null) conn.close();
			} catch (Exception e) {e.printStackTrace();}
		}/////////////////close()
		
		
		//회원여부 판단용]
		public boolean isMember(String id ,String pwd){
			String sql="SELECT COUNT(*) FROM AD_ADMIN WHERE AD_ID=? AND AD_PASSWORD=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, pwd);
				rs = psmt.executeQuery();
				rs.next();
				if(rs.getInt(1) != 1) return false;
			} catch (SQLException e) {			
				e.printStackTrace();
				return false;
			}
			return true;
		}///////////////////isMember
		
		
}
