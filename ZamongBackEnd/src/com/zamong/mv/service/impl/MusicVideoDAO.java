package com.zamong.mv.service.impl;

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

import com.zamong.mv.service.MusicVideoDTO;
import com.zamong.nt.service.NotiDataDTO;

public class MusicVideoDAO {

	//멤버변수]
		private Connection conn;
		private PreparedStatement psmt;
		private ResultSet rs;
		//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
		public MusicVideoDAO(ServletContext context){
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
			String sql="SELECT * FROM MV_MUSICVIDEO ORDER BY MV_NO DESC";
			try{
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while(rs.next()){
					MusicVideoDTO dto = new MusicVideoDTO();
					dto.setMv_no(rs.getString(1));
					dto.setMv_regidate(rs.getDate(2));
					dto.setAt_no(rs.getString(3));
					dto.setSs_no(rs.getString(4));
					dto.setMv_title(rs.getString(5));
					dto.setMv_contents(rs.getString(6));
					dto.setMv_link(rs.getString(7));
					dto.setMv_hitcount(rs.getString(8));
					dto.setMv_image(rs.getString(9));
					list.add(dto);
					
					/*	MV_NO
					MV_REGIDATE
					AT_NO
					SS_NO
					MV_TITLE
					MV_CONTENTS
					MV_LINK
					MV_HITCOUNT*/
				}
			}
			catch(SQLException e){e.printStackTrace();}
			return list;
		}/////////////selectList()

		//입력용]
		public int insert(MusicVideoDTO dto) {
			int affected=0;
			String sql="INSERT INTO MV_MUSICVIDEO VALUES(MV_MUSICVIDEO_SEQ.NEXTVAL,SYSDATE,?,?,?,?,?,0,?)";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getAt_no());
				psmt.setString(2, dto.getSs_no());
				psmt.setString(3, dto.getMv_title());
				psmt.setString(4, dto.getMv_contents());
				psmt.setString(5, dto.getMv_link());
				psmt.setString(6, dto.getMv_image());
				affected = psmt.executeUpdate();
				
				/*MV_NO      -nextval
				MV_REGIDATE  -sysdate
				AT_NO   ?
				SS_NO   ?
				MV_TITLE ?
				MV_CONTENTS ?
				MV_LINK ?
				MV_HITCOUNT
				MV_IMAGE ?  
				*/
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////insert
		
		
		//삭제용
		public int delete(String no) {
			int affected =0;
			String sql="DELETE MV_MUSICVIDEO WHERE MV_NO=?";
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
		
		
		public MusicVideoDTO selectOne(String no) {
			MusicVideoDTO dto=null;
			
			String sql="UPDATE MV_MUSICVIDEO SET MV_HITCOUNT=MV_HITCOUNT+1 WHERE MV_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				psmt.executeUpdate();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			
			sql="select m.mv_no,m.mv_regidate,a.at_name,s.ss_title,m.mv_title,m.mv_contents,m.mv_link,m.mv_hitcount,m.mv_image "
					+ "from mv_musicvideo m join at_artist a on m.at_no=a.at_no join ss_soundsource s on m.ss_no=s.ss_no where m.mv_no=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new MusicVideoDTO();
					dto.setMv_no(rs.getString(1));
					dto.setMv_regidate(rs.getDate(2));
					dto.setAt_no(rs.getString(3));
					dto.setSs_no(rs.getString(4));
					dto.setMv_title(rs.getString(5));
					dto.setMv_contents(rs.getString(6));
					dto.setMv_link(rs.getString(7));
					dto.setMv_hitcount(rs.getString(8));
					dto.setMv_image(rs.getString(9));
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()

		//수정용]
		public int update(MusicVideoDTO dto) {
			int affected=0;
			String sql="UPDATE MV_MUSICVIDEO SET AT_NO=?,SS_NO=?,MV_TITLE=?,MV_CONTENTS=?,MV_LINK=?,MV_IMAGE=? WHERE MV_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getAt_no());
				psmt.setString(2, dto.getSs_no());
				psmt.setString(3, dto.getMv_title());
				psmt.setString(4, dto.getMv_contents());
				psmt.setString(5, dto.getMv_link());
				psmt.setString(6, dto.getMv_image());
				psmt.setString(7, dto.getMv_no());
				affected = psmt.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		
		public List<MusicVideoDTO> selectList(Map<String, Object> map) {

			List<MusicVideoDTO> records = new Vector<MusicVideoDTO>();

			//페이징 미 적용
			//String sql="SELECT B.*,M.NAME FROM BBS B JOIN MEMBER M ON B.ID=M.ID ORDER BY NO DESC";
			//페이징 적용-구간쿼리로 변경
			String sql="SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM MV_MUSICVIDEO ";
			//검색용 쿼리 추가
			if(map.get("searchWord") !=null){
				sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
			}		
			sql+=" ORDER BY MV_NO DESC) T) WHERE R BETWEEN ? AND ?";
			
			try {
				psmt = conn.prepareStatement(sql);
				
				//페이징을 위한 시작 및 종료 rownum설정]
				psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
				psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
				
				rs = psmt.executeQuery();
				while(rs.next()){
					MusicVideoDTO dto = new MusicVideoDTO(
							rs.getString(1),
							rs.getDate(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getString(9));
					records.add(dto);
				}
			} catch (SQLException e) {			
				e.printStackTrace();
			}		
			return records;
		}/////////////////selectList
		

		
		public int getTotalCount(Map<String, Object> map) {
			int totalCount = 0;
			String sql = "SELECT COUNT(*) FROM MV_MUSICVIDEO ";
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
		
		
		
		public Map<String, MusicVideoDTO> prevNnext(String no) {
			Map<String, MusicVideoDTO> map = new HashMap<String, MusicVideoDTO>();
			String sql = "SELECT MV_NO, MV_TITLE FROM MV_MUSICVIDEO WHERE MV_NO=(SELECT MIN(MV_NO) FROM MV_MUSICVIDEO WHERE MV_NO > ?)";
			try {
				// 이전글 구하기]
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 이전글 존재
					map.put("PREV", new MusicVideoDTO(rs.getString(1),null,null,null,rs.getString(2),null,null,null,null));//MV_NO 및 MV_TITLE만 가져오기 

				}
				// 다음글 구하기
				sql = "SELECT MV_NO,MV_TITLE FROM MV_MUSICVIDEO WHERE MV_NO=(SELECT MAX(MV_NO) FROM MV_MUSICVIDEO WHERE MV_NO < ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 다음글 존재
					map.put("NEXT", new MusicVideoDTO(rs.getString(1),null,null,null,rs.getString(2),null,null,null,null));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return map;
		}//////////////// prevNnext
		
		
		
		
}

