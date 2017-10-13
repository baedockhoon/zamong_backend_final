package com.zamong.ss.service.impl;

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

import com.zamong.nt.service.NotiDataDTO;
import com.zamong.ss.service.SoundDTO;

public class SoundDAO {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public SoundDAO(ServletContext context) {
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

	public List<SoundDTO> selectList(Map<String, Object> map) {

		List<SoundDTO> records = new Vector<SoundDTO>();
		
		String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM AL_ALBUM A FULL OUTER JOIN SS_SOUNDSOURCE S ON  A.AL_NO = S.AL_NO ";
				

		if (map.get("searchColumn") != null && map.get("searchWord") != null) {
		// 검색용 쿼리 추가
			sql += " WHERE ";
			if (map.get("searchColumn").toString().equalsIgnoreCase("AT")) {
				sql += " AL_ARTIST LIKE '%" + map.get("searchWord") + "%' ";
			}
			else if (map.get("searchColumn").toString().equalsIgnoreCase("AL")) {
				sql += " AL_ALBUMNAME LIKE '%" + map.get("searchWord") + "%' ";
			}
			else if (map.get("searchColumn").toString().equalsIgnoreCase("SS")) {
				sql += " S.SS_TITLE LIKE '%" + map.get("searchWord") + "%'";
			}
		}
		sql += " ORDER BY AL_NO DESC) T) WHERE R BETWEEN ? AND ?";
		try {
			psmt = conn.prepareStatement(sql);

			// 페이징을 위한 시작 및 종료 rownum설정]
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));

			rs = psmt.executeQuery();
			/* 곡번호 / 곡명 / 아티스트 / 앨범 / 좋아요 / */
			while (rs.next()) {
				SoundDTO dto = new SoundDTO();
				dto.setAl_no(rs.getString(1));
				dto.setAl_albumname(rs.getString(3));
				dto.setAl_artist(rs.getString(4));
				if (rs != null)
					dto.setAl_soundcount(rs.getString(12));

				records.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}///////////////// selectList
	
	public List<SoundDTO> selectSoundList(Map<String, Object> map) {

		List<SoundDTO> records = new Vector<SoundDTO>();
		String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM "
				+ " (SELECT * FROM (SELECT A.*, S.SS_NO, SS_TITLE, SS_ALBUMTITLE FROM AL_ALBUM A FULL OUTER JOIN SS_SOUNDSOURCE S ON  A.AL_NO = S.AL_NO ";
		if (map.get("searchWord") != null) {
			sql += " WHERE S.SS_TITLE LIKE '%" + map.get("searchWord") + "%'";
		}
		sql += " ) A FULL OUTER JOIN ";
		sql += " (SELECT COUNT(*) LK_COUNT, LK_TARGETNO FROM LK_LIKE WHERE LK_FLAG = 'SS' GROUP by LK_TARGETNO) L "
			+ " ON A.SS_NO = L.LK_TARGETNO ORDER BY AL_NO DESC, LK_COUNT DESC ";
		sql += " ) T) WHERE R BETWEEN ? AND ?";
		try {
			psmt = conn.prepareStatement(sql);

			// 페이징을 위한 시작 및 종료 rownum설정]
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));

			rs = psmt.executeQuery();
			/* 곡번호 / 곡명 / 아티스트 / 앨범 / 좋아요 / */
			while (rs.next()) {
				SoundDTO dto = new SoundDTO();
				dto.setAl_no(rs.getString(1));
				dto.setAl_albumname(rs.getString(3));
				dto.setAl_artist(rs.getString(4));
				dto.setSs_no(rs.getString(12));
				dto.setSs_title(rs.getString(13));
				dto.setSs_albumtitle(rs.getString(14));
				if (rs != null)
					dto.setSs_likecount(rs.getString(15));
				
				records.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}///////////////// selectList
	
	

	public int getTotalRecordCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM SS_SOUNDSOURCE ";
		// 검색용 쿼리 추가
		if (map.get("searchWord") != null) {
			sql += " WHERE SS_TITLE LIKE '%" + map.get("searchWord") + "%' ";
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
	 * while(rs.next()){ NotiDataDTO dto = new NotiDataDTO();
	 * dto.setNt_no(rs.getString(1)); dto.setNt_regidate(rs.getDate(2));
	 * dto.setAd_no(rs.getString(3)); dto.setNt_classification(rs.getString(4));
	 * dto.setNt_title(rs.getString(5)); dto.setNt_contents(rs.getString(6));
	 * dto.setNt_hitcount(rs.getString(7)); list.add(dto); } } catch(SQLException
	 * e){e.printStackTrace();} return list; }/////////////selectList()
	 */
	/*public void updateVisitCount(String no) {
		String sql = "UPDATE NT_NOTICE SET NT_HITCOUNT=NT_HITCOUNT+1 WHERE NT_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//////////////////// updateVisitCount
*/
	// 입력용]
	public int insert(SoundDTO dto) {
		int affected = 0;
		String sql = "INSERT INTO SS_SOUNDSOURCE(SS_NO, SS_REGIDATE, AL_NO, SS_TITLE, SS_GENRE, SS_DURATION, SS_PATH, SS_ALBUMTITLE)"
				+ " VALUES(SS_SOUNDSOURCE_SEQ.NEXTVAL, SYSDATE, (SELECT MAX(AL_NO) FROM AL_ALBUM), ?, ?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getSs_title());
			psmt.setString(2, dto.getSs_genre());
			psmt.setString(3, dto.getSs_duration());
			psmt.setString(4, dto.getSs_path());
			psmt.setString(5, dto.getSs_albumtitle());
			psmt.executeUpdate();
			
			affected = lyInsert(dto);
			if (affected == 1)
				conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}///////////////// insert
	
