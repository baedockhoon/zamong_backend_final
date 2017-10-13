package com.zamong.web.payment;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.ch.service.CashDTO;
import com.zamong.ch.service.impl.CashDAO;


public class PaymentViewController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String no = req.getParameter("bp_no");
		String me_no = req.getParameter("me_no");
		String nowPage = req.getParameter("nowPage");
		//4]모델 호출 및 결과값 받기
		BuyproductDAO dao = new BuyproductDAO(req.getServletContext());
		
		BuyproductDTO dto = dao.selectOne(me_no);
	
		dao.close();
		
		
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		req.setAttribute("nowPage", nowPage);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/membermgmt/MemberPointView.jsp").forward(req, resp);
		
		
		
		
	}
	
}
