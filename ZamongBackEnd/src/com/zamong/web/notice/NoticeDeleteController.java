package com.zamong.web.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;


public class NoticeDeleteController extends HttpServlet{
	
	@Override 
	protected void service(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		
		//서비스 호출
		String no = req.getParameter("nt_no");
		NotiDataDAO dao = new NotiDataDAO(req.getServletContext());
		
		NotiDataDTO dto= dao.selectOne(no);
		
		dao.delete(no);
		dao.close();
		
		req.getRequestDispatcher("/ZAMONG/NoticeList.do").forward(req, resp);

	/*
		//리퀘스트 영역에 데이타 저장]
				req.setAttribute("SUC_FAIL", sucorfail);
				req.setAttribute("WHERE", "DEL");
				//포워드]
				req.getRequestDispatcher("/bbs/notice/Message.jsp").forward(req, resp);*/
		
		
	}////////////////////////////////////
	
}
