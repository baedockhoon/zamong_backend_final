package com.zamong.web.broadcast;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.br.service.BroadCastDAO;

public class BroadCastDeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String br_no = req.getParameter("br_no");
		BroadCastDAO dao = new BroadCastDAO(req.getServletContext());
		int sucorfail = dao.delete(br_no);	
		dao.close();
		
		//req.getRequestDispatcher("/ZAMONG/MusicVideoList.do").forward(req, resp);
		
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		req.setAttribute("WHERE","DEL");
		//6]포워드
		req.getRequestDispatcher("/bbs/zamongtv/Broadcast/BroadCastMessage.jsp").forward(req, resp);
		
		
	}
	
	
}


