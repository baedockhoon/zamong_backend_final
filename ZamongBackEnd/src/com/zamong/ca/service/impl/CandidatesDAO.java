package com.zamong.ca.service.impl;

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

import com.zamong.ca.service.CandidatesDTO;

public class CandidatesDAO {
	//멤버변수]
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public CandidatesDAO(ServletContext context){
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
		String sql="SELECT * FROM CA_CANDIDATES ORDER BY CA_NO DESC";
		try{
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				CandidatesDTO dto = new CandidatesDTO();
				dto.setCa_no(rs.getString(1));
				dto.setCa_regidate(rs.getDate(2));
				dto.setSs_no(rs.getString(3));
				dto.setAt_no(rs.getString(4));
				dto.setMa_no(rs.getString(5));
				list.add(dto);
				
				/*	private String ca_no;
					private java.sql.Date ca_regidate;
					private String ss_no;
					private String at_no;
					private String ma_no;*/
			}
		}
		catch(SQLException e){e.printStackTrace();}
		return list;
	}/////////////selectList()

	//입력용]
	public int insert(CandidatesDTO dto) {
		int affected=0;
		String sql="INSERT INTO CA_CANDIDATES VALUES(CA_SEQ.NEXTVAL,SYSDATE,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getSs_no());
			psmt.setString(2, dto.getAt_no());
			psmt.setString(3, dto.getMa_no());
			affected = psmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return affected;
	}/////////////////insert
	
	
	//삭제용
	public int delete(String no) {
		int affected =0;
		String sql="DELETE CA_CANDIDATES WHERE CA_NO=?";
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
	
	
	public CandidatesDTO selectOne(String no) {
		CandidatesDTO dto=null;
		
		String sql="SELECT * FROM CA_CANDIDATES WHERE CA_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if(rs.next()){
				dto = new CandidatesDTO();
				dto.setCa_no(rs.getString(1));
				dto.setCa_regidate(rs.getDate(2));
				dto.setSs_no(rs.getString(3));
				dto.setAt_no(rs.getString(4));
				dto.setMa_no(rs.getString(5));
			}			
		} catch (SQLException e) {e.printStackTrace();}		
		return dto;
	}/////////////////////selectOne()

	//수정용]
	public int update(CandidatesDTO dto) {
		int affected=0;
		String sql="UPDATE CA_CANDIDATES SET SS_NO=?,AT_NO=?,MA_NO=? WHERE CA_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getSs_no());
			psmt.setString(2, dto.getAt_no());
			psmt.setString(3, dto.getMa_no());
			psmt.setString(4, dto.getCa_no());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return affected;
	}////////////////////update
	
	/*	private String ca_no;
	private java.sql.Date ca_regidate;
	private String ss_no;
	private String at_no;
	private String ma_no;*/
	
	public List<CandidatesDTO> selectList(Map<String, Object> map,String ma_no) {

		List<CandidatesDTO> records = new Vector<CandidatesDTO>();

		//String sql="SELECT * FROM CA_CANDIDATES ORDER BY CA_NO DESC";
		/*String sql = "select c.ca_no,c.ca_regidate,s.ss_title,at_name,ma_title "
				+ "from ca_candidates c join SS_SOUNDSOURCE s on c.ss_no=s.ss_no join AT_artist a on c.at_no=a.at_no join MA_musicaward m on c.ma_no=m.ma_no "
				+ "order by c.ca_no desc";*/
		
		String sql="";
		if(ma_no!=null) {
			sql="select c.ca_no,c.ca_regidate,s.ss_title,at_name,ma_title from ca_candidates c join SS_SOUNDSOURCE s on c.ss_no=s.ss_no join AT_artist a on c.at_no=a.at_no join MA_musicaward m on c.ma_no=m.ma_no where m.ma_no="
				+ ma_no+" order by c.ca_no desc";
		}
		else {
			sql = "select c.ca_no,c.ca_regidate,s.ss_title,at_name,ma_title "
					+ "from ca_candidates c join SS_SOUNDSOURCE s on c.ss_no=s.ss_no join AT_artist a on c.at_no=a.at_no join MA_musicaward m on c.ma_no=m.ma_no "
					+ "order by c.ca_no desc";
		}
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				CandidatesDTO dto = new CandidatesDTO(
						rs.getString(1),
						rs.getDate(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5));
				records.add(dto);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return records;
	}/////////////////selectList
	

	
	public int getTotalCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM CA_CANDIDATES";
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
	
	

	
	
}
