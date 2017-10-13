package com.zamong.web.Search;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.ch.service.CashDTO;
import com.zamong.ch.service.impl.CashDAO;
import com.zamong.se.service.SearchDTO;
import com.zamong.se.service.impl.SearchDAO;

public class SearchWriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	/*	req.getRequestDispatcher("/ZAMONG/Cash/Write.do").forward(req,resp);*/
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");	
	String search = req.getParameter("search");

		//price = req.getParameter("price");
	SearchDAO dao = new SearchDAO(req.getServletContext());
	SearchDTO dto = new SearchDTO();
	dto.setSearch(search);
		
		dao.insert(dto);
		
		
		req.setAttribute("dto",dto);
		dao.close();
		req.getRequestDispatcher("/ZAMONG/NoticeList.do").forward(req, resp);
	/*req.getRequestDispatcher("/ZAMONG/MemberList.do").forward(req, resp);*/
	}
	

}
