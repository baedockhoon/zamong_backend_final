package com.zamong.web.notice;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;


public class NoticeViewController extends HttpServlet {
	//2]service()혹은 doGet() 혹은 doPost()오버라이딩
	@Override 
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String no = req.getParameter("nt_no");
		String nowPage = req.getParameter("nowPage");
		//4]모델 호출 및 결과값 받기
		NotiDataDAO dao = new NotiDataDAO(req.getServletContext());
		dao.updateVisitCount(no);
		NotiDataDTO dto = dao.selectOne(no);
		Map<String,NotiDataDTO> map = dao.prevNnext(no);
		//줄바꿈 처리
		dto.setNt_contents(dto.getNt_contents().replace("\r\n","<br/>"));
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		req.setAttribute("nowPage", nowPage);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/notice/NoticeView.jsp").forward(req, resp);
		
	}/////////////////////service()
	
	
}



