package com.zamong.web.album;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.zamong.al.service.AlbumDTO;
import com.zamong.al.service.impl.AlbumDAO;
import com.zamong.ss.service.SoundDTO;
import com.zamong.ss.service.impl.SoundDAO;

import model.FileUtils;
import model.PagingUtil;

public class AlbumController extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		String mode = req.getMethod();

		if (url.toUpperCase().contains("WRITE.DO")) {
			if (mode.equalsIgnoreCase("get"))
				req.getRequestDispatcher("/bbs/album/albumWrite.jsp").forward(req, resp);
			else {
				
				AlbumDAO dao = new AlbumDAO(req.getServletContext());
				req.setCharacterEncoding("UTF-8");


				SoundDTO dto = new SoundDTO();
				String al_divide = req.getParameter("al_divide");
				String al_albumname = req.getParameter("al_albumname");
				String al_releasedate = req.getParameter("al_releasedate");
				String al_publishcorp = req.getParameter("al_publishcorp");
				String al_entertaincorp = req.getParameter("al_entertaincorp");
				String al_albuminfo = req.getParameter("al_albuminfo");
				String al_albumimage = req.getParameter("al_albumimage");
				String al_artist = req.getParameter("al_artist");
				String al_artistno = req.getParameter("al_artistno");
				

				/*psmt.setString(1, dto.getAl_albumname());
				psmt.setString(2, dto.getAl_artist());
				psmt.setString(3, dto.getAl_releasedate());
				psmt.setString(4, dto.getAl_publishcorp());
				psmt.setString(5, dto.getAl_entertaincorp());
				psmt.setString(6, dto.getAl_albumimage());
				psmt.setString(7, dto.getAl_albuminfo());
				psmt.setString(8, dto.getAl_divide());
				psmt.setString(9, dto.getAl_artistno());*/
				
				dto.setAl_divide(al_divide);
				dto.setAl_albumname(al_albumname);
				dto.setAl_releasedate(al_releasedate);
				dto.setAl_publishcorp(al_publishcorp);
				dto.setAl_entertaincorp(al_entertaincorp);
				dto.setAl_albuminfo(al_albuminfo);
				dto.setAl_albumimage(al_albumimage);
				dto.setAl_artist(al_artist);
				dto.setAl_artistno(al_artistno);
				int index = Integer.parseInt(req.getParameter("index"));
				
				for (int i = 0; i < index; i++) {
					System.out.println(i);
					String ss_title = req.getParameter("ss_title"+i);
					String ss_albumtitle = req.getParameter("ss_albumtitle"+i) == null ? "0" : req.getParameter("ss_albumtitle"+i);
					String ss_genre = req.getParameter("ss_genre"+i);
					String ly_contents = req.getParameter("ly_contents"+i);
					String ss_duration = req.getParameter("ss_duration"+i);
					String ss_path = req.getParameter("ss_path"+i);
					
					dto.setSs_title(ss_title);
					dto.setSs_albumtitle(ss_albumtitle);
					dto.setSs_genre(ss_genre);
					dto.setLy_contents(ly_contents);
					dto.setSs_duration(ss_duration);
					dto.setSs_path(ss_path);
					
					dao.insert(dto, i);
				}
				
				
				
				req.getRequestDispatcher("/ZAMONG/Album/List.do").forward(req, resp);
				
				/*MultipartRequest mr = FileUtils.upload(req, req.getServletContext().getRealPath("/Images/Sound"));

				int sucorfail;// DB입력 성공시에는 1, 실패시0, 파일용량 초과시에는 -1

				if (mr != null) {// 파일 업로드 성공시 데이터 입력처리
					// 기타 파라미터 받아서 테이블에 입력처리

					SoundDAO dao = new SoundDAO(req.getServletContext());
					
					//그룹정보
					grop = mr.getParameter("grop");
					gp_name = mr.getParameter("gp_name");
					gp_gender = mr.getParameter("gp_gender");
					gp_image = mr.getFilesystemName("gp_image");
					
					//아티스트 정보
					at_name = mr.getParameter("at_name");
					at_belong = mr.getParameter("at_belong");
					at_debutdate = mr.getParameter("at_debutdate");
					at_debutsong = mr.getParameter("at_debutsong");
					at_birth = mr.getParameter("at_birth");
					at_contry = mr.getParameter("at_contry");
					at_gender = mr.getParameter("at_gender");
					at_Soundinfo = mr.getParameter("at_Soundinfo");
					at_image = mr.getFilesystemName("at_image");

					// 그룹 데이터베이스 CRUD작업과 관련된 모델 호출
					dto.setGp_name(gp_name);
					dto.setGp_gender(gp_gender);
					dto.setGp_image(gp_image);

					// 아티스트 데이터베이스 CRUD작업과 관련된 모델 호출
					dto.setAt_name(at_name);
					dto.setAt_belong(at_belong);
					dto.setAt_debutdate(at_debutdate);
					dto.setAt_debutsong(at_debutsong);
					dto.setAt_birth(at_birth);
					dto.setAt_contry(at_contry);
					dto.setAt_gender(at_gender);
					dto.setAt_Soundinfo(at_Soundinfo);
					dto.setAt_image(at_image);

					req.setAttribute("dto", dto);
					sucorfail = dao.insert(dto);
					dao.close();
					
					
				} // if
				else {
					sucorfail = -1;
				} // else
				// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
				// 5-1]DB입력 성공 여부 및 파일용량 초과 판단용 속성 저장
				req.setAttribute("SUC_FAIL", sucorfail);

				// 5-2]컨트롤러 구분용-입력:INS, 수정:EDT, 삭제:DEL
				req.setAttribute("WHERE", "INS");

				if (sucorfail == 1) {// 입력성공
					// ※입력후 바로 목록으로 이동]-반드시 List.jsp가 아닌 List.do로 이동
					//req.getRequestDispatcher("/DATAROOM/List.do").forward(req, resp);
					req.getRequestDispatcher("/ZAMONG/Album/List.do").forward(req, resp);
				} // if
				else {// 입력실패 혹은 파일용량 초과
					req.setAttribute("errorMessage", sucorfail == 0 ? "입력 실패" : "파일용량 초과");

					resp.setContentType("text/html; charset=UTF-8");
					PrintWriter out = resp.getWriter();
					out.println("<script>");
					out.println("alert('" + (sucorfail == 0 ? "입력실패" : "파일용량초과") + "')");
					out.println("history.back();");
					out.println("</script>");

				} // else*/
			}
		}
		else if (url.toUpperCase().contains("LIST.DO")) {
			req.setCharacterEncoding("UTF-8");
			SoundDAO ssDao = new SoundDAO(req.getServletContext());
			AlbumDAO alDao = new AlbumDAO(req.getServletContext());
			String 	searchColumn = req.getParameter("searchColumn");
			String 	searchWord = req.getParameter("searchWord");
			//String no = req.getParameter("at_no");
		
			//dao.updateVisitCount(no);
			//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]
			//String addQuery ="";
			Map<String,Object> map = new HashMap<String,Object>();
			
			if(searchWord !=null){
				//addQuery+="searchColumn="+searchColumn+"&searchWord="+searchWord+"&";
				map.put("searchColumn",searchColumn);
				map.put("searchWord",searchWord);
				//map.put("Notice_category",Notice_category);
			}
			int alTotalRecord=alDao.getTotalRecordCount(map);
			int ssTotalRecord=ssDao.getTotalRecordCount(map);
			//페이지 사이즈
			int pageSize  =Integer.valueOf(req.getServletContext().getInitParameter("PAGE_SIZE"));
			//블락페이지
			int blockPage =Integer.parseInt(req.getServletContext().getInitParameter("BLOCK_PAGE"));
			//전체 페이지수]
			int alTotalPage = (int)Math.ceil((double)alTotalRecord/pageSize);		
			//현재 페이지를 파라미터로 받기]
			int nowPage=req.getParameter("nowPage")==null ? 1 :	Integer.parseInt(req.getParameter("nowPage"));		
			//시작 및 끝 ROWNUM구하기]
			int start= (nowPage-1)*pageSize+1;
			int end = nowPage*pageSize;
			map.put("start",start);
			map.put("end",end);
			//페이징을 위한 로직 끝]
			List<SoundDTO> list= alDao.selectAlbumList(map);
			req.setAttribute("album", list);
			
			list = ssDao.selectSoundList(map);
			req.setAttribute("sound", list);
			//페이징용 문자열 생성
			String alPageing = PagingUtil.pagingText(alTotalRecord, pageSize, blockPage, nowPage, req.getServletContext().getContextPath()+"/ZAMONG/Album/List.do?");
			String ssPageing = PagingUtil.pagingText(ssTotalRecord, pageSize, blockPage, nowPage, req.getServletContext().getContextPath()+"/ZAMONG/Album/List.do?");
			
			
			//라]결과값이 있으면 리퀘스트 영역에 저장
			req.setAttribute("alPageing", alPageing);
			req.setAttribute("ssPageing", ssPageing);
			req.setAttribute("nowPage", nowPage);
			req.setAttribute("totalPage", alTotalPage);
			req.setAttribute("alTotalRecord", alTotalRecord);
			req.setAttribute("pageSize", pageSize);
			req.setAttribute("nowPage", nowPage);
			ssDao.close();
			alDao.close();
		
			req.getRequestDispatcher("/bbs/album/albumList.jsp").forward(req, resp);
		}
		
	}
}



