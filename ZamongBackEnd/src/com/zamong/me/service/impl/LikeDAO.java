package com.zamong.me.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.me.service.LikeDTO;
import com.zamong.me.service.MemberDTO;

public class LikeDAO {
					
		//멤버변수	
			private Connection conn;
			private PreparedStatement psmt;
			private ResultSet rs;
					
			//생성자]
			public LikeDAO(ServletContext context){
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
		
			public int insert(LikeDTO dto) {
				int affected =0;
				String sql="INSERT INTO lk_like values(LK_SEQUENCE.NEXTVAL,SYSDATE, ?, ?, ?)";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, dto.getMe_no());
					psmt.setString(2, dto.getLk_flag());
					psmt.setString(3, dto.getLk_targetno());
					
					affected = psmt.executeUpdate();
				} 
				catch (SQLException e) {			
					e.printStackTrace();
				}		
				return affected;
			}
			
			public int getLikeCount(String me_no,String lk_targetno){
				int total = 0;
				String sql = "SELECT COUNT(*) FROM lk_like where me_no=? and lk_targetno=?";
				try{
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, me_no);
					psmt.setString(2, lk_targetno);
					rs = psmt.executeQuery();
					rs.next();
					total = rs.getInt(1);
					
				}//try
				catch(Exception e){ e.printStackTrace(); };//catch
				
				return total;
				
			}//getTotalLikeCount
			
			public int getTotalLikeCount(String lk_targetno){
				int total = 0;
				String sql = "SELECT COUNT(*) FROM lk_like where lk_targetno=?";
				try{
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, lk_targetno);
					rs = psmt.executeQuery();
					rs.next();
					total = rs.getInt(1);
					
				}//try
				catch(Exception e){ e.printStackTrace(); };//catch
				
				return total;
				
			}//getTotalLikeCount
			
			public int delete(String me_no,String lk_flag,String lk_targetno) {
				int affected=0;
				String sql="DELETE lk_like WHERE me_no=? and lk_flag=? and lk_targetno=?";		
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, me_no);
					psmt.setString(2, lk_flag);
					psmt.setString(3, lk_targetno);
					affected = psmt.executeUpdate();			
				} catch (SQLException e) {e.printStackTrace();}		
				return affected;
			}
			
			public LikeDTO selectOne(String me_no,String lk_flag,String lk_targetno) {
				LikeDTO dto=null;
				String sql="SELECT * FROM lk_like WHERE me_no=? and lk_flag=? and lk_targetno=?";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, me_no);
					psmt.setString(2, lk_flag);
					psmt.setString(3, lk_targetno);
					rs = psmt.executeQuery();
					if(rs.next()){
						dto = new LikeDTO();
						dto.setLk_no(rs.getString(1));
						dto.setLk_regidate(rs.getDate(2));
						dto.setMe_no(rs.getString(3));
						dto.setLk_flag(rs.getString(4));
						dto.setLk_targetno(rs.getString(5));
					}			
				} catch (SQLException e) {e.printStackTrace();}		
				return dto;
			}/////////////////////selectOne()

}
