package com.zamong.web.todaymagazine;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mg.service.MagazineDTO;
import com.zamong.mg.service.impl.MagazineDAO;

import model.PagingUtil;

public class TodayMagazineController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MagazineDAO dao = new MagazineDAO(req.getServletContext());
		MagazineDTO dto = new MagazineDTO();
		String no = req.getParameter("mg_no");
		dao.updateVisitCount(no);
		//페이징을 위한 로직 시작]
		//전체 레코드 수
		int totalRecordCount=dao.getTotalRecordCount();
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
		//페이징을 위한 로직 끝]
		List list = dao.selectList_today(start, end);
		
		//페이징용 문자열 생성
		String pagingString = PagingUtil.pagingText(totalRecordCount, pageSize, blockPage, nowPage, req.getServletContext().getContextPath()+"/ZAMONG/TodayMagazine.do?");
		
		
		//라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("list", list);
		req.setAttribute("pagingString", pagingString);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalRecordCount", totalRecordCount);
		req.setAttribute("pageSize", pageSize);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/bbs/magazine/TodayMagazine.jsp");
		// 포워딩
		dispatcher.forward(req, resp);
	}
	
	
	
}



