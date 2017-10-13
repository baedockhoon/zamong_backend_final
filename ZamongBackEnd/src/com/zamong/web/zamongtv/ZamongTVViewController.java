package com.zamong.web.zamongtv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.tv.service.ZamongTVDTO;
import com.zamong.tv.service.impl.ZamongTVDAO;


public class ZamongTVViewController extends HttpServlet {
	//2]service()혹은 doGet() 혹은 doPost()오버라이딩
	@Override
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String view_no = req.getParameter("view_no");
		//4]모델 호출 및 결과값 받기
		ZamongTVDAO dao = new ZamongTVDAO(req.getServletContext());
		ZamongTVDTO dto = dao.selectOne(view_no);
		//줄바꿈 처리
		dto.setTv_contents(dto.getTv_contents().replace("\r\n","<br/>"));
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		//6]뷰 선택후 포워딩
		req.getRequestDispatcher("/bbs/zamongtv/ZamongTVView.jsp").forward(req, resp);
		
		
	}/////////////////////service()
	
	
}
