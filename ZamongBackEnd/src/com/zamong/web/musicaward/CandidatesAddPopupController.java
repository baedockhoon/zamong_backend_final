package com.zamong.web.musicaward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CandidatesAddPopupController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ma_no=req.getParameter("ma_no");
		req.setAttribute("ma_no", ma_no);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/bbs/musicaward/candidates/CandidatesWrite_Popup.jsp");
															     
		// 포워딩
		dispatcher.forward(req, resp);
	}
}


