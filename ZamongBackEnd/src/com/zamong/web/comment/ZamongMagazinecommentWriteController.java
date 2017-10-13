package com.zamong.web.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mg.service.CommentDTO;
import com.zamong.mg.service.MagazineDTO;
import com.zamong.mg.service.impl.CommentDAO;
import com.zamong.mg.service.impl.MagazineDAO;

public class ZamongMagazinecommentWriteController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//한글처리
		req.setCharacterEncoding("UTF-8");
		//오류시 입력값 보존을 위한 변수 선언
		String contents;
		String regidate;
		//3]요청분석 -입력처리 요청
		//4]모델호출 및 결과값 받기
		
		
		//기타 파라미터 받아서 테이블에 입력처리
		contents = req.getParameter("mg_cm_contents");
		regidate = req.getParameter("mg_cm_regidate");
		//데이터베이스 CRUD작업과 관련된 모델 호출
		CommentDAO dao = new CommentDAO(req.getServletContext());
		CommentDTO dto = new CommentDTO();
		dto.setMg_cm_contents(contents);
		
		dao.insert(dto);
		dao.close();
		
		req.getRequestDispatcher("/ZAMONG/zamongMagazine.do").forward(req, resp);
		
		
	}
	
	
	
	
}
