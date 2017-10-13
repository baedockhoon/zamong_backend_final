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

public class ArtistDAO extends ArtistGropDAO {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public ArtistDAO(ServletContext context) {
		super(context);
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
		sql += " ORDER BY AT_NO DESC) T) " + "WHERE R BETWEEN ? AND ?";
		try {
			psmt = conn.prepareStatement(sql);

			// 페이징을 위한 시작 및 종료 rownum설정]
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));

			rs = psmt.executeQuery();
			while (rs.next()) {
				ArtistDTO dto = new ArtistDTO(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4),
						rs.getDate(5).toString(), rs.getString(6), rs.getDate(7).toString(), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11));

				records.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}///////////////// selectList

	public int getTotalRecordCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM AT_ARTIST ";
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
	public int insert(ArtistDTO dto, String grop) {
		int affected = 0;
		String sql = "INSERT INTO AT_ARTIST(AT_NO, AT_REGIDATE, AT_NAME, AT_BELONG, AT_DEBUTDATE, AT_DEBUTSONG, AT_BIRTH, AT_CONTRY, AT_GENDER, AT_ARTISTINFO, AT_IMAGE)"
				+ " VALUES(AT_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getAt_name());
				psmt.setString(2, dto.getAt_belong());
				psmt.setString(3, dto.getAt_debutdate());
				psmt.setString(4, dto.getAt_debutsong());
				psmt.setString(5, dto.getAt_birth());
				psmt.setString(6, dto.getAt_contry());
				psmt.setString(7, dto.getAt_gender());
				psmt.setString(8, dto.getAt_artistinfo());
				psmt.setString(9, dto.getAt_image());
				affected = psmt.executeUpdate();

			if (grop.equalsIgnoreCase("G")) {
				affected = super.insert(dto);
				super.groupInsert();
			}
			//if (affected == 1)
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}///////////////// insert

	public List<ArtistDTO> searchArtist(Map<String, Object> map) {
		List<ArtistDTO> records = new Vector<ArtistDTO>();
		String searchWord = map.get("searchWord") != null ? map.get("searchWord").toString() : "" ;
		String searchColumn = map.get("searchColumn") != null ? map.get("searchColumn").toString() : "";
		try {
			/*String sql = "SELECT A.*, G.* FROM "  
					+" (SELECT AT.*, GM.GP_NO FROM AT_ARTIST AT FULL OUTER JOIN GM_GROUPMEMBER GM ON AT.AT_NO = GM.AT_NO) "
					+" A FULL OUTER JOIN GP_ATGROUP G ON A.GP_NO = G.GP_NO "
					+" WHERE G.GP_NAME LIKE '%"+searchWord+"%' OR A.AT_NAME LIKE '%"+searchWord+"%'";sql = "SELECT * FROM AT_ARTIST WHERE AT_NAME LIKE '%" + searchWord + "%'";
					psmt = conn.prepareStatement(sql);
					// psmt.setString(1, searchWord);
					rs = psmt.executeQuery();
					while (rs.next()) {
						ArtistDTO dto = new ArtistDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getDate(5).toString(), rs.getString(6), rs.getDate(7).toString(), rs.getString(8),
								rs.getString(9), rs.getString(10), rs.getString(11));
						records.add(dto);
						if(rs != null) {
							dto.setGp_no(rs.getString(13));
							dto.setAt_regidate(rs.getDate(14).toString());
							dto.setAt_name(rs.getString(15));
							dto.setAt_gender(rs.getString(16));
							dto.setAt_image(rs.getString(17));
							dto.setAt_belong(rs.getString(18));
							dto.setAt_debutdate(rs.getDate(19).toString());
							records.add(dto);
						}
					}*/
			String sql;
			if (searchColumn.equals("") || searchColumn.equalsIgnoreCase("S")) {
				sql = "SELECT * FROM AT_ARTIST WHERE AT_NAME LIKE '%" + searchWord + "%'";
				psmt = conn.prepareStatement(sql);
				// psmt.setString(1, searchWord);
				rs = psmt.executeQuery();
				while (rs.next()) {
					ArtistDTO dto = new ArtistDTO(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4),
							rs.getDate(5).toString(), rs.getString(6), rs.getDate(7).toString(), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11));
					records.add(dto);
				}
				sql = "SELECT A.* FROM "
						+ " (SELECT AT.*, GM.GP_NO FROM AT_ARTIST AT FULL OUTER JOIN GM_GROUPMEMBER GM ON AT.AT_NO = GM.AT_NO)"
						+ "  A FULL OUTER JOIN GP_ATGROUP G ON A.GP_NO = G.GP_NO " + " WHERE G.GP_NAME LIKE '%"
						+ searchWord + "%' AND A.AT_NAME NOT LIKE '%" + searchWord + "%'";

				psmt = conn.prepareStatement(sql);
				// psmt.setString(1, searchWord);
				rs = psmt.executeQuery();
				while (rs.next()) {
					ArtistDTO dto = new ArtistDTO(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4),
							rs.getDate(5).toString(), rs.getString(6), rs.getDate(7).toString(), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11));
					records.add(dto);
				}
			}

			if (searchColumn.equals("") || searchColumn.equalsIgnoreCase("G")) {

				sql = "SELECT * FROM GP_ATGROUP WHERE GP_NAME LIKE '%" + searchWord + "%'";

				psmt = conn.prepareStatement(sql);
				// psmt.setString(1, searchWord);
				rs = psmt.executeQuery();
				while (rs.next()) {
					ArtistDTO dto = new ArtistDTO();
					dto.setGp_no(rs.getString(1));
					dto.setAt_regidate(rs.getDate(2));
					dto.setAt_name(rs.getString(3));
					dto.setAt_gender(rs.getString(4));
					dto.setAt_image(rs.getString(5));
					dto.setAt_belong(rs.getString(6));
					dto.setAt_debutdate(rs.getDate(7).toString());
					records.add(dto);
				}

				if(!searchColumn.equalsIgnoreCase("G")) {
					sql = "SELECT DISTINCT G.* FROM "
							+ " (SELECT AT.*, GM.GP_NO FROM AT_ARTIST AT FULL OUTER JOIN GM_GROUPMEMBER GM ON AT.AT_NO = GM.AT_NO) "
							+ " A FULL OUTER JOIN GP_ATGROUP G ON A.GP_NO = G.GP_NO "
							+ " WHERE ";
					
							sql += " A.AT_NAME LIKE '%" + searchWord + "%' AND ";
					sql += "G.GP_NAME NOT LIKE '%" + searchWord + "%' AND NOT G.GP_NAME IS NULL";
	
					psmt = conn.prepareStatement(sql);
					// psmt.setString(1, searchWord);
					rs = psmt.executeQuery();
					while (rs.next()) {
						ArtistDTO dto = new ArtistDTO();
						dto.setGp_no(rs.getString(1));
						dto.setAt_regidate(rs.getDate(2));
						dto.setAt_name(rs.getString(3));
						dto.setAt_gender(rs.getString(4));
						dto.setAt_image(rs.getString(5));
						dto.setAt_belong(rs.getString(6));
						dto.setAt_debutdate(rs.getDate(7).toString());
						records.add(dto);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}///////////////////// searchArtist()

	public ArtistDTO selectArtistOne(String no) {
		ArtistDTO dto = null;
		String sql = "SELECT * FROM AT_ARTIST WHERE AT_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if (rs.next()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}///////////////////// selectOne()

}