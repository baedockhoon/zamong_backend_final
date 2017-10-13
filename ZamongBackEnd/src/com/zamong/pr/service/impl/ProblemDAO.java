package com.zamong.pr.service.impl;

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
import com.zamong.pr.service.ProblemDTO;
import com.zamong.qu.service.UserQuestionDTO;





public class ProblemDAO {

	private Connection conn; 
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public ProblemDAO(ServletContext context){
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
public List<ProblemDTO> selectList(Map<String,Object> map){
		
		List<ProblemDTO> records = new Vector<ProblemDTO>();
		
		//페이징 미 적용
		//String sql="SELECT B.*,M.NAME FROM BBS B JOIN MEMBER M ON B.ID=M.ID ORDER BY NO DESC";
		//페이징 적용-구간쿼리로 변경
		String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM MQ_MYQUESTION ";
		//검색용 쿼리 추가
		if(map.get("searchWord") !=null){
			sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
		}		
		sql+=" ORDER BY MQ_NO DESC) T) WHERE R BETWEEN ? AND ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			//페이징을 위한 시작 및 종료 rownum설정]
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			
			rs = psmt.executeQuery();
			while(rs.next()){
				ProblemDTO dto = new ProblemDTO(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15));
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
		String sql = "SELECT COUNT(*) FROM MQ_MYQUESTION ";
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
		public int insert(ProblemDTO dto) {
			int affected=0;
			String sql="INSERT INTO MQ_MYQUESTION(MQ_NO,MQ_REGIDATE,ME_NO,MQ_USEENV,MQ_OS,MQ_BROWSER,MQ_USEMODEL,MQ_TEL,MQ_EMAIL,MQ_TITLE,MQ_CONTENTS,MQ_UPLOADFILE,MQ_STATUS,MQ_LARGEDIVIDE,MQ_MEDIUMDIVIDE) "
					+ " VALUES(MG_SEQ.NEXTVAL,SYSDATE,2,?,?,?,?,?,?,?,?,?,DEFAULT,?,?)";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getMq_useenv());
				psmt.setString(2, dto.getMq_os());
				psmt.setString(3, dto.getMq_browser());
				psmt.setString(4, dto.getMq_usemodel());
				psmt.setString(5, dto.getMq_tel());
				psmt.setString(6, dto.getMq_email());
				psmt.setString(7, dto.getMq_title());
				psmt.setString(8, dto.getMq_contents());
				psmt.setString(9, dto.getMq_uploadfile());	
				psmt.setString(10, dto.getMq_largedivide());
				psmt.setString(11, dto.getMq_mediumdivide());
	
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////insert
		
		
		public ProblemDTO selectOne(String no) {
			ProblemDTO dto=null;
			String sql="SELECT * FROM MQ_MYQUESTION WHERE MQ_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()){
				 dto = new ProblemDTO(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15));									
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()
		
		public int update(ProblemDTO dto) {
			int affected=0;
			String sql="UPDATE MQ_MYQUESTION "
					+ "SET MQ_USEENV=?,MQ_OS=?,MQ_BROWSER=?,MQ_USEMODEL=?,MQ_TEL=?,MQ_EMAIL=?,MQ_TITLE=?,MQ_CONTENTS=?,MQ_UPLOADFILE=?,MQ_LARGEDIVIDE=?,MQ_MEDIUMDIVIDE=? "
					+ "WHERE MQ_NO=?";
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getMq_useenv());
				psmt.setString(2, dto.getMq_os());
				psmt.setString(3, dto.getMq_browser());
				psmt.setString(4, dto.getMq_usemodel());
				psmt.setString(5, dto.getMq_tel());
				psmt.setString(6, dto.getMq_email());
				psmt.setString(7, dto.getMq_title());
				psmt.setString(8, dto.getMq_contents());
				psmt.setString(9, dto.getMq_uploadfile());	
				psmt.setString(10, dto.getMq_largedivide());
				psmt.setString(11, dto.getMq_mediumdivide());
				psmt.setString(12, dto.getMq_no());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		//삭제용
		public int delete(String no) {
			int affected=0;
			try {
				conn.setAutoCommit(false);
				String sql="DELETE MQ_MYQUESTION WHERE MQ_NO=?";		
			
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				affected = psmt.executeUpdate();
				if(affected == 1) {
					 sql="DELETE AD_ADMIN WHERE AD_NO=?";	
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, no);			
					affected = psmt.executeUpdate();	
					conn.commit();
				} 	
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return affected;
			
		}
		
		public Map<String, ProblemDTO> prevNnext(String no) {
			Map<String, ProblemDTO> map = new HashMap<String, ProblemDTO>();

			String sql = "SELECT MQ_NO, MQ_TITLE FROM MQ_MYQUESTION WHERE MQ_NO=(SELECT MIN(MQ_NO) FROM MQ_MYQUESTION WHERE MQ_NO > ?)";
			try {
				// 이전글 구하기]
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 이전글 존재
					map.put("PREV", new ProblemDTO(rs.getString(1),null,null,null,null,null,null,null,null,rs.getString(2),null,null,null,null,null));

				}
				// 다음글 구하기
			 sql = "SELECT MQ_NO, MQ_TITLE FROM MQ_MYQUESTION WHERE MQ_NO=(SELECT MAX(MQ_NO) FROM MQ_MYQUESTION WHERE MQ_NO < ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 다음글 존재
					map.put("NEXT", new ProblemDTO(rs.getString(1),null,null,null,null,null,null,null,null,rs.getString(2),null,null,null,null,null));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return map;
		}//////////////// prevNnext
	
}