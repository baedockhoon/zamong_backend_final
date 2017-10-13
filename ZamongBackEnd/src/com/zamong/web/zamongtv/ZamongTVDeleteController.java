package com.zamong.web.zamongtv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.tv.service.impl.ZamongTVDAO;


public class ZamongTVDeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("del_no");
		ZamongTVDAO dao = new ZamongTVDAO(req.getServletContext());
		int affected = dao.delete(no);	
		dao.close();
		
		req.getRequestDispatcher("/ZAMONG/ZamongTVList.do").forward(req, resp);
		
	}
	
	
}


