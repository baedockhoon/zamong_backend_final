package com.zamong.web.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;


public class NoticeWriteController extends HttpServlet {
 
	String getStringValue(String value){
	      switch(value){
	      
	         case "서비스소식":return "서비스소식";
	         case "서비스오픈":return "서비스오픈";
	         case "서비스종료":return "서비스종료";
	         case "서비스점검":return "서비스점검";
	         default : return "안내";
	      }
	   }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//req.getRequestDispatcher("/ZAMONG/NoticeList.do").forward(req, resp);
		doPost(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		//한글처리]
				req.setCharacterEncoding("UTF-8");	
				
				//오류시 입력값 보존을 위한 변수 선언]
				String classification;
				String title;
				String content;
				
				//3]요청분석- 입력처리 요청
				//4]모델호출 및 결과값 받기
				//파일 업로드와 관련된 모델(비지니스 로직) 호출
					//ad_no = req.getParameter("ad_no");
					classification = getStringValue(req.getParameter("Notice_category"));
					title = req.getParameter("title");
					content= req.getParameter("contents");
					//데이타베이스 CRUD작업과 관련된 모델 호출]
					NotiDataDAO dao = new NotiDataDAO(req.getServletContext());
					NotiDataDTO dto = new NotiDataDTO();
				//	dto.setAd_no(ad_no);
					dto.setNt_classification(classification);
					dto.setNt_title(title);
					dto.setNt_contents(content);
					
					
					dao.insert(dto);
					dao.close();
					resp.sendRedirect(req.getContextPath()+"/ZAMONG/NoticeList.do");
					//req.getRequestDispatcher("/ZAMONG/NoticeList.do").forward(req, resp);
	}
}



