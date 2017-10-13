package com.zamong.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.ad.service.AdminLoginDAO;

public class BackEndLoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 사용자 요청을 받는다
		// 2. 요청을 분석한다
		// 3. 모델에서 필요한 로직을 호출해서 결과값이 있으면 받기 
		// 4. 결과값이 있으면 리퀘스트 영역에 저장
		// 5. 결과값을 뿌려주거나 이동할 뷰(JSP페이지) 선택후 포워딩
		// 뷰 선택
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/bbs/adminlogin/Login.jsp");
		// 포워딩
		dispatcher.forward(req, resp);
	}
	
}



