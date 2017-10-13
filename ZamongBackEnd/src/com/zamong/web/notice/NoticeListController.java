package com.zamong.web.notice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;
import com.zamong.se.service.SearchDTO;
import com.zamong.se.service.impl.SearchDAO;

import model.PagingUtil;


public class NoticeListController extends HttpServlet {
	
	
	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}////////////////////////	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		NotiDataDAO dao = new NotiDataDAO(req.getServletContext());
		String 	searchColumn = req.getParameter("searchColumn");
		String 	searchWord = req.getParameter("searchWord");
		String Notice_category = req.getParameter("Notice_category");		
		String no = req.getParameter("nt_no");
	
		dao.updateVisitCount(no);
		//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]
		String addQuery ="";
		Map<String,Object> map = new HashMap<String,Object>();
		//Map<String,Object> map1 = new HashMap<String,Object>();
		if(searchWord != null){
			addQuery+="searchColumn="+searchColumn+"&searchWord="+searchWord+"&";
			map.put("searchColumn",searchColumn);
			map.put("searchWord",searchWord);
		}
		if(Notice_category != null && !Notice_category.equals("")){
			map.put("Notice_category",Notice_category);
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
		List<NotiDataDTO> list= dao.selectList(map);
		System.out.println(searchWord);
		if (searchWord != null && !searchWord.equals("")) {
		SearchDAO dao1 = new SearchDAO(req.getServletContext());
			SearchDTO dto1 = new SearchDTO();
					dto1.setSearch(searchWord);
			if(dao1 != null) {
				dao1.insert(dto1);
			}
		}
			
		//페이징용 문자열 생성
		String pagingString = PagingUtil.pagingText(totalRecordCount, pageSize, blockPage, nowPage, req.getServletContext().getContextPath()+"/ZAMONG/NoticeList.do?");
		
		
		//라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("list", list);
		req.setAttribute("pagingString", pagingString);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalRecordCount", totalRecordCount);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("nowPage", nowPage);
		dao.close();
	
	
		RequestDispatcher dispatcher=req.getRequestDispatcher("/bbs/notice/NoticeList.jsp");
		
		dispatcher.forward(req, resp);
		
		
	}
	

}



