package com.zamong.ch.service.impl;

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

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.ch.service.CashDTO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.pr.service.ProblemDTO;






public class CashDAO {

	private Connection conn; 
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public CashDAO(ServletContext context){
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
		
	
	
public List<CashDTO> selectList(Map<String,Object> map){
	
	List<CashDTO> records = new Vector<CashDTO>();
	
	//페이징 미 적용
	//String sql="SELECT B.*,M.NAME FROM BBS B JOIN MEMBER M ON B.ID=M.ID ORDER BY NO DESC";
	//페이징 적용-구간쿼리로 변경
	String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT M.ME_id,C.* FROM ME_MEMBER M JOIN CH_PAYMENT C ON M.ME_NO = C.ME_NO ";
	//검색용 쿼리 추가
	if(map.get("searchWord") !=null){
		sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
	}		
	sql+=" ORDER BY CH_NO DESC,CH_REGIDATE DESC) T) WHERE R BETWEEN ? AND ?";
	
	try {
		psmt = conn.prepareStatement(sql);
		
		//페이징을 위한 시작 및 종료 rownum설정]
		psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
		psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
		
		rs = psmt.executeQuery();
		while(rs.next()){
			CashDTO dto = new CashDTO(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5));
			records.add(dto);
		}
	} catch (SQLException e) {			
		e.printStackTrace();
	}		
	return records;
}/////////////////selectList
public int getTotalRecordCount(Map<String,Object> map){
	int totalCount=0;
	String sql="SELECT COUNT(*) FROM ME_MEMBER M JOIN CH_PAYMENT C ON M.ME_NO = C.ME_NO  ";
	//검색용 쿼리 추가
	if(map.get("searchWord") !=null){
		sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
	}
	
	try {
		psmt = conn.prepareStatement(sql);
		
		rs= psmt.executeQuery();
		rs.next();
		totalCount = rs.getInt(1);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}	
	return totalCount;
}///////////////getTotalRecordCount	
	
	
	
	

	
		//입력용]
		/*public int insert(CashDTO dto) {
			int affected=0;
			String sql="INSERT INTO CH_PAYMENT(CH_NO,CH_REGIDATE,ME_NO,BP_NO,CH_HAVECASH)" + 
					"SELECT BP_SEQ.NEXTVAL,SYSDATE,ME_NO,BP_NO,?" + 
					"FROM BP_BUYPRODUCT";
			String sql="INSERT INTO CH_PAYMENT VALUES(CH_SEQ.NEXTVAL,SYSDATE,11,2,4000)";
			try {
				psmt = conn.prepareStatement(sql);
			   // psmt.setString(1, dto.getMe_no());
				//psmt.setString(2, dto.getBp_no());
				//psmt.setString(1, dto.getCh_havecash());
				
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////insert
*/		
		
		public CashDTO selectOne(String no/*String me_no*/) {
			CashDTO dto=null;
			String sql="SELECT ME_ID, SUM(CH_HAVECASH) FROM  CH_PAYMENT C join ME_MEMBER M ON C.ME_NO = M.ME_NO WHERE M.ME_NO=?  GROUP BY ME_ID ";
		/*	String sql="SELECT M.ME_ID,C.* FROM  CH_PAYMENT C join ME_MEMBER M ON C.ME_NO = M.ME_NO WHERE C.CH_NO=? ";*/	
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
			/*	psmt.setString(2, me_no);*/
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new CashDTO();
				
					dto.setMe_id(rs.getString(1));
					dto.setCh_havecash(rs.getString(2));
			
					
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()
		
		public int update(NotiDataDTO dto) {
			int affected=0;
			String sql="UPDATE NT_NOTICE "
					+ "SET NT_CLASSIFICATION=?,NT_TITLE=?,NT_CONTENTS=? "
					+ "WHERE NT_NO=?";
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getNt_classification());
				psmt.setString(2, dto.getNt_title());
				psmt.setString(3, dto.getNt_contents());			
				psmt.setString(4, dto.getNt_no());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		//삭제용
		public int delete(String no) {
			int affected=0;
			String sql="DELETE NT_NOTICE WHERE NT_NO=?";		
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);			
				affected = psmt.executeUpdate();			
			} catch (SQLException e) {e.printStackTrace();}		
			return affected;
		}
		
		public Map<String, NotiDataDTO> prevNnext(String no) {
			Map<String, NotiDataDTO> map = new HashMap<String, NotiDataDTO>();

			String sql = "SELECT NT_NO, NT_TITLE FROM NT_NOTICE WHERE NT_NO=(SELECT MIN(NT_NO) FROM NT_NOTICE WHERE NT_NO > ?)";
			try {
				// 이전글 구하기]
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 이전글 존재
					map.put("PREV", new NotiDataDTO(rs.getString(1),null,null,null,rs.getString(2),null,null));

				}
				// 다음글 구하기
				sql = "SELECT NT_NO,NT_TITLE FROM NT_NOTICE WHERE NT_NO=(SELECT MAX(NT_NO) FROM NT_NOTICE WHERE NT_NO < ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 다음글 존재
					map.put("NEXT", new NotiDataDTO(rs.getString(1),null,null,null,rs.getString(2),null,null));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return map;
		}//////////////// prevNnext
	
}