package com.zamong.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.zamong.ad.service.AdminLoginDAO;

public class BackEndLoginProcessController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 사용자 요청을 받는다
		// 2. 요청을 분석한다 
		// 3. 모델에서 필요한 로직을 호출해서 결과값이 있으면 받기 
		// 4. 결과값이 있으면 리퀘스트 영역에 저장
		// 5. 결과값을 뿌려주거나 이동할 뷰(JSP페이지) 선택후 포워딩
		// 뷰 선택
		//req.getRequestDispatcher("/ZAMONG/Login.do").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String adminid = req.getParameter("zamongadminid").trim();
		String adminpass = req.getParameter("zamongadminpass").trim();

		//실제 테이블과 연동]
		AdminLoginDAO dao = new AdminLoginDAO(req.getServletContext());

		boolean flag=dao.isMember(adminid,adminpass);
		dao.close();
		if(flag){
			HttpSession session = req.getSession();
			//1.로그인 처리]-세션영역에 속성 저장
			session.setAttribute("ADMIN_ID", adminid);
			/*session.setAttribute("USER_PWD",adminpass);*/
			//2.로그인 처리후 마이 페이지로 이동]
			/*resp.sendRedirect("/bbs/MainPage.jsp");*/
			req.getRequestDispatcher("/bbs/MainPage.jsp").forward(req, resp);
		}
		else{//비회원-다시 로그인 페이지로 포워드

			//리퀘스트 영역에 필요한 데이터 저장]
			req.setAttribute("ERROR_MASSAGE","아이디 패스워드가 맞지않습니다. <br/> 다시 로그인해주세요");
			//로그인 페이지로 포워드]
			req.getRequestDispatcher("/bbs/adminlogin/Login.jsp").forward(req, resp);		
			
		}
/*		RequestDispatcher dispatcher = req.getRequestDispatcher("/bbs/adminlogin/Login.jsp");
		// 포워딩
		dispatcher.forward(req, resp);*/
	}
	
}



