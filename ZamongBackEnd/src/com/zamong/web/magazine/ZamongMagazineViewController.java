package com.zamong.web.magazine;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mg.service.CommentDTO;
import com.zamong.mg.service.MagazineDTO;
import com.zamong.mg.service.impl.CommentDAO;
import com.zamong.mg.service.impl.MagazineDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;

public class ZamongMagazineViewController extends HttpServlet {
	@Override 
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String no = req.getParameter("mg_no");
		String nowPage = req.getParameter("nowPage");
		//4]모델 호출 및 결과값 받기
		MagazineDAO dao = new MagazineDAO(req.getServletContext());
		MagazineDTO dto = dao.selectOne(no);
		dao.updateVisitCount(no);
		CommentDAO cm_dao = new CommentDAO(req.getServletContext());
		List cm_dto = cm_dao.selectList_CM();
		//줄바꿈 처리
		dto.setMg_contents(dto.getMg_contents().replace("\r\n","<br/>"));
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		req.setAttribute("cm_dto", cm_dto);
		req.setAttribute("nowPage", nowPage);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/magazine/ZamongMagazineView.jsp").forward(req, resp);
		
	}/////////////////////service()
	
	
	
	
	
}
