package com.zamong.web.payment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.me.service.MemberDTO;
import com.zamong.me.service.impl.MemberDAO;

import model.PagingUtil;

public class UserController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}////////////////////////

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		MemberDAO dao = new MemberDAO(req.getServletContext());
		String searchColumn = req.getParameter("searchColumn");
		String searchWord = req.getParameter("searchWord");
		String me_no = req.getParameter("me_no");
		String pd_no = req.getParameter("pd_no");
		String ss_no = req.getParameter("ss_no");
		Map<String, Object> map = new HashMap<String, Object>();
	
		// 검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]

		int totalRecordCount = dao.getTotalRecordCount(map);
		// 페이지 사이즈
		int pageSize = Integer.valueOf(req.getServletContext().getInitParameter("PAGE_SIZE"));
		// 블락페이지
		int blockPage = Integer.parseInt(req.getServletContext().getInitParameter("BLOCK_PAGE"));
		// 전체 페이지수]
		int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);
		// 현재 페이지를 파라미터로 받기]
		int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));
		// 시작 및 끝 ROWNUM구하기]
		int start = (nowPage - 1) * pageSize + 1;
		int end = nowPage * pageSize;
		map.put("start", start);
		map.put("end", end);
		// 페이징을 위한 로직 끝]
		List<MemberDTO> list = dao.selectList(map);
			
		// 검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]

		// 페이징용 문자열 생성
	
			
		// 라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("list", list);

		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalRecordCount", totalRecordCount);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("me_no",me_no);
		req.setAttribute("pd_no",pd_no);
		req.setAttribute("ss_no",ss_no);
		dao.close();
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/bbs/payment/UserList.jsp");

		dispatcher.forward(req, resp);

	}
}