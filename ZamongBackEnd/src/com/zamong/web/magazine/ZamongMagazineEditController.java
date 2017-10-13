package com.zamong.web.magazine;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mg.service.MagazineDTO;
import com.zamong.mg.service.impl.MagazineDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;

public class ZamongMagazineEditController extends HttpServlet {
	
	@Override
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getMethod().toUpperCase().equals("GET")){//수정폼으로 이동
			String no = req.getParameter("mg_no");
			String nowPage = req.getParameter("nowPage");
			//모델 호출 및 결과 값 받기]
			MagazineDAO dao = new MagazineDAO(req.getServletContext());
			MagazineDTO dto= dao.selectOne(no);
			dao.close();
			//리퀘스트 영역에 저장]
			req.setAttribute("dto", dto);
			req.setAttribute("nowPage", nowPage);
			//포워드]
			req.getRequestDispatcher("/bbs/magazine/ZamongMagazineEdit.jsp").forward(req, resp);
		}
		else{//수정처리-POST방식
			//한글처리]
			int sucorfail;
			
			req.setCharacterEncoding("UTF-8");
			//3]요청분석- 수정처리 요청
			//4]모델호출 및 결과값 받기
			//파일 업로드와 관련된 모델(비지니스 로직) 호출
			String nowPage = req.getParameter("nowPage");
			//수정성공:1,실패:0,용량초과:-1
			String no = req.getParameter("mg_no");
			
			req.setAttribute("mg_no", no);
			req.setAttribute("nowPage", nowPage);
			
			String title = req.getParameter("title");
			String division = req.getParameter("Magazine_category");
			//String postdate= req.getParameter("postdate");
			String content= req.getParameter("content");
			//데이타베이스 CRUD작업과 관련된 모델 호출]
			MagazineDAO dao = new MagazineDAO(req.getServletContext());
			MagazineDTO dto = new MagazineDTO();
			dto.setMg_no(no);
			dto.setMg_division(division);
			dto.setMg_title(title);
			dto.setMg_contents(content);
			
			sucorfail = dao.update(dto);
			
			dao.close();
			
	
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		req.setAttribute("WHERE","EDT");
		//6]포워드
		req.getRequestDispatcher("/bbs/magazine/Magazine_Message.jsp").forward(req, resp);
		}/////////else
			

	}////////////service
	
	
}
