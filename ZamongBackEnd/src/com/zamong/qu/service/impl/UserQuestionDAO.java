package com.zamong.qu.service.impl;

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

import com.zamong.mg.service.MagazineDTO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.qu.service.UserQuestionDTO;





public class UserQuestionDAO {

	private Connection conn; 
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public UserQuestionDAO(ServletContext context){
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
public List<UserQuestionDTO> selectList(Map<String,Object> map){
		
		List<UserQuestionDTO> records = new Vector<UserQuestionDTO>();
		
		//페이징 미 적용
		//String sql="SELECT B.*,M.NAME FROM BBS B JOIN MEMBER M ON B.ID=M.ID ORDER BY NO DESC";
		//페이징 적용-구간쿼리로 변경
		String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM QU_QUESTION1 ";
		//검색용 쿼리 추가
		if (!(map.get("qu_largedivide") == null && map.get("searchWord") == null && map.get("qu_mediumdivide") == null) 
				&& !(map.get("qu_largedivide").equals("") && map.get("searchWord").equals("") && map.get("qu_mediumdivide").equals(""))) {
			
			sql += " WHERE ";
			
			String notice = map.get("qu_largedivide").equals("") ? " LIKE '%%' " : " = '" + map.get("qu_largedivide").toString() + "'";
			String searchWord = map.get("searchWord") == null ? " '%%' " : "'%"+map.get("searchWord").toString()+"%'";
	String mediumdivide = map.get("qu_mediumdivide").equals("") ? " LIKE '%%' " : " = '" + map.get("qu_mediumdivide").toString() + "'";
			
			sql += " QU_LARGEDIVIDE " + notice;
			sql += " AND QU_MEDIUMDIVIDE " + mediumdivide;
			sql+=" AND "+map.get("searchColumn")+ " LIKE " + searchWord;
		}
		sql+=" ORDER BY QU_NO DESC) T) WHERE R BETWEEN ? AND ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			//페이징을 위한 시작 및 종료 rownum설정]
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			
			rs = psmt.executeQuery();
			while(rs.next()){
				UserQuestionDTO dto = new UserQuestionDTO(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9));
				records.add(dto);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return records;
	}/////////////////selectList
	/*public List selectList(int start, int end) {
		List list = new Vector();
		String sql="SELECT * FROM (SELECT T.*,ROWNUM R FROM(SELECT * FROM QU_QUESTION1 ORDER BY QU_NO DESC) T) WHERE R BETWEEN ? AND ?";
		//String sql="SELECT * FROM MG_MAGAZINE ORDER BY MG_NO DESC";
		try{
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			
			rs = psmt.executeQuery();
			while(rs.next()){
				UserQuestionDTO dto = new UserQuestionDTO(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9));
				
				list.add(dto);
			}
		}
		catch(SQLException e){e.printStackTrace();}
		return list;
	}/////////////selectList()
*/	public int getTotalRecordCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM QU_QUESTION1 ";
		// 검색용 쿼리 추가
		if (map.get("searchWord") != null) {
			sql += " WHERE " + map.get("searchColumn") + " LIKE '%" + map.get("searchWord") + "%' ";
		}

		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}/////////////// getTotalRecordCount
	
	
	
	public void updateVisitCount(String no) {
		String sql = "UPDATE QU_QUESTION1 SET QU_HITCOUNT=QU_HITCOUNT+1 WHERE QU_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//////////////////// updateVisitCount
	
		//입력용]
		public int insert(UserQuestionDTO dto) {
			int affected=0;
			String sql="INSERT INTO QU_QUESTION1(QU_no,QU_regidate,ad_no,qu_largedivide,qu_mediumdivide,QU_title,QU_contents,qu_hitcount) VALUES(QU_SEQ.NEXTVAL,SYSDATE,2,?,?,?,?,0)";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getQu_largedivide());
				psmt.setString(2, dto.getQu_mediumdivide());
				psmt.setString(3, dto.getQu_title());
				psmt.setString(4, dto.getQu_contents());
	
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////insert
		
		
		public UserQuestionDTO selectOne(String no) {
			UserQuestionDTO dto=null;
			String sql="SELECT * FROM QU_QUESTION1 WHERE QU_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()){
				 dto = new UserQuestionDTO(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9));
				
					
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()
		
		public int update(UserQuestionDTO dto) {
			int affected=0;
			String sql="UPDATE QU_QUESTION1 "
					+ "SET qu_largedivide=?,qu_mediumdivide=?,QU_TITLE=?,QU_CONTENTS=? "
					+ "WHERE QU_NO=?";
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,dto.getQu_largedivide());
				psmt.setString(2,dto.getQu_mediumdivide() );
				psmt.setString(3,dto.getQu_title() );			
				psmt.setString(4,dto.getQu_contents());
				psmt.setString(5, dto.getQu_no());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		//삭제용
		public int delete(String no) {
			int affected=0;
			String sql="DELETE QU_QUESTION1 WHERE QU_NO=?";		
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);			
				affected = psmt.executeUpdate();			
			} catch (SQLException e) {e.printStackTrace();}		
			return affected;
		}
		
		public Map<String, UserQuestionDTO> prevNnext(String no) {
			Map<String, UserQuestionDTO> map = new HashMap<String,UserQuestionDTO>();

			String sql = "SELECT QU_NO, QU_TITLE FROM QU_QUESTION1 WHERE QU_NO=(SELECT MIN(QU_NO) FROM QU_QUESTION1 WHERE QU_NO > ?)";
			try {
				// 이전글 구하기]
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 이전글 존재
					map.put("PREV", new UserQuestionDTO(rs.getString(1),null,null,null,null,rs.getString(2),null,null,null));

				}
				// 다음글 구하기
				sql = "SELECT QU_NO, QU_TITLE FROM QU_QUESTION1 WHERE QU_NO=(SELECT MAX(QU_NO) FROM QU_QUESTION1 WHERE QU_NO <?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 다음글 존재
					map.put("NEXT", new UserQuestionDTO(rs.getString(1),null,null,null,null,rs.getString(2),null,null,null));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return map;
		}//////////////// prevNnext
	
}