package com.zamong.web.cash;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.ch.service.CashDTO;
import com.zamong.ch.service.impl.CashDAO;

public class CashWriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("/ZAMONG/MemberList.do").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");	
	String bp_no = req.getParameter("bp_no");
		
	String pd_no = req.getParameter("pd_no");
	String ch_havecash = req.getParameter("price");
	String me_no = req.getParameter("me_no");
	BuyproductDAO dao1 = new BuyproductDAO(req.getServletContext());
	BuyproductDTO dto1 = new BuyproductDTO();
		CashDAO dao = new CashDAO(req.getServletContext());
		CashDTO dto = new CashDTO();
	/*
	
		dto.setMe_no(me_no);
		dto.setCh_havecash(ch_havecash);
	
	dao1.insert(dto1,dto);*/
		
		dao.close();
		req.getRequestDispatcher("/ZAMONG/Cash/List.do").forward(req, resp);
	}
	

}
