package com.zamong.al.service.impl;

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
import com.zamong.ss.service.impl.SoundDAO;

public class AlbumDAO extends SoundDAO {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public AlbumDAO(ServletContext context) {
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

	public List<SoundDTO> selectList(Map<String, Object> map) {

		List<SoundDTO> records = new Vector<SoundDTO>();

		/*// 페이징 미 적용

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
				SoundDTO dto = new SoundDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toString()
						, rs.getString(6), rs.getDate(7).toString(), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));

				records.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return records;
	}///////////////// selectList
	
	public List<SoundDTO> selectAlbumList(Map<String, Object> map) {

		List<SoundDTO> records = new Vector<SoundDTO>();
		
		String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM "
				+ " (SELECT Z.* FROM ( " 
				+ " SELECT X.*, ROW_NUMBER() OVER (PARTITION BY X.AL_NO ORDER BY X.AL_NO DESC ,X.LK_COUNT DESC) NO FROM ( "
				+ " SELECT A.*, SL.SS_NO, SL.SS_TITLE, SL.SS_ALBUMTITLE, SL.LK_COUNT FROM AL_ALBUM A FULL OUTER JOIN ( "
				+ " SELECT * FROM SS_SOUNDSOURCE S FULL OUTER JOIN " 
				+ " (SELECT COUNT(*) LK_COUNT, LK_TARGETNO FROM LK_LIKE WHERE LK_FLAG = 'SS' GROUP by LK_TARGETNO ORDER BY LK_TARGETNO DESC) "
				+ " L ON S.SS_NO = L.LK_TARGETNO "
				+ " ) SL ON A.AL_NO = SL.AL_NO ";
		
		//앨범 총 곡수 구하기
		/*String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM "
				+ " (SELECT * FROM AL_ALBUM A FULL OUTER JOIN "
				+ " (SELECT count(*), al_no FROM SS_SOUNDSOURCE GROUP by al_no) S ON  A.AL_NO = S.AL_NO ";*/
				
		if (map.get("searchWord") != null) {
			sql += " WHERE A.AL_ALBUMNAME LIKE '%" + map.get("searchWord") + "%'";
		}

		sql += " ORDER BY A.AL_NO DESC ,LK_COUNT DESC ) X ) Z  WHERE Z.NO=1 ";
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
		String sql = "SELECT COUNT(*) FROM AL_ALBUM ";
		// 검색용 쿼리 추가
		if (map.get("searchWord") != null) {
			sql += " WHERE AL_ALBUMNAME LIKE '%" + map.get("searchWord") + "%' ";
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
	public int insert(SoundDTO dto, int i) {
		int affected = 0;
		String sql = "INSERT INTO AL_ALBUM(AL_NO, AL_REGIDATE, AL_ALBUMNAME, AL_ARTIST, AL_RELEASEDATE, AL_PUBLISHCORP, AL_ENTERTAINCORP, AL_ALBUMIMAGE, AL_ALBUMINFO, AL_DIVIDE, AL_ARTISTNO)"
				+ " VALUES(AL_ALBUM_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			//conn.setAutoCommit(false);
			if (i == 0) {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getAl_albumname());
				psmt.setString(2, dto.getAl_artist());
				psmt.setString(3, dto.getAl_releasedate());
				psmt.setString(4, dto.getAl_publishcorp());
				psmt.setString(5, dto.getAl_entertaincorp());
				psmt.setString(6, dto.getAl_albumimage());
				psmt.setString(7, dto.getAl_albuminfo());
				psmt.setString(8, dto.getAl_divide());
				psmt.setString(9, dto.getAl_artistno());
				psmt.executeUpdate();
			}
			affected = super.insert(dto);
			//if (affected == 1)
				//conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}///////////////// insert

	public NotiDataDTO selectOne(String no) {
		NotiDataDTO dto = null;
		String sql = "SELECT * FROM NT_NOTICE WHERE NT_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new NotiDataDTO();
				dto.setNt_no(rs.getString(1));
				dto.setNt_regidate(rs.getDate(2));
				dto.setAd_no(rs.getString(3));
				dto.setNt_classification(rs.getString(4));
				dto.setNt_title(rs.getString(5));
				dto.setNt_contents(rs.getString(6));
				dto.setNt_hitcount(rs.getString(7));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
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