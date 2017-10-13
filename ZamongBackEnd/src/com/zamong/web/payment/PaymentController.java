package com.zamong.web.payment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.ch.service.CashDTO;
import com.zamong.ch.service.impl.CashDAO;
import com.zamong.pd.service.ProductDTO;
import com.zamong.pd.service.impl.ProductDAO;
import com.zamong.st.service.StreamingDTO;
import com.zamong.st.service.impl.StreamingDAO;

public class PaymentController extends HttpServlet {
	String getStringValue(String value){
	      switch(value){
	      	 case "1000":return "1000";
	         case "2000":return "2000";
	         case "3000":return "3000";
	         case "4000":return "4000";
	         case "5000":return "5000";
	         case "6000":return "6000";
	         case "7000":return "7000";
	         case "8000":return "8000";
	         case "9000":return "9000";
	         case "10000":return "10000";
	         default : return "11000";
	      }
	   }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("/ZAMONG/Cash/Write.do").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");	
		String price;
		String price1;
		 price1= req.getParameter("price1");
		 price  = getStringValue(req.getParameter("price"));
		 price = price1;
		 String pd_no = req.getParameter("pd_no");
		 String pd_name = req.getParameter("pd_name");
		 String pd_price = req.getParameter("pd_price");

		//String buyway = req.getParameter("buyway");

	String me_no = req.getParameter("me_no");
		//price = req.getParameter("price");
	CashDAO cash = new CashDAO(req.getServletContext());
	CashDTO cash1 = new CashDTO();
	StreamingDAO dao1 = new StreamingDAO(req.getServletContext());
	StreamingDTO dto1 = new StreamingDTO();
		BuyproductDAO dao = new BuyproductDAO(req.getServletContext());
		BuyproductDTO dto = new BuyproductDTO();
		dto.setBp_price(price);
		dto.setMe_no(me_no);
		dto.setPd_no(pd_no);
		dto1.setMe_no(me_no);

		cash1.setMe_no(me_no);
		cash1.setCh_havecash(price);
		dao.insert(dto,dto1,cash1);
		
		
		req.setAttribute("dto",dto);
		dao.close();
	/*	req.getRequestDispatcher("/ZAMONG/Streaming/Write.do").forward(req, resp);*/
		req.getRequestDispatcher("/ZAMONG/Payment/List.do").forward(req, resp);
	/*req.getRequestDispatcher("/ZAMONG/MemberList.do").forward(req, resp);*/
		/*req.getRequestDispatcher("/bbs/payment/PaymentList.jsp").forward(req, resp);*/
	}
	

}
