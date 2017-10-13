package com.zamong.mg.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.mg.service.CommentDTO;
import com.zamong.mg.service.MagazineDTO;

public class CommentDAO {
	private Connection conn; 
	private PreparedStatement psmt;
	private ResultSet rs;
	
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public CommentDAO(ServletContext context){
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
	
	
	public List selectList_CM() {
		List list = new Vector();
		String sql="SELECT MG_CM_NO,MG_CM_REGIDATE,MG_CM_CONTENTS FROM MG_COMMENT ORDER BY MG_NO DESC";
		try{
			
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			while(rs.next()){
				CommentDTO dto = new CommentDTO();
				dto.setMg_cm_no(rs.getInt(1));
				dto.setMg_cm_regidate(rs.getDate(2));
				dto.setMg_cm_contents(rs.getString(3));
				
				list.add(dto);
			}
		}
		catch(SQLException e){e.printStackTrace();}
		return list;
	}/////////////selectList()
	
	public CommentDTO selectOne(String no) {
		CommentDTO dto=null;
		String sql="SELECT MG_CM_NO,MG_CM_REGIDATE,MG_CM_CONTENTS FROM MG_COMMENT WHERE MG_CM_NO = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if(rs.next()){
				dto = new CommentDTO();
				dto.setMg_cm_no(rs.getInt(1));
				dto.setMg_cm_regidate(rs.getDate(2));
				dto.setMg_cm_contents(rs.getString(3));
				
			}			
		} catch (SQLException e) {e.printStackTrace();}		
		return dto;
	}/////////////////////selectOne()
	
	
	
		//입력용
		public int insert(CommentDTO dto) {
			int affected = 0;
			String sql = "INSERT INTO MG_COMMENT VALUES(MG_COMMENT_SEQ,SYSDATE,?,?,?)";
			try{
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, dto.getMg_no());
				psmt.setInt(2, dto.getMe_no());
				psmt.setString(3, dto.getMg_cm_contents());
				
				affected = psmt.executeUpdate();
				
			}//try
			catch(SQLException e){e.printStackTrace();}//catch
			
			return affected;
			
		}//insert
	
	
	
	
}
