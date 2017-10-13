package com.zamong.web.musicvideo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mv.service.MusicVideoDTO;
import com.zamong.mv.service.impl.MusicVideoDAO;

import model.PagingUtil;

public class MusicVideoListController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}////////////////////////	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.사용자 요청을 받는다
		// 2.요청을 분석한다
		// 3.모델에서 필요한 로직을 호출해서 결과값이 있으면 받기
		// 4.결과값이 있으면 리퀘스트 영역에 저장
		// 5.결과값을 뿌려주거나 이동할 뷰(JSP페이지) 선택후 포워딩
		// 뷰 선택
		
		//한글처리]
		req.setCharacterEncoding("UTF-8");
		
		MusicVideoDAO dao = new MusicVideoDAO(req.getServletContext());
		String searchColumn = req.getParameter("searchColumn");
		String searchWord = req.getParameter("searchWord");
		String mv_no = req.getParameter("mv_no");
		
		
		//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]
		String addQuery ="";
		Map<String,Object> map = new HashMap<String,Object>();
		//Map<String,Object> map1 = new HashMap<String,Object>();
		if(searchWord !=null){
			addQuery+="searchColumn="+searchColumn+"&searchWord="+searchWord+"&";
			map.put("searchColumn",searchColumn);
			map.put("searchWord",searchWord);
		}
		int totalRecordCount=dao.getTotalCount(map);
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

		
		List<MusicVideoDTO> list= dao.selectList(map);
		
		//페이징용 문자열 생성
		String pagingString = PagingUtil.pagingText(totalRecordCount, pageSize, blockPage, nowPage, req.getServletContext().getContextPath()+"/ZAMONG/MusicVideoList.do?");
		
		
/*		List listAll=dao.listAll();
		req.setAttribute("list", listAll);*/
		
		req.setAttribute("list", list);

		//페이징용 결과값 저장
		req.setAttribute("pagingString", pagingString);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalRecordCount", totalRecordCount);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("nowPage", nowPage);
		dao.close();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/bbs/zamongtv/MusicVideo/MusicvideoList.jsp");
															     
		// 포워딩
		dispatcher.forward(req, resp);
	}
	
	
	
}



