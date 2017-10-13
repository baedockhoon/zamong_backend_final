package com.zamong.mg.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.mg.service.MagazineDTO;
import com.zamong.nt.service.NotiDataDTO;


public class MagazineDAO {
	private Connection conn; 
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public MagazineDAO(ServletContext context){
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
	
	
	public List selectList(int start, int end) {
		List list = new Vector();
		String sql="SELECT * FROM (SELECT T.*,ROWNUM R FROM(SELECT * FROM MG_MAGAZINE ORDER BY MG_NO DESC) T) WHERE R BETWEEN ? AND ?";
		//String sql="SELECT * FROM MG_MAGAZINE ORDER BY MG_NO DESC";
		try{
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			
			rs = psmt.executeQuery();
			while(rs.next()){
				MagazineDTO dto = new MagazineDTO();
				dto.setMg_no(rs.getString(1));
				dto.setMg_regidate(rs.getDate(2));
				dto.setMg_title(rs.getString(3));
				dto.setMg_contents(rs.getString(4));
				dto.setMg_division(rs.getString(5));
				dto.setMg_hitcount(rs.getInt(6));
				list.add(dto);
			}
		}
		catch(SQLException e){e.printStackTrace();}
		return list;
	}/////////////selectList()
	
	public List selectList_today(int start, int end) {
		List list = new Vector();
		String sql="SELECT * FROM (SELECT T.*,ROWNUM R FROM(SELECT * FROM MG_MAGAZINE WHERE TO_CHAR(MG_REGIDATE,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD') ORDER BY MG_NO DESC) T) WHERE R BETWEEN ? AND ?";
		//String sql="SELECT * FROM MG_MAGAZINE ORDER BY MG_NO DESC";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			
			rs = psmt.executeQuery();
			while(rs.next()){
				MagazineDTO dto = new MagazineDTO();
				dto.setMg_no(rs.getString(1));
				dto.setMg_regidate(rs.getDate(2));
				dto.setMg_title(rs.getString(3));
				dto.setMg_contents(rs.getString(4));
				dto.setMg_division(rs.getString(5));
				dto.setMg_hitcount(rs.getInt(6));
				list.add(dto);
			}
		}
		catch(SQLException e){e.printStackTrace();}
		return list;
	}/////////////selectList()
	
	
	//총 레코드 수 얻기용
	public int getTotalRecordCount(){
		int total = 0;
		String sql = "SELECT COUNT(*) FROM MG_MAGAZINE";
		try{
			psmt = conn.prepareStatement(sql);
			 rs = psmt.executeQuery();
			rs.next();
			total = rs.getInt(1);
			
		}//try
		catch(Exception e){ e.printStackTrace(); };//catch
		
		return total;
		
	}//getTotalRecordCount
	
	
	//입력용
		public int insert(MagazineDTO dto) {
			int affected = 0;
			String sql = "INSERT INTO MG_MAGAZINE(MG_NO,MG_REGIDATE,MG_TITLE,MG_CONTENTS,MG_DIVISION) VALUES(MG_MAGAZINE_SEQ.NEXTVAL,SYSDATE,?,?,?)";
			try{
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getMg_title());
				psmt.setString(2, dto.getMg_contents());
				psmt.setString(3, dto.getMg_division());
				
				affected = psmt.executeUpdate();
				
			}//try
			catch(SQLException e){e.printStackTrace();}//catch
			
			return affected;
			
		}//insert
	

		public MagazineDTO selectOne(String no) {
			MagazineDTO dto=null;
			String sql="SELECT * FROM MG_MAGAZINE WHERE MG_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new MagazineDTO();
					dto.setMg_no(rs.getString(1));
					dto.setMg_regidate(rs.getDate(2));
					dto.setMg_title(rs.getString(3));
					dto.setMg_contents(rs.getString(4));
					dto.setMg_division(rs.getString(5));
					dto.setMg_hitcount(rs.getInt(6));
					
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()
		
		
		//삭제용
		public int delete(String no) {
			int affected=0;
			String sql="DELETE MG_MAGAZINE WHERE MG_NO=?";		
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);			
				affected = psmt.executeUpdate();			
			} catch (SQLException e) {e.printStackTrace();}		
			return affected;
		}
		
		
		public int update(MagazineDTO dto) {
			int affected=0;
			String sql="update mg_magazine set mg_title = ?, mg_division = ?, mg_contents = ? where mg_no = ?";
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getMg_title());
				psmt.setString(2, dto.getMg_division());
				psmt.setString(3, dto.getMg_contents());
				psmt.setString(4, dto.getMg_no());
				
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		public void updateVisitCount(String no) {
			String sql = "UPDATE MG_MAGAZINE SET MG_HITCOUNT=MG_HITCOUNT+1 WHERE MG_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//////////////////// updateVisitCount
		
		
		
		
}