	public int lyInsert(SoundDTO dto) {
		int affected = 0;
		String sql = "INSERT INTO LY_LYLICS(LY_NO, LY_CONTENTS, SS_NO)"
				+ " VALUES(LY_SEQ.NEXTVAL, ?, (SELECT MAX(SS_NO) FROM SS_SOUNDSOURCE))";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getLy_contents());
			affected = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}///////////////// insert

	public List<SoundDTO> searchArtist(Map<String, String> map) {
		List<SoundDTO> records = new Vector<SoundDTO>();
		/*String searchWord = map.get("searchWord");
		String searchColumn = map.get("searchColumn");
		try {
			String sql;
			if (searchColumn.equals("") || searchColumn.equalsIgnoreCase("S")) {
				sql = "SELECT * FROM AT_ARTIST WHERE AT_NAME LIKE '%" + searchWord + "%'";
				psmt = conn.prepareStatement(sql);
				// psmt.setString(1, searchWord);
				rs = psmt.executeQuery();
				while (rs.next()) {
					SoundDTO dto = new SoundDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getDate(5).toString(), rs.getString(6), rs.getDate(7).toString(), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11));
					records.add(dto);
				}
				sql = "SELECT A.* FROM "
						+ " (SELECT AT.*, GPM.GP_NO FROM AT_ARTIST AT FULL OUTER JOIN GPM_GROUPMEMBER GPM ON AT.AT_NO = GPM.AT_NO)"
						+ "  A FULL OUTER JOIN GP_ATGROUP G ON A.GP_NO = G.GP_NO " + " WHERE G.GP_NAME LIKE '%"
						+ searchWord + "%' AND A.AT_NAME NOT LIKE '%" + searchWord + "%'";

				psmt = conn.prepareStatement(sql);
				// psmt.setString(1, searchWord);
				rs = psmt.executeQuery();
				while (rs.next()) {
					SoundDTO dto = new SoundDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
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
					SoundDTO dto = new SoundDTO();
					dto.setGp_no(rs.getString(1));
					dto.setAt_regidate(rs.getDate(2).toString());
					dto.setAt_name(rs.getString(3));
					dto.setAt_gender(rs.getString(4));
					dto.setAt_image(rs.getString(5));
					dto.setAt_belong(rs.getString(6));
					dto.setAt_debutdate(rs.getDate(7).toString());
					records.add(dto);
				}

				if(!searchColumn.equalsIgnoreCase("G")) {
					sql = "SELECT DISTINCT G.* FROM "
							+ " (SELECT AT.*, GPM.GP_NO FROM AT_ARTIST AT FULL OUTER JOIN GPM_GROUPMEMBER GPM ON AT.AT_NO = GPM.AT_NO) "
							+ " A FULL OUTER JOIN GP_ATGROUP G ON A.GP_NO = G.GP_NO "
							+ " WHERE ";
					
							sql += " A.AT_NAME LIKE '%" + searchWord + "%' AND ";
					sql += "G.GP_NAME NOT LIKE '%" + searchWord + "%' AND NOT G.GP_NAME IS NULL";
	
					psmt = conn.prepareStatement(sql);
					// psmt.setString(1, searchWord);
					rs = psmt.executeQuery();
					while (rs.next()) {
						SoundDTO dto = new SoundDTO();
						dto.setGp_no(rs.getString(1));
						dto.setAt_regidate(rs.getDate(2).toString());
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
		}*/
		return records;
	}///////////////////// selectOne()

	public int update(NotiDataDTO dto) {
		int affected = 0;
		String sql = "UPDATE NT_NOTICE " + "SET NT_CLASSIFICATION=?,NT_TITLE=?,NT_CONTENTS=? " + "WHERE NT_NO=?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getNt_classification());
			psmt.setString(2, dto.getNt_title());
			psmt.setString(3, dto.getNt_contents());
			psmt.setString(4, dto.getNt_no());
			affected = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return affected;
	}//////////////////// update

	// 삭제용
	public int delete(String no) {
		int affected = 0;
		String sql = "DELETE NT_NOTICE WHERE NT_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				map.put("PREV", new NotiDataDTO(rs.getString(1), null, null, null, rs.getString(2), null, null));

			}
			// 다음글 구하기
			sql = "SELECT NT_NO,NT_TITLE FROM NT_NOTICE WHERE NT_NO=(SELECT MAX(NT_NO) FROM NT_NOTICE WHERE NT_NO < ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if (rs.next()) {// 다음글 존재
				map.put("NEXT", new NotiDataDTO(rs.getString(1), null, null, null, rs.getString(2), null, null));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}//////////////// prevNnext

}