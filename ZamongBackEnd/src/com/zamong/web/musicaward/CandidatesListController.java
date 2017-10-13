package com.zamong.web.musicaward;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.ca.service.CandidatesDTO;
import com.zamong.ca.service.impl.CandidatesDAO;
import com.zamong.mv.service.MusicVideoDTO;
import com.zamong.mv.service.impl.MusicVideoDAO;

import model.PagingUtil;

public class CandidatesListController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}////////////////////////	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.사용자 요청을 받는다
		// 2.요청을 분석한다
		// 3.모델에서 필요한 로직을 호출해서 결과값이 있으면 받기
		// 4.결과값이 있으면 리퀘스트 영역에 저장
		// 5.결과값을 뿌려주거나 이동할 뷰(JSP페이지) 선택후 포워딩
		// 뷰 선택
		
		//한글처리]
		req.setCharacterEncoding("UTF-8");
		
		CandidatesDAO dao = new CandidatesDAO(req.getServletContext());
		String ca_no = req.getParameter("ca_no");
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<CandidatesDTO> list= dao.selectList(map,null);
		
		//페이징용 문자열 생성
		
/*		List listAll=dao.listAll();
		req.setAttribute("list", listAll);*/
		
		req.setAttribute("list", list);

		dao.close();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/bbs/musicaward/candidates/CandidatesList.jsp");
															     
		// 포워딩
		dispatcher.forward(req, resp);
	}
	
	
	
}



