package com.zamong.web.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.me.service.MemberDTO;
import com.zamong.me.service.impl.MemberDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;

public class MemberDeleteController extends HttpServlet{
	@Override 
	protected void service(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		int sucorfail;
		//서비스 호출
		String no = req.getParameter("me_no");
		MemberDAO dao = new MemberDAO(req.getServletContext());
		
		MemberDTO dto= dao.selectOne(no);
		
		sucorfail = dao.delete(no);
		dao.close();
		
		 
		
		req.setAttribute("SUC_FAIL", sucorfail);
		// 5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		
		req.setAttribute("WHERE", "DEL");
		
		req.getRequestDispatcher("/bbs/membermgmt/MemberMessage.jsp").forward(req, resp);
		
		
		
		

	/*
		//리퀘스트 영역에 데이타 저장]
				req.setAttribute("SUC_FAIL", sucorfail);
				req.setAttribute("WHERE", "DEL");
				//포워드]
				req.getRequestDispatcher("/bbs/notice/Message.jsp").forward(req, resp);*/
		
		
	}////////////////////////////////////
	
	
}
