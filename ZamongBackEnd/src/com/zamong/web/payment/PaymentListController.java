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
import com.zamong.ch.service.CashDTO;
import com.zamong.ch.service.impl.CashDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;

import model.PagingUtil;


public class PaymentListController extends HttpServlet {

/*	@Override 
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String no = req.getParameter("bp_no");
	
		BuyproductDAO dao = new BuyproductDAO(req.getServletContext());

		List<BuyproductDTO> list = dao.selectList();

		//줄바꿈 처리
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("list",list);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/membermgmt/MemberPoint.jsp").forward(req, resp);
		
	}/////////////////////service()
*/	
	
	@Override 
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		
		String no = req.getParameter("bp_no");
		
		BuyproductDAO dao = new BuyproductDAO(req.getServletContext());
		String 	searchColumn = req.getParameter("searchColumn");
		String 	searchWord = req.getParameter("searchWord");

	
	
		//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]
		String addQuery ="";
		Map<String,Object> map = new HashMap<String,Object>();
		//Map<String,Object> map1 = new HashMap<String,Object>();
		if(searchWord !=null){
			addQuery+="searchColumn="+searchColumn+"&searchWord="+searchWord+"&";
			
			map.put("searchColumn",searchColumn);
			map.put("searchWord",searchWord);
		}
	
		int totalRecordCount=dao.getTotalRecordCount(map);
		//페이지 사이즈
		int pageSize  =Integer.valueOf(req.getServletContext().getInitParameter("PAGE_SIZE"));
		//블락페이지
		int blockPage =Integer.parseInt(req.getServletContext().getInitParameter("BLOCK_PAGE"));
		//전체 페이지수]
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);		
		//현재 페이지를 파라미터로 받기]
		int nowPage=req.getParameter("nowPage")==null ? 1 :	Integer.parseInt(req.getParameter("nowPage"));		
		//시작 및 끝 ROWNUM구하기]
		int start= (nowPage-1)*pageSize+1;
		int end = nowPage*pageSize;
		map.put("start",start);
		map.put("end",end);
		
		
		//페이징을 위한 로직 끝]	
		List<BuyproductDTO> list= dao.selectList(map);
		
		//페이징용 문자열 생성
		String pagingString = PagingUtil.pagingText(totalRecordCount, pageSize, blockPage, nowPage, req.getServletContext().getContextPath()+"/ZAMONG/Payment/List.do?");
		
		
		//라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("list", list);
		req.setAttribute("pagingString", pagingString);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalRecordCount", totalRecordCount);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("bp_no",no);
		dao.close();
	
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/membermgmt/MemberPoint.jsp").forward(req, resp);
		
	}/////////////////////service()
	
	
}

