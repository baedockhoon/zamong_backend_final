package com.zamong.web.artist;

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
import com.zamong.at.service.ArtistDTO;
import com.zamong.at.service.impl.ArtistDAO;

import model.FileUtils;
import model.PagingUtil;


public class ArtistController extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		String mode = req.getMethod();

		if (url.toUpperCase().contains("WRITE.DO")) {
			if (mode.equalsIgnoreCase("get"))
				req.getRequestDispatcher("/bbs/artist/artistWrite.jsp").forward(req, resp);
			else {
				req.setCharacterEncoding("UTF-8");

				String grop;
				String gp_name;
				String gp_gender;
				String gp_image;
				String at_name;
				String at_belong;
				String at_debutdate;
				String at_debutsong;
				String at_birth;
				String at_contry;
				String at_gender;
				String at_artistinfo;
				String at_image;
				String gp_belong;
				String gp_debutdate;
				ArtistDTO dto = new ArtistDTO();
				
				MultipartRequest mr = FileUtils.upload(req, req.getServletContext().getRealPath("/Images/Artist"));

				int sucorfail;// DB입력 성공시에는 1, 실패시0, 파일용량 초과시에는 -1

				if (mr != null) {// 파일 업로드 성공시 데이터 입력처리
					// 기타 파라미터 받아서 테이블에 입력처리

					ArtistDAO dao = new ArtistDAO(req.getServletContext());
					
					//그룹정보
					grop = mr.getParameter("group");
					gp_name = mr.getParameter("gp_name");
					gp_gender = mr.getParameter("gp_gender");
					gp_belong = mr.getParameter("gp_belong");
					gp_debutdate = mr.getParameter("gp_debutdate");
					gp_image = mr.getFilesystemName("gp_image");
					
					//아티스트 정보
					at_name = mr.getParameter("at_name");
					at_belong = mr.getParameter("at_belong");
					at_debutdate = mr.getParameter("at_debutdate");
					at_debutsong = mr.getParameter("at_debutsong");
					at_birth = mr.getParameter("at_birth");
					at_contry = mr.getParameter("at_contry");
					at_gender = mr.getParameter("at_gender");
					at_artistinfo = mr.getParameter("at_artistinfo");
					at_image = mr.getFilesystemName("at_image");

					// 그룹 데이터베이스 CRUD작업과 관련된 모델 호출
					dto.setGp_name(gp_name);
					dto.setGp_gender(gp_gender);
					dto.setGp_image(gp_image);
					dto.setGp_debutdate(gp_debutdate);
					dto.setGp_belong(gp_belong);

					// 아티스트 데이터베이스 CRUD작업과 관련된 모델 호출
					dto.setAt_name(at_name);
					dto.setAt_belong(at_belong);
					dto.setAt_debutdate(at_debutdate);
					dto.setAt_debutsong(at_debutsong);
					dto.setAt_birth(at_birth);
					dto.setAt_contry(at_contry);
					dto.setAt_gender(at_gender);
					dto.setAt_artistinfo(at_artistinfo);
					dto.setAt_image(at_image);

					req.setAttribute("dto", dto);
					sucorfail = dao.insert(dto, grop);
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
					req.getRequestDispatcher("/ZAMONG/Artist/List.do").forward(req, resp);
				} // if
				else {// 입력실패 혹은 파일용량 초과
					req.setAttribute("errorMessage", sucorfail == 0 ? "입력 실패" : "파일용량 초과");

					resp.setContentType("text/html; charset=UTF-8");
					PrintWriter out = resp.getWriter();
					out.println("<script>");
					out.println("alert('" + (sucorfail == 0 ? "입력실패" : "파일용량초과") + "')");
					out.println("history.back();");
					out.println("</script>");

				} // else
			}
		}
		else if (url.toUpperCase().contains("LIST.DO")) {
			req.setCharacterEncoding("UTF-8");
			ArtistDAO dao = new ArtistDAO(req.getServletContext());
			String 	searchColumn = req.getParameter("searchColumn");
			String 	searchWord = req.getParameter("searchWord");
			String no = req.getParameter("at_no");
		
			dao.updateVisitCount(no);
			//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]
			Map<String,Object> map = new HashMap<String,Object>();
			
			if(searchWord != null){
				map.put("searchColumn",searchColumn);
				map.put("searchWord",searchWord);
			}
			int totalRecordCount=dao.getTotalRecordCount(map);
			//페이지 사이즈
			int pageSize  =Integer.valueOf(req.getServletContext().getInitParameter("PAGE_SIZE"));
			//블락페이지
			int blockPage =Integer.parseInt(req.getServletContext().getInitParameter("BLOCK_PAGE"));
			//전체 페이지수]
			int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);		
			//현재 페이지를 파라미터로 받기]
			int nowPage=req.getParameter("nowPage")==null ? 1 :	Integer.parseInt(req.getParameter("nowPage"));		
			//시작 및 끝 ROWNUM구하기]
			int start= (nowPage-1)*pageSize+1;
			int end = nowPage*pageSize;
			map.put("start",start);
			map.put("end",end);
			//페이징을 위한 로직 끝]	
			List<ArtistDTO> list= dao.searchArtist(map);
			
			//페이징용 문자열 생성
			String pagingString = PagingUtil.pagingText(totalRecordCount, pageSize, blockPage, nowPage, req.getServletContext().getContextPath()+"/ZAMONG/Artist/List.do?");
			
			
			//라]결과값이 있으면 리퀘스트 영역에 저장
			req.setAttribute("list", list);
			req.setAttribute("pagingString", pagingString);
			req.setAttribute("nowPage", nowPage);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("totalRecordCount", totalRecordCount);
			req.setAttribute("pageSize", pageSize);
			req.setAttribute("nowPage", nowPage);
			dao.close();
		
			req.getRequestDispatcher("/bbs/artist/artistList.jsp").forward(req, resp);
			
		} 
		else if(url.toUpperCase().contains("SEARCHPOP.DO")) {
			
			req.setCharacterEncoding("UTF-8");
			ArtistDAO dao = new ArtistDAO(req.getServletContext());
			String 	searchWord = req.getParameter("searchWord");
			String 	searchColumn = req.getParameter("searchColumn");
			Map<String, Object> map = new HashMap<String, Object>();
			if(searchWord != null){
				map.put("searchColumn",searchColumn);
				map.put("searchWord",searchWord);
			}
			List<ArtistDTO> list = dao.searchArtist(map);
			req.setAttribute("map", map);
			req.setAttribute("list", list);
			dao.close();

			req.getRequestDispatcher("/Common/FindArtistPopup.jsp").forward(req, resp);
		}
		else if(url.toUpperCase().contains("VIEW.DO")) {
			
			req.setCharacterEncoding("UTF-8");
			ArtistDAO dao = new ArtistDAO(req.getServletContext());
			String 	at_no = req.getParameter("at_no");
			String 	gp_no = req.getParameter("gp_no");
			ArtistDTO dto = new ArtistDTO();
			List<ArtistDTO> list = null;
			if (at_no != null) {
				dto = dao.selectArtistOne(at_no);
				req.setAttribute("group", "A");
			} else {
				dto = dao.selectOne(gp_no);
				req.setAttribute("group", "G");
				list = dao.selectArtist(gp_no);
			}
			req.setAttribute("dto", dto);
			req.setAttribute("list", list);
			dao.close();
			req.getRequestDispatcher("/bbs/artist/artistView.jsp").forward(req, resp);
		}

	}
	
	
	/*@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}////////////////////////	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		NotiDataDAO dao = new NotiDataDAO(req.getServletContext());
		String 	searchColumn = req.getParameter("searchColumn");
		String 	searchWord = req.getParameter("searchWord");
		String Notice_category = req.getParameter("Notice_category");		
		String no = req.getParameter("nt_no");
	
		dao.updateVisitCount(no);
		//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]
		String addQuery ="";
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(searchWord !=null){
			addQuery+="searchColumn="+searchColumn+"&searchWord="+searchWord+"&";
			
			map.put("searchColumn",searchColumn);
			map.put("searchWord",searchWord);
			map.put("Notice_category",Notice_category);
		}
		int totalRecordCount=dao.getTotalRecordCount(map);
		//페이지 사이즈
		int pageSize  =Integer.valueOf(req.getServletContext().getInitParameter("PAGE_SIZE"));
		//블락페이지
		int blockPage =Integer.parseInt(req.getServletContext().getInitParameter("BLOCK_PAGE"));
		//전체 페이지수]
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);		
		//현재 페이지를 파라미터로 받기]
		int nowPage=req.getParameter("nowPage")==null ? 1 :	Integer.parseInt(req.getParameter("nowPage"));		
		//시작 및 끝 ROWNUM구하기]
		int start= (nowPage-1)*pageSize+1;
		int end = nowPage*pageSize;
		map.put("start",start);
		map.put("end",end);
		//페이징을 위한 로직 끝]	
		List<NotiDataDTO> list= dao.selectList(map);
		
		//페이징용 문자열 생성
		String pagingString = PagingUtil.pagingText(totalRecordCount, pageSize, blockPage, nowPage, req.getServletContext().getContextPath()+"/ZAMONG/NoticeList.do?");
		
		
		//라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("list", list);
		req.setAttribute("pagingString", pagingString);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalRecordCount", totalRecordCount);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("nowPage", nowPage);
		dao.close();
		req.setAttribute("list", list);
	
		RequestDispatcher dispatcher=req.getRequestDispatcher("/bbs/notice/NoticeList.jsp");
		
		dispatcher.forward(req, resp);
		
		
	}*/
	

}



