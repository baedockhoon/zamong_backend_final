package com.zamong.tv.service.impl;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.tv.service.ZamongTVDTO;



public class ZamongTVDAO {

	//멤버변수]
		private Connection conn;
		private PreparedStatement psmt;
		private ResultSet rs;
		//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
		public ZamongTVDAO(ServletContext context){
			try {
				Context ctx = new InitialContext();
				DataSource source=(DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jdbc/zamong");
				conn = source.getConnection();	
				
			} 
			catch (NamingException e) {e.printStackTrace();}
			catch (SQLException e) {e.printStackTrace();}
			
		}//////////////////zamongtvDAO()
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
		
		//전체 목록용]
		public List selectList() {
			List list = new Vector();
			String sql="SELECT * FROM TV_ZAMONGTV ORDER BY TV_NO DESC";
			try{
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while(rs.next()){
					ZamongTVDTO dto = new ZamongTVDTO();
					dto.setTv_no(rs.getString(1));
					dto.setTv_regidate(rs.getDate(2));
					dto.setAt_no(rs.getString(3));
					dto.setTv_title(rs.getString(4));
					dto.setTv_contents(rs.getString(5));
					dto.setTv_link(rs.getString(6));
					dto.setTv_type(rs.getString(7));
					dto.setTv_hitcount(rs.getString(8));
					list.add(dto);
					
					/*TV_NO number NOT NULL,
						TV_REGIDATE date NOT NULL,
						AT_NO number NOT NULL,
						TV_TITLE nvarchar2(100) NOT NULL,
						TV_CONTENTS nvarchar2(1000) NOT NULL,
						TV_LINK varchar2(500) NOT NULL,
						TV_TYPE char(1) NOT NULL,
						TV_HITCOUNT number DEFAULT 0 NOT NULL,*/
				}
			}
			catch(SQLException e){e.printStackTrace();}
			return list;
		}/////////////selectList()

		//입력용]
		public int insert(ZamongTVDTO dto) {
			int affected=0;
			String sql="INSERT INTO TV_ZAMONGTV VALUES(TV_ZAMONGTV_seq.NEXTVAL,SYSDATE,?,?,?,?,?,566)";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getAt_no());
				psmt.setString(2, dto.getTv_title());
				psmt.setString(3, dto.getTv_contents());
				psmt.setString(4, dto.getTv_link());
				psmt.setString(5, dto.getTv_type());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////insert
		
		
		//삭제용
		public int delete(String no) {
			int affected =0;
			String sql="DELETE TV_ZAMONGTV WHERE TV_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,no);			
				affected = psmt.executeUpdate();
			} 
			catch (SQLException e) {			
				e.printStackTrace();
			}		
			return affected;
		}
		
		
		public ZamongTVDTO selectOne(String no) {
			ZamongTVDTO dto=null;
			String sql="SELECT * FROM TV_ZAMONGTV WHERE TV_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new ZamongTVDTO();
					dto.setTv_no(rs.getString(1));
					dto.setTv_regidate(rs.getDate(2));
					dto.setAt_no(rs.getString(3));
					dto.setTv_title(rs.getString(4));
					dto.setTv_contents(rs.getString(5));
					dto.setTv_link(rs.getString(6));
					dto.setTv_hitcount(rs.getString(7));
					dto.setTv_type(rs.getString(8));
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()

		//수정용]
		public int update(ZamongTVDTO dto) {
			int affected=0;
			String sql="UPDATE TV_ZAMONGTV SET AT_NO=?,TV_TITLE=?,TV_CONTENT=?,TV_LINK=?,TV_TYPE=? WHERE TV_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getAt_no());
				psmt.setString(2, dto.getTv_title());
				psmt.setString(3, dto.getTv_contents());
				psmt.setString(4, dto.getTv_link());
				psmt.setString(5, dto.getTv_no());
				psmt.setString(6, dto.getTv_type());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		
}
