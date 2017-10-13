package com.zamong.web.magazine;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mg.service.MagazineDTO;
import com.zamong.mg.service.impl.MagazineDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;

public class ZamongMagazineDeleteController extends HttpServlet {

	@Override 
	protected void service(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		
		//서비스 호출
		String no = req.getParameter("mg_no");
		MagazineDAO dao = new MagazineDAO(req.getServletContext());
		
		MagazineDTO dto= dao.selectOne(no);
		
		dao.delete(no);
		dao.close();
		
		req.getRequestDispatcher("/ZAMONG/zamongMagazine.do").forward(req, resp);
	}
	
	
	
}



