package com.zamong.as.service.impl;

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

import com.zamong.as.service.AssignDTO;

public class AssignDAO {
	
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public AssignDAO(ServletContext context) {
		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT") + "/jdbc/zamong");
			conn = source.getConnection();

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}////////////////// DataRoomDAO()

	public void close() {
		try {

			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}///////////////// close()
	
	public List selectList(int start, int end) {
		List list = new Vector();
		String sql="SELECT * FROM (SELECT T.*,ROWNUM R FROM(SELECT A.*,M.ME_ID FROM AS_ASSIGN A JOIN ME_MEMBER M ON A.ME_NO = M.ME_NO ORDER BY AS_NO DESC) T) WHERE R BETWEEN ? AND ?";
		//String sql="SELECT * FROM MG_MAGAZINE ORDER BY MG_NO DESC";
		try{
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			
			rs = psmt.executeQuery();
			while(rs.next()){
				AssignDTO dto = new AssignDTO();
				dto.setAs_no(rs.getInt(1));
				dto.setAs_regidate(rs.getString(2));
				dto.setMe_no(rs.getInt(3));
				dto.setAl_no(rs.getInt(4));
				dto.setAs_getpoint(rs.getString(5));
				dto.setMe_id(rs.getString(6));
				list.add(dto);
			}
		}
		catch(SQLException e){e.printStackTrace();}
		return list;
	}/////////////selectList()
	
	//총 레코드 수 얻기용
		public int getTotalRecordCount(){
			int total = 0;
			String sql = "SELECT COUNT(*) FROM AS_ASSIGN";
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
	public int insert(AssignDTO dto) {
		int affected = 0;
		String sql = "INSERT INTO AS_ASSIGN VALUES(AS_SEQ.NEXTVAL,SYSDATE,2,2,?)";
		try{
			psmt = conn.prepareStatement(sql);
			//psmt.setInt(1, dto.getMe_no());
			//psmt.setInt(2, dto.getAl_no());
			psmt.setString(1, dto.getAs_getpoint());
			
			affected = psmt.executeUpdate();
			
		}//try
		catch(SQLException e){e.printStackTrace();}//catch
		
		return affected;
		
	}//insert
	
	
	
}
