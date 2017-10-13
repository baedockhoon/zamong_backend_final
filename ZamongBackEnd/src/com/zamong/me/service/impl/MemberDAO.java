package com.zamong.me.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;
import javax.sql.DataSource;

import org.apache.catalina.connector.Request;

import com.zamong.me.service.MemberDTO;
import com.zamong.mg.service.MagazineDTO;
import com.zamong.nt.service.NotiDataDTO;


public class MemberDAO {
	//멤버변수
		private Connection conn;
		private PreparedStatement psmt;
		private ResultSet rs;
		
		//생성자]
		public MemberDAO(ServletContext context){
			try {
				Context ctx = new InitialContext();
				DataSource source=(DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jdbc/zamong");
				conn = source.getConnection();	
				
			} 
			catch (NamingException e) {e.printStackTrace();}
			catch (SQLException e) {e.printStackTrace();}
			
		}//////////////////DataRoomDAO()
		
		public void close(){
			try{
				if(rs   != null) rs.close();
				if(psmt != null) psmt.close();
				if(conn != null) conn.close();
			}//try
			catch(Exception e){e.printStackTrace();}//catch
		}//close
	
		public int insert(MemberDTO dto) {
			int affected =0;
			String sql="INSERT INTO me_member(me_no,me_regidate, ME_ID , ME_PASS, ME_NAME ,ME_NICKNAME,ME_GENDER ,ME_BIRTH ,ME_EMAIL ,ME_TEL ,ME_PHOTO) VALUES(me_seq.NEXTVAL,sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getMe_id());
				psmt.setString(2, dto.getMe_pass());
				psmt.setString(3, dto.getMe_name());
				psmt.setString(4, dto.getMe_nickname());
				psmt.setString(5, dto.getMe_gender());
				psmt.setString(6, dto.getMe_birth());
				psmt.setString(7, dto.getMe_email());
				psmt.setString(8, dto.getMe_tel());
				psmt.setString(9, dto.getMe_photo());
				
				affected = psmt.executeUpdate();
			} 
			catch (SQLException e) {			
				e.printStackTrace();
			}		
			return affected;
			
		}
		public List<MemberDTO> selectList(Map<String, Object> map) {

			List<MemberDTO> records = new Vector<MemberDTO>();
			// 페이징 미 적용

			String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM me_member ";
			// 검색용 쿼리 추가

			
			try {
			if(map.get("searchWord") !=null){
				URLEncoder.encode(map.get("searchWord").toString(), "EUC-kr");
				sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
			}		
			sql += "ORDER BY me_NO DESC) T) " + "WHERE R BETWEEN ? AND ?";
				psmt = conn.prepareStatement(sql);
				
				// 페이징을 위한 시작 및 종료 rownum설정]
				psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
				psmt.setInt(2, Integer.parseInt(map.get("end").toString()));

				rs = psmt.executeQuery();
				while (rs.next()) {
					MemberDTO dto = new MemberDTO(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7),
							rs.getString(8), rs.getString(9), rs.getString(10),
							rs.getString(11),rs.getString(12));

					records.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return records;
		}///////////////// selectList
		public int getTotalRecordCount(Map map){
			int total = 0;
			String sql = "SELECT COUNT(*) FROM me_member";
			try{
				psmt = conn.prepareStatement(sql);
				 rs = psmt.executeQuery();
				rs.next();
				total = rs.getInt(1);
				
			}//try
			catch(Exception e){ e.printStackTrace(); };//catch
			
			return total;
			
		}//getTotalRecordCount
		
		public MemberDTO selectOne(String me_no) {
			MemberDTO dto=null;
			String sql="SELECT * FROM me_member WHERE me_no=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, me_no);
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new MemberDTO();
					dto.setMe_no(rs.getString(1));
					dto.setMe_regidate(rs.getString(2));
					dto.setMe_id(rs.getString(3));
					dto.setMe_pass(rs.getString(4));
					dto.setMe_name(rs.getString(5));
					dto.setMe_nickname(rs.getString(6));
					dto.setMe_gender(rs.getString(7));
					dto.setMe_birth(rs.getString(8));
					dto.setMe_email(rs.getString(9));
					dto.setMe_tel(rs.getString(10));
					dto.setMe_photo(rs.getString(11));
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()
		
		public int update(MemberDTO dto) {
			int affected=0;
			String sql="UPDATE me_member "
					+ "SET me_id=?,me_name=?,me_nickname=?,me_gender=?,me_birth=?,me_email=?,me_tel=?,me_photo=? "
					+ "WHERE me_no=?";
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getMe_id());
				psmt.setString(2, dto.getMe_name());
				psmt.setString(3, dto.getMe_nickname());
				psmt.setString(4, dto.getMe_gender());
				psmt.setString(5, dto.getMe_birth());
				psmt.setString(6, dto.getMe_email());
				psmt.setString(7, dto.getMe_tel());
				psmt.setString(8, dto.getMe_photo());
				psmt.setString(9, dto.getMe_no());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		public int delete(String no) {
			int affected=0;
			String sql="DELETE me_member WHERE me_no=?";		
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);			
				affected = psmt.executeUpdate();			
			} catch (SQLException e) {e.printStackTrace();}		
			return affected;
		}
		
		public ArrayList<MemberDTO> getMemberlist(String keyField){
		       
	        ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
	       
	        try{//실행
	           
	            String sql ="select * from me_member";
	           
	            if(keyField != null && !keyField.equals("") ){
	                sql +="WHERE "+keyField.trim()+" LIKE '%"+keyField.trim()+"%' order by id";
	            }else{//모든 레코드 검색
	                sql +="order by id";
	            }
	            System.out.println("sql = " + sql);
	            psmt = conn.prepareStatement(sql);
	            rs = psmt.executeQuery();
	           
	            while(rs.next()){
	                MemberDTO dto = new MemberDTO();
	               
	                dto.setMe_no(rs.getString(1));
	                dto.setMe_regidate(rs.getString(2));
	                dto.setMe_id(rs.getString(3));
	                dto.setMe_pass(rs.getString(4));
	                dto.setMe_name(rs.getString(5));
	                dto.setMe_nickname(rs.getString(6));
	                dto.setMe_gender(rs.getString(7));
	                dto.setMe_birth(rs.getString(8));
	                dto.setMe_email(rs.getString(9));
	                dto.setMe_tel(rs.getString(10));
	                dto.setMe_addr(rs.getString(11));
	                dto.setMe_photo(rs.getString(12));
	               
	                list.add(dto);
	            }
	        }catch(Exception e){           
	            System.out.println(e+"=> getMemberlist fail");         
	        }
	        return list;
	    }  
	
		
}//class
