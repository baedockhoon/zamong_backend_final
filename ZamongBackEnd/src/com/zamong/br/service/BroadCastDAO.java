package com.zamong.br.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.br.service.impl.BroadCastDTO;

public class BroadCastDAO {

	//멤버변수]
		private Connection conn;
		private PreparedStatement psmt;
		private ResultSet rs;
		//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
		public BroadCastDAO(ServletContext context){
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
		public List listAll() {
			List list = new Vector();
			String sql="SELECT * FROM BR_BROADCAST ORDER BY BR_NO DESC";
			try{
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while(rs.next()){
					BroadCastDTO dto = new BroadCastDTO();
					dto.setBr_no(rs.getString(1));
					dto.setBr_regidate(rs.getDate(2));
					dto.setAt_no(rs.getString(3));
					dto.setBr_title(rs.getString(4));
					dto.setBr_contents(rs.getString(5));
					dto.setBr_link(rs.getString(6));
					dto.setBr_hitcount(rs.getString(7));
					dto.setBr_programname(rs.getString(8));
					dto.setBr_endof(rs.getString(9));
					dto.setBr_image(rs.getString(10));
					list.add(dto);
				}
			}
			catch(SQLException e){e.printStackTrace();}
			return list;
		}/////////////selectList()

		
		//입력용]
		public int insert(BroadCastDTO dto) {
			int affected=0;
			String sql="INSERT INTO BR_BROADCAST VALUES(BR_BROADCAST_SEQ.NEXTVAL,SYSDATE,?,?,?,?,0,?,?,?)";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getAt_no());
				psmt.setString(2, dto.getBr_title());
				psmt.setString(3, dto.getBr_contents());
				psmt.setString(4, dto.getBr_link());
				psmt.setString(5, dto.getBr_programname());
				psmt.setString(6, dto.getBr_endof());
				psmt.setString(7, dto.getBr_image());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////insert
		
		
		//삭제용
		public int delete(String no) {
			int affected =0;
			String sql="DELETE BR_BROADCAST WHERE BR_NO=?";
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
		
		
		public BroadCastDTO selectOne(String no) {
			BroadCastDTO dto=null;
			
			String sql="UPDATE BR_BROADCAST SET BR_HITCOUNT=BR_HITCOUNT+1 WHERE BR_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				psmt.executeUpdate();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			
			sql="SELECT * FROM BR_BROADCAST WHERE BR_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new BroadCastDTO();
					dto.setBr_no(rs.getString(1));
					dto.setBr_regidate(rs.getDate(2));
					dto.setAt_no(rs.getString(3));
					dto.setBr_title(rs.getString(4));
					dto.setBr_contents(rs.getString(5));
					dto.setBr_link(rs.getString(6));
					dto.setBr_hitcount(rs.getString(7));
					dto.setBr_programname(rs.getString(8));
					dto.setBr_endof(rs.getString(9));
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()

		//수정용]
		public int update(BroadCastDTO dto) {
			int affected=0;
			String sql="UPDATE BR_BROADCAST SET AT_NO=?,BR_TITLE=?,BR_CONTENTS=?,BR_LINK=?,BR_PROGRAMNAME=?,BR_ENDOF=? WHERE BR_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getAt_no());
				psmt.setString(2, dto.getBr_title());
				psmt.setString(3, dto.getBr_contents());
				psmt.setString(4, dto.getBr_link());
				psmt.setString(5, dto.getBr_programname());
				psmt.setString(6, dto.getBr_endof());
				psmt.setString(7, dto.getBr_no());

				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		
		public List<BroadCastDTO> selectList(Map<String, Object> map) {

			List<BroadCastDTO> records = new Vector<BroadCastDTO>();

			//페이징 미 적용
			//String sql="SELECT B.*,M.NAME FROM BBS B JOIN MEMBER M ON B.ID=M.ID ORDER BY NO DESC";
			//페이징 적용-구간쿼리로 변경
			String sql="SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM BR_BROADCAST ";
			//검색용 쿼리 추가
			if(map.get("searchWord") !=null){
				sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
			}		
			sql+=" ORDER BY BR_NO DESC) T) WHERE R BETWEEN ? AND ?";
			
			try {
				psmt = conn.prepareStatement(sql);
				
				//페이징을 위한 시작 및 종료 rownum설정]
				psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
				psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
				
				rs = psmt.executeQuery();
				while(rs.next()){
					BroadCastDTO dto = new BroadCastDTO(
							rs.getString(1),
							rs.getDate(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getString(9),
							rs.getString(10));
					//dto.setName(rs.getString(7));
					records.add(dto);
				}
			} catch (SQLException e) {			
				e.printStackTrace();
			}		
			return records;
		}/////////////////selectList
		

		
		public int getTotalCount(Map<String, Object> map) {
			int totalCount = 0;
			String sql = "SELECT COUNT(*) FROM BR_BROADCAST ";
			try {
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				rs.next();
				totalCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return totalCount;
		}/////////////////////
		
		
		
		public Map<String, BroadCastDTO> prevNnext(String no) {
			Map<String, BroadCastDTO> map = new HashMap<String, BroadCastDTO>();
			String sql = "SELECT BR_NO, BR_TITLE FROM BR_BROADCAST WHERE BR_NO=(SELECT MIN(BR_NO) FROM BR_BROADCAST WHERE BR_NO > ?)";
			try {
				// 이전글 구하기]
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 이전글 존재
					map.put("PREV", new BroadCastDTO(rs.getString(1),null,null,rs.getString(2),null,null,null,null,null,null));//BR_NO 및 MV_TITLE만 가져오기 

				}
				// 다음글 구하기
				sql = "SELECT BR_NO, BR_TITLE FROM BR_BROADCAST WHERE BR_NO=(SELECT MAX(BR_NO) FROM BR_BROADCAST WHERE BR_NO < ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 다음글 존재
					map.put("NEXT", new BroadCastDTO(rs.getString(1),null,null,rs.getString(2),null,null,null,null,null,null));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return map;
		}//////////////// prevNnext
		
		
		
		
}

