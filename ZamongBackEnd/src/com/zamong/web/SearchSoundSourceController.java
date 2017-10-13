package com.zamong.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mv.service.impl.SearchSoundSourceDAO;
import com.zamong.tv.service.impl.SearchArtistDAO;


public class SearchSoundSourceController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchSoundSourceDAO dao = new SearchSoundSourceDAO(req.getServletContext());
		List list=dao.selectList();
		
		//라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/bbs/zamongtv/FindSoundSourcePopup.jsp");
															     
		// 포워딩
		dispatcher.forward(req, resp);
	}
}
