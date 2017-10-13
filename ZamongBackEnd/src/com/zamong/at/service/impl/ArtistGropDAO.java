package com.zamong.at.service.impl;

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

import com.zamong.at.service.ArtistDTO;
import com.zamong.at.service.ArtistGropDTO;

public class ArtistGropDAO {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public ArtistGropDAO(ServletContext context) {
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

	public List<ArtistDTO> selectList(Map<String, Object> map) {

		List<ArtistDTO> records = new Vector<ArtistDTO>();

		// 페이징 미 적용

		String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM AT_ARTIST ";
		// 검색용 쿼리 추가
		if (!(map.get("searchWord") == null && map.get("Notice_category") == null)) {
			sql += " WHERE ";

			if (!(map.get("searchColumn").equals(""))) {
				sql += map.get("searchColumn") + " LIKE '%" + map.get("searchWord") + "%' ";
			}
			if (!map.get("Notice_category").equals("")) {
				sql += " NT_CLASSIFICATION = '" + map.get("Notice_category") + "'";
			}

		}
		sql += " ORDER BY NT_NO DESC) T) " + "WHERE R BETWEEN ? AND ?";
		System.out.println(sql);
		System.out.println(map.get("searchColumn"));
		System.out.println(map.get("Notice_category"));
		try {
			psmt = conn.prepareStatement(sql);

			// 페이징을 위한 시작 및 종료 rownum설정]
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));

			rs = psmt.executeQuery();
			while (rs.next()) {
				ArtistDTO dto = new ArtistDTO(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getDate(5).toString()
						, rs.getString(6), rs.getDate(7).toString(), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));

				records.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}///////////////// selectList

	public int getTotalRecordCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM NT_NOTICE ";
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

	/*
	 * public List selectList() { List list = new Vector(); String
	 * sql="SELECT * FROM NT_NOTICE ORDER BY NT_NO DESC"; try{
	 * 
	 * psmt = conn.prepareStatement(sql); rs = psmt.executeQuery();
	 * while(rs.next()){ ArtistDTO dto = new ArtistDTO();
	 * dto.setNt_no(rs.getString(1)); dto.setNt_regidate(rs.getDate(2));
	 * dto.setAd_no(rs.getString(3)); dto.setNt_classification(rs.getString(4));
	 * dto.setNt_title(rs.getString(5)); dto.setNt_contents(rs.getString(6));
	 * dto.setNt_hitcount(rs.getString(7)); list.add(dto); } } catch(SQLException
	 * e){e.printStackTrace();} return list; }/////////////selectList()
	 */
	public void updateVisitCount(String no) {
		String sql = "UPDATE NT_NOTICE SET NT_HITCOUNT=NT_HITCOUNT+1 WHERE NT_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//////////////////// updateVisitCount

	// 입력용]
	public int insert(ArtistDTO dto) {
		int affected = 0;
		String sql = "INSERT INTO GP_ATGROUP VALUES(GP_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, ?, ?)";
		try {

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getGp_name());
			psmt.setString(2, dto.getGp_gender());
			psmt.setString(3, dto.getGp_image());
			psmt.setString(4, dto.getGp_belong());
			psmt.setString(5, dto.getGp_debutdate());
			affected = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return affected;
	}///////////////// insert
	
	// 입력용]
	public int groupInsert() {
		int affected = 0;
		String sql = "INSERT INTO GM_GROUPMEMBER VALUES(GM_GROPMEMBER_SEQ.NEXTVAL, SYSDATE, (SELECT MAX(AT_NO) FROM AT_ARTIST), (SELECT MAX(GP_NO) FROM GP_ATGROUP))";
		try {

			psmt = conn.prepareStatement(sql);
			affected = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return affected;
	}///////////////// insert

	public ArtistDTO selectOne(String no) {
		ArtistDTO dto = null;
		String sql = "SELECT G.*, M.AT_NO FROM GP_ATGROUP G JOIN GM_GROUPMEMBER M ON G.GP_NO = M.GP_NO WHERE G.GP_NO = ? AND ROWNUM = 1";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new ArtistDTO();
				dto.setGp_no(rs.getString(1));
				dto.setGp_regidate(rs.getDate(2).toString());
				dto.setGp_name(rs.getString(3));
				dto.setGp_gender(rs.getString(4));
				dto.setGp_image(rs.getString(5));
				dto.setGp_belong(rs.getString(6));
				dto.setGp_debutdate(rs.getDate(7).toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}///////////////////// selectOne()
	
	public List<ArtistDTO> selectArtist(String no) {
		ArtistDTO dto = null;
		List<ArtistDTO> records = new Vector<ArtistDTO>();
		String sql = "SELECT A.* FROM (SELECT G.*, M.AT_NO FROM GP_ATGROUP G JOIN GM_GROUPMEMBER M ON G.GP_NO = M.GP_NO WHERE G.GP_NO=?) G JOIN AT_ARTIST A ON G.AT_NO = A.AT_NO";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				dto = new ArtistDTO();
				dto.setAt_no(rs.getString(1));
				dto.setAt_regidate(rs.getDate(2));
				dto.setAt_name(rs.getString(3));
				dto.setAt_belong(rs.getString(4));
				dto.setAt_debutdate(rs.getDate(5).toString());
				dto.setAt_debutsong(rs.getString(6));
				dto.setAt_birth(rs.getDate(7).toString());
				dto.setAt_contry(rs.getString(8));
				dto.setAt_gender(rs.getString(9));
				dto.setAt_artistinfo(rs.getString(10));
				dto.setAt_image(rs.getString(11));
				records.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}///////////////////// selectOne()


}