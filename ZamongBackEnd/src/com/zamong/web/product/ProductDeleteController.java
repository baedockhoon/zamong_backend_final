package com.zamong.web.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;
import com.zamong.pd.service.ProductDTO;
import com.zamong.pd.service.impl.ProductDAO;


public class ProductDeleteController extends HttpServlet{
	
	@Override 
	protected void service(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		
		//서비스 호출
		String no = req.getParameter("pd_no");
		ProductDAO dao = new ProductDAO(req.getServletContext());
		
/*		ProductDTO dto= dao.selectOne(no);*/
		
		dao.delete(no);
		dao.close();
		
		req.getRequestDispatcher("/ZAMONG/Product/List.do").forward(req, resp);

	/*
		//리퀘스트 영역에 데이타 저장]
				req.setAttribute("SUC_FAIL", sucorfail);
				req.setAttribute("WHERE", "DEL");
				//포워드]
				req.getRequestDispatcher("/bbs/notice/Message.jsp").forward(req, resp);*/
		
		
	}////////////////////////////////////
	
}
