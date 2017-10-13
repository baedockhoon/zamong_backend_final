package com.zamong.web.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.pd.service.ProductDTO;
import com.zamong.pd.service.impl.ProductDAO;

public class ProductListController extends HttpServlet {
	@Override 
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String no = req.getParameter("pd_no");
	
		ProductDAO dao = new ProductDAO(req.getServletContext());
		ProductDTO dto = new ProductDTO();
		List<ProductDTO> list = dao.selectList();
	
		//줄바꿈 처리
		dao.close();
	
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("list",list);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/product/ProductList.jsp").forward(req, resp);
		
	}/////////////////////service()
	
	
}


