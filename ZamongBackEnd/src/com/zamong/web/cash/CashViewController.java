package com.zamong.web.cash;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.ch.service.CashDTO;
import com.zamong.ch.service.impl.CashDAO;


public class CashViewController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String no = req.getParameter("ch_no");
		String me_no = req.getParameter("me_no");
		String nowPage = req.getParameter("nowPage");
		//4]모델 호출 및 결과값 받기
		CashDAO dao = new CashDAO(req.getServletContext());
		
		CashDTO dto = dao.selectOne(me_no);
	
		dao.close();
		
		
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("me_no", me_no);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/membermgmt/CashPoint.jsp").forward(req, resp);
		
		
		
		
	}
	
}
